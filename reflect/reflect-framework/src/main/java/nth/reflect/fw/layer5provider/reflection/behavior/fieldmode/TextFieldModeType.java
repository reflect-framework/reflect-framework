package nth.reflect.fw.layer5provider.reflection.behavior.fieldmode;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public enum TextFieldModeType {
	SINGLE_LINE, PASSWORD, MILTI_LINE; // TODO RICH_TEXT;

	public static TextFieldModeType getFor(PropertyInfo propertyInfo) {
		Method getterMethod = propertyInfo.getGetterMethod();
		TextFieldMode textFieldModeFromAnnotation = getterMethod.getAnnotation(TextFieldMode.class);
		if (textFieldModeFromAnnotation == null) {
			return SINGLE_LINE;
		} else {
			return textFieldModeFromAnnotation.mode();
		}
	}

}
