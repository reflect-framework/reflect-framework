package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.layer5provider.reflection.behavior.fieldmode.TextFieldModeType;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class TextFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		Class<?> propertyType = info.getPropertyInfo().getTypeInfo().getType();
		return isStringType(propertyType) || isNumberType(propertyType) || isCharacterType(propertyType);
	}

	protected boolean isCharacterType(Class<?> propertyType) {
		boolean propertyIsCharType = propertyType == char.class || propertyType == Character.class;
		return propertyIsCharType;
	}

	protected boolean isStringType(Class<?> propertyType) {
		return propertyType==String.class;
	}

	protected boolean isNumberType(Class<?> propertyType) {
		return Number.class.isInstance(propertyType);
	}
	
	protected TextFieldModeType getTextFieldModeType(PropertyInfo propertyInfo) {
		return TextFieldModeType.getFor(propertyInfo);
	}

}
