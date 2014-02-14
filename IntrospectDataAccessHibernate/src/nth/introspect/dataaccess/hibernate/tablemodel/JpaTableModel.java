package nth.introspect.dataaccess.hibernate.tablemodel;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.AbstractTableModel;

import nth.introspect.Introspect;
import nth.introspect.dataaccess.hibernate.HibernateDataAccess;
import nth.introspect.dataaccess.hibernate.persistenceunit.PersistenceUnit;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.property.TableOrderComparator;
import nth.introspect.provider.domain.info.property.TableVisibleFilter;
import nth.introspect.tablemodel.DomainTableModel;
import nth.introspect.tablemodel.SortableTableModel;

public class JpaTableModel extends AbstractTableModel implements DomainTableModel, SortableTableModel {

	private int CACHE_SIZE = 100;
	private static final long serialVersionUID = 1L;
	private static final String SELECT = "select";
	private static final String FROM = "from";
	private static final String ORDER = " order ";
	private static final String ORDER_BY = "order by";
	private int startPosition;
	private List<?> items;
	private String ejbQlCount;
	private List<PropertyInfo> propertyInfos;
	private List<? extends SortKey> sortKeys;
	private String selectClause;
	private String fromClause;
	private String variableName;
	private final PersistenceUnit persistenceUnit;

	/**
	 * @param ejbQlSelect
	 *            a Enterprise Java Bean Query Language expression without order clause! (i.e. select c from Customer c where ...)
	 */
	public JpaTableModel(HibernateDataAccess<?> dataAccessObject, Class<?> domainClass, String ejbQlSelect) {
		// get persistanceUnit (the ugly way, because normally its shielded from other classes like this one)
		try {
			Field field = dataAccessObject.getClass().getField("persistenceUnit");
			persistenceUnit = (PersistenceUnit) field.get(dataAccessObject);
		} catch (Exception e) {
			throw new RuntimeException("Could not get the persistanceUnit from dataAccessObject");
		}

		// Bisect query
		String hqlLowerCase = ejbQlSelect.toLowerCase();
		// select clause
		int startSelectClause = hqlLowerCase.indexOf(SELECT) + SELECT.length();
		int endSelectClause = hqlLowerCase.indexOf(FROM);
		selectClause = ejbQlSelect.substring(startSelectClause, endSelectClause).trim(); // between select ... from
		// from clause (with optional where clause)
		int startFromClause = endSelectClause + FROM.length();
		int endFromClause = hqlLowerCase.indexOf(ORDER);
		if (endFromClause < 1) {
			endFromClause = ejbQlSelect.length();
		}
		fromClause = ejbQlSelect.substring(startFromClause, endFromClause).trim(); // between from ... where
		// variable name
		int startVariableName = fromClause.indexOf(" ");
		int endVariableName = fromClause.indexOf(" ", startVariableName + 1);
		variableName = fromClause.substring(startVariableName, endVariableName).trim();
		// create count query
		StringBuffer ejbQlCount = new StringBuffer(SELECT);
		ejbQlCount.append(" count(");
		ejbQlCount.append(variableName);
		ejbQlCount.append(") ");
		ejbQlCount.append(FROM);
		ejbQlCount.append(" ");
		ejbQlCount.append(fromClause);
		this.ejbQlCount = ejbQlCount.toString();
		// get column info
		TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
		TableOrderComparator propertyInfoComparator = new TableOrderComparator();
		propertyInfos = Introspect.getDomainProvider().getPropertyInfos(domainClass, propertyInfoFilter, propertyInfoComparator);
		// Initialize fields
		this.startPosition = 0;
		this.items = getItems(startPosition, startPosition + CACHE_SIZE);

	}

	public int getRowCount() {
		EntityManager entityManager = persistenceUnit.getEntityManager();
		return ((Long) entityManager.createQuery(ejbQlCount).getSingleResult()).intValue();
	}

	public int getColumnCount() {
		return propertyInfos.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object domainObject = getDomainValue(rowIndex);
		PropertyInfo propetyInfo = propertyInfos.get(columnIndex);
		return propetyInfo.getValue(domainObject);
	}

	@SuppressWarnings("unchecked")
	private List<Object> getItems(int from, int to) {
		// System.out.println("number of requests to the database " + counter++);
		StringBuffer ejbQl = new StringBuffer(SELECT);
		ejbQl.append(" ");
		ejbQl.append(selectClause);
		ejbQl.append(" ");
		ejbQl.append(FROM);
		ejbQl.append(" ");
		ejbQl.append(fromClause);

		StringBuffer orderClause = new StringBuffer();
		if (sortKeys != null) {
			for (SortKey sortKey : sortKeys) {
				if (sortKey.getSortOrder() != SortOrder.UNSORTED) {
					if (orderClause.length() > 0) {
						orderClause.append(",");
					}
					orderClause.append(" ");
					PropertyInfo propertyInfo = propertyInfos.get(sortKey.getColumn());
					String columnName = propertyInfo.getName();
					orderClause.append(variableName);
					orderClause.append(".");
					orderClause.append(columnName);
					if (sortKey.getSortOrder() != SortOrder.DESCENDING) {
						orderClause.append(" desc");
					}
				}
			}
		}
		if (orderClause.length() > 0) {
			ejbQl.append(" ");
			ejbQl.append(ORDER_BY);
			ejbQl.append(orderClause.toString());
		}
		EntityManager entityManager = persistenceUnit.getEntityManager();
		Query query = entityManager.createQuery(ejbQl.toString()).setMaxResults(to - from).setFirstResult(from);
		// add the cache
		List<Object> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public Object getDomainValue(int rowIndex) {
		// TODO also refresh if cache is older than 2 minutes?
		if ((rowIndex >= startPosition) && (rowIndex < (startPosition + CACHE_SIZE))) {
			// use the cache list that we already have retrieved
		} else {
			this.items = getItems(rowIndex, rowIndex + CACHE_SIZE);
			this.startPosition = rowIndex;
		}
		return items.get(rowIndex - startPosition);
	}

	@Override
	public String getColumnName(int column) {
		PropertyInfo propertyInfo= propertyInfos.get(column);
		return propertyInfo.getText();
	}

	@Override
	public void sort(List<? extends SortKey> sortKeys) {
		this.sortKeys = sortKeys;
		fireTableDataChanged();
	}


}