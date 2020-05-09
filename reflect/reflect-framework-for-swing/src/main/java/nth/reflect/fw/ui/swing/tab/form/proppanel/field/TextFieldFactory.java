package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class TextFieldFactory
		extends nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.TextFieldFactory {

	@Override
	public PropertyField create(PropertyFieldFactoryInfo info) {
		Class<?> propertyType = info.getPropertyInfo().getTypeInfo().getType();
		if (isStringType(propertyType)) {
			return createTextField(info);
		} else if (isNumberType(propertyType)) {
			return createNumericField(info);
		} else {
			return createCharField(info);
		}
	}

	private PropertyField createCharField(PropertyFieldFactoryInfo info) {
		PropertyValueModel propertyValueModel = info.getPropertyValueModel();
		return new UniversalTextField(propertyValueModel);
	}

	private PropertyField createNumericField(PropertyFieldFactoryInfo info) {
		PropertyValueModel propertyValueModel = info.getPropertyValueModel();
		return new NumericField(propertyValueModel);
	}

	private PropertyField createTextField(PropertyFieldFactoryInfo info) {
		PropertyInfo propertyInfo = info.getPropertyInfo();
		TextFieldModeType textFieldMode = getTextFieldModeType(propertyInfo);
		PropertyValueModel propertyValueModel = info.getPropertyValueModel();
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
