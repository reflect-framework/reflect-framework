package nth.reflect.fw.gui.component.table.info.column;

import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class PropertyColumnInfo implements ColumnInfo {

	private final PropertyInfo propertyInfo;

	/**
	 * Only create {@link PropertyColumnInfo}'s for properties that have a string
	 * converter. e.g. all properties returned by:
	 * {@link DomainClassInfo#getPropertyInfosSortedAndVisibleInTable()}
	 * 
	 * @param propertyInfo
	 */
	public PropertyColumnInfo(PropertyInfo propertyInfo) {
		this.propertyInfo = propertyInfo;
	}

	@Override
	public String getName() {
		return propertyInfo.getDisplayName().getTranslation();
	}

	@Override
	public boolean hasName() {
		return true;
	}

	@Override
	public String toString(Object domainObject) {
		return propertyInfo.getStringValue(domainObject);
	}
}
