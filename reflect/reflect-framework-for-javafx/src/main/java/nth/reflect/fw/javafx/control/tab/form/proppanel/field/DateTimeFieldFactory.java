package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import java.util.Optional;

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
		Optional<DateTimeFieldModeType> mode = getDateTimeFieldModeType(propertyInfo);
		DateTimeField dateTimeField = new DateTimeField(propertyValueModel, mode.get());
		return dateTimeField;
	}

}