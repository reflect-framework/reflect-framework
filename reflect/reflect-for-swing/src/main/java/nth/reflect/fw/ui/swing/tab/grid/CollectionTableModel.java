package nth.reflect.fw.ui.swing.tab.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class CollectionTableModel extends AbstractTableModel implements DomainTableModel {

	private static final long serialVersionUID = 1903326845833688508L;
	private final List<PropertyInfo> propertyInfos;
	private List<Object> list;

	public CollectionTableModel(ReflectionProvider reflectionProvider, Collection<?> collection, Class<?> domainClass) {
		if (collection == null) {
			list = new ArrayList<Object>();
		} else {
			list = new ArrayList<Object>(collection);
			// convert collection to an array list to safegard the sequance of
			// objects
		}
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainClass);
		propertyInfos = domainClassInfo.getPropertyInfosSortedAndVisibleInTable();
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
		return propertyInfo.getStringValue(domainObject);
	}

	@Override
	public String getColumnName(int column) {
		PropertyInfo propertyInfo = propertyInfos.get(column);
		return propertyInfo.getDisplayName().getTranslation();
	}

	@Override
	public Object getDomainValue(int rowIndex) {
		return list.get(rowIndex);
	}

}
