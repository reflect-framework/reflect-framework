package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import java.util.Optional;

import nth.reflect.fw.generic.util.PrimitiveType;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class TextFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Class<?> propertyType = propertyInfo.getTypeInfo().getType();
		// TODO: change to: return true when there is a StringConverter that can convert
		// to and from string
		boolean stringType = isStringType(propertyType);
		boolean numberType = isNumberType(propertyType);
		boolean characterType = isCharacterType(propertyType);
		return stringType || numberType || characterType;
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

	public static TextFieldModeType getTextFieldModeType(PropertyInfo propertyInfo) {
		return TextFieldModeType.getFor(propertyInfo);
	}

}
