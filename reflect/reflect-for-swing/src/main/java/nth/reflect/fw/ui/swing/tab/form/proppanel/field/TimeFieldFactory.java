package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.reflect.fw.ui.component.tab.form.DateTimeMode;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldFactory;
import nth.reflect.fw.ui.component.tab.form.valuemodel.PropertyValueModel;

public class TimeFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		return new DateTimeField(propertyValueModel, DateTimeMode.TIME);
	}

	@Override
	public boolean canCreateFor(PropertyValueModel propertyValueModel) {
		return propertyValueModel.getPropertyInfo().getFieldMode() == FieldModeType.TIME;
	}

}
