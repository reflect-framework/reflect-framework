package nth.reflect.fw.swing.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class TextFieldFactory extends nth.reflect.fw.gui.layer5provider.properyfield.factory.TextFieldFactory {

	@Override
	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel) {
		Class<?> propertyType = propertyValueModel.getPropertyInfo().getTypeInfo().getType();
		if (isStringType(propertyType)) {
			return createTextField(propertyValueModel);
		} else if (isNumberType(propertyType)) {
			return createNumericField(propertyValueModel);
		} else {
			return createCharField(propertyValueModel);
		}
	}

	private PropertyField createCharField(PropertyValueModel propertyValueModel) {
		return new UniversalTextField(propertyValueModel);
	}

	private PropertyField createNumericField(PropertyValueModel propertyValueModel) {
		return new NumericField(propertyValueModel);
	}

	private PropertyField createTextField(PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		TextFieldModeType textFieldMode = getTextFieldModeType(propertyInfo);
		switch (textFieldMode) {
		case PASSWORD:
			return new PasswordField(propertyValueModel);
		case MILTI_LINE:
			return new TextAreaField(propertyValueModel);
		default:
			return new TextField(propertyValueModel);
		}
	}

}
