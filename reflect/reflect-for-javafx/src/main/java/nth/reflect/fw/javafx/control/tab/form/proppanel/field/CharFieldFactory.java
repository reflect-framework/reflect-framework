package nth.reflect.fw.javafx.control.tab.form.proppanel.field;

import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldFactory;
import nth.reflect.fw.ui.component.tab.form.valuemodel.PropertyValueModel;

public class CharFieldFactory implements PropertyFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		TextFieldLimitedLength textFieldLimitedLength = new TextFieldLimitedLength(propertyValueModel);
		textFieldLimitedLength.setMaxLength(1);
		return textFieldLimitedLength;
	}

	@Override
	public boolean canCreateFor(PropertyValueModel propertyValueModel) {
		return propertyValueModel.getPropertyInfo().getFieldMode()==FieldModeType.CHAR;
	}

}
