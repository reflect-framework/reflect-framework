package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.generic.util.JavaTypeConverter;
import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class TextFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		Class<?> propertyType = info.getPropertyInfo().getTypeInfo().getType();
		//TODO: change to: return true when there is a StringConverter that can convert to and from string
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
		return propertyType==String.class;
	}

	public static boolean isNumberType(Class<?> propertyType) {
		Class<?> complexType = JavaTypeConverter.getComplexType(propertyType);
		return Number.class.isAssignableFrom(complexType);
	}
	
	public static TextFieldModeType getTextFieldModeType(PropertyInfo propertyInfo) {
		return TextFieldModeType.getFor(propertyInfo);
	}

}
