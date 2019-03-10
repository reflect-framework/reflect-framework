package nth.reflect.fw.gui.component.table.info.cell;

import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class CellValueFactoryForObjectPropertyValue implements CellValueFactory {

	private final PropertyInfo propertyInfo;

	public CellValueFactoryForObjectPropertyValue(PropertyInfo propertyInfo) {
		this.propertyInfo = propertyInfo;
	}

	@Override
	public String getValue(Object domainObj) {
		Object propertyValue = propertyInfo.getValue(domainObj);
		String fromatedPropertyValue = propertyInfo.getFormatedValue(propertyValue);
		return fromatedPropertyValue;
	}

}
