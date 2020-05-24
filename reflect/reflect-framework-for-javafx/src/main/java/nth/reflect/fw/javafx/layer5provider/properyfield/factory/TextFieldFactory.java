package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import java.util.Optional;

import nth.reflect.fw.generic.util.PrimitiveType;
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

	public static boolean isCharacterType(Class<?> propertyType) {
		boolean propertyIsCharType = propertyType == char.class || propertyType == Character.class;
		return propertyIsCharType;
	}

	public static boolean isStringType(Class<?> propertyType) {
		return propertyType == String.class;
	}

	public static boolean isNumberType(Class<?> propertyType) {
		Optional<Class<?>> primitiveWrapperType = PrimitiveType.primitiveToWrapper(propertyType);
		if (primitiveWrapperType.isPresent()) {
			propertyType = primitiveWrapperType.get();
		}
		return Number.class.isAssignableFrom(propertyType);
	}

	private PropertyField createCharField(PropertyValueModel propertyValueModel) {
		TextFieldLimitedLength textFieldLimitedLength = new TextFieldLimitedLength(propertyValueModel);
		textFieldLimitedLength.setMaxLength(1);
		return textFieldLimitedLength;
	}

	private PropertyField createNumericField(PropertyValueModel propertyValueModel) {
		return new NumericField(propertyValueModel);
	}

	private PropertyField createTextField(PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		TextFieldModeType textFieldMode = getTextFieldModeType(propertyInfo);
		switch (textFieldMode) {
		case PASSWORD:
			return new PassWordField(propertyValueModel);
		case MILTI_LINE:
			return new TextAreaField(propertyValueModel);
		default:
			return new TextField(propertyValueModel);
		}
	}

}
