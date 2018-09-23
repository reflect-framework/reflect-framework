package nth.reflect.fw.javafx.control.view.form.proppanel.field;

import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.reflect.fw.ui.form.field.DateTimeMode;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.view.form.propertypanel.PropertyFieldFactory;

public class TimeFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormView formView, PropertyValueModel propertyValueModel) {
		return new DateTimeField(propertyValueModel, DateTimeMode.TIME);
	}

	@Override
	public boolean canCreateFor(PropertyValueModel propertyValueModel) {
		return propertyValueModel.getPropertyInfo().getFieldMode()==FieldModeType.TIME;
	}

}
