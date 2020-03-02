package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.DateTimeFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class DateTimeFieldFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.DateTimeFieldFactory {

	@Override
	public PropertyField create(PropertyFieldFactoryInfo info) {
		PropertyValueModel propertyValueModel = info.getPropertyValueModel();
		PropertyInfo propertyInfo = info.getPropertyInfo();
		DateTimeFieldModeType mode = getDateTimeFieldModeType(propertyInfo).get();
		DateTimeField dateTimeField = new DateTimeField(propertyValueModel, mode);
		return dateTimeField;
	}

}
