package nth.introsepect.ui.swing.view.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.property.TableOrderComparator;
import nth.introspect.provider.domain.info.property.TableVisibleFilter;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.tablemodel.DomainTableModel;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class MethodTableModel extends AbstractTableModel implements DomainTableModel, Refreshable {

	//TODO move to Introspect package to replace domainTableModel?
	
	private static final long serialVersionUID = 605374068245011236L;
	private ArrayList<Object> list;
	private List<PropertyInfo> propertyInfos;
	private final ReadOnlyValueModel valueModel;

	public MethodTableModel(ReadOnlyValueModel valueModel) {
		this.valueModel = valueModel;
		Class<?> domainClass = valueModel.getValueType();
		TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
		TableOrderComparator propertyInfoComparator = new TableOrderComparator();
		propertyInfos = Introspect.getDomainProvider().getPropertyInfos(domainClass, propertyInfoFilter, propertyInfoComparator);
		refresh();
	}

	@Override
	public int getColumnCount() {
		return propertyInfos.size();
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object domainObject = list.get(rowIndex);
		PropertyInfo propertyInfo = propertyInfos.get(columnIndex);
		return propertyInfo.getFormatedValue(domainObject);
	}

	@Override
	public String getColumnName(int column) {
		PropertyInfo propertyInfo = propertyInfos.get(column);
		return propertyInfo.getText();
	}

	@Override
	public Object getDomainValue(int rowIndex) {
		return list.get(rowIndex);
	}

	@Override
	public void refresh() {
		Collection<?> collection=null;
		try {
			collection = (Collection<?>) valueModel.getValue();
			fireTableDataChanged();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		list = new ArrayList<Object>(collection);
	}

	public int getRow(Object domainObject) {
		return list.indexOf(domainObject);
	}

}
