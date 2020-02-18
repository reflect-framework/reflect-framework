package nth.reflect.fw.gui.component.table.info.cell;

import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class PropertyValueFormatter implements CellStringConverter {

	private final PropertyInfo propertyInfo;

	public PropertyValueFormatter(PropertyInfo propertyInfo) {
		this.propertyInfo = propertyInfo;
	}

	@Override
	public String getValue(Object domainObj) {
		String fromatedPropertyValue = propertyInfo.getFormatedValue(domainObj);
		return fromatedPropertyValue;
	}

}
