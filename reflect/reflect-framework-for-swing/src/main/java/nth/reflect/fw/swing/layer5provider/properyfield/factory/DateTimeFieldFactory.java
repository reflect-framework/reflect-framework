package nth.reflect.fw.swing.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class DateTimeFieldFactory extends nth.reflect.fw.gui.layer5provider.properyfield.factory.DateTimeFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		DateTimeFieldModeType mode = getDateTimeFieldModeType(propertyInfo).get();
		DateTimeField dateTimeField = new DateTimeField(propertyValueModel, mode);
		return dateTimeField;
	}

}
