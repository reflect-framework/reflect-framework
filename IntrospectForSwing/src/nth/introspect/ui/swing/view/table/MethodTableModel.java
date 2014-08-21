package nth.introspect.ui.swing.view.table;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.format.JavaFormatFactory;
import nth.introspect.provider.domain.format.PropertyInfoFormatFactory;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.property.TableOrderComparator;
import nth.introspect.provider.domain.info.property.TableVisibleFilter;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.tablemodel.DomainTableModel;
import nth.introspect.util.TypeUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class MethodTableModel extends AbstractTableModel implements
		DomainTableModel, Refreshable {

	// TODO move to Introspect package to replace domainTableModel?

	private static final long serialVersionUID = 605374068245011236L;
	private ArrayList<Object> list;
	private List<PropertyInfo> propertyInfos;
	private final ReadOnlyValueModel valueModel;
	private Format format;

	public MethodTableModel(ReadOnlyValueModel valueModel) {
		this.valueModel = valueModel;
		Class<?> domainClass = valueModel.getValueType();

		if (TypeUtil.isJavaType(domainClass)) {
			JavaFormatFactory formatFactory = new JavaFormatFactory();
			format = formatFactory.create(domainClass);
		} else {
			TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
			TableOrderComparator propertyInfoComparator = new TableOrderComparator();
			propertyInfos = Introspect.getDomainInfoProvider().getPropertyInfos(
					domainClass, propertyInfoFilter, propertyInfoComparator);
		}
		refresh();
	}

	@Override
	public int getColumnCount() {
		if (propertyInfos==null) {
			return 1;//table represents a java type
		}
		return propertyInfos.size();
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = list.get(rowIndex);
		if (propertyInfos==null) {
			//java type 
			return format.format(value);
		}
		//domain object
		Object domainObject = value;
		PropertyInfo propertyInfo = propertyInfos.get(columnIndex);
		return propertyInfo.getFormatedValue(domainObject);
	}

	@Override
	public String getColumnName(int column) {
		if (propertyInfos==null) {
			//java type
			return null;
		}
		// domain type
		PropertyInfo propertyInfo = propertyInfos.get(column);
		return propertyInfo.getText();
	}

	@Override
	public Object getDomainValue(int rowIndex) {//TODO rename to value?
		return list.get(rowIndex);
	}

	@Override
	public void refresh() {
		Collection<?> collection = null;
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
