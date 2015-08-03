package nth.introspect.layer5provider.reflection.behavior.fieldmode;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.info.valuemodel.impl.SimpleValue;

/**
 * <p>
 * Gets the mode from the {@link TextFieldMode} annotation and determines the
 * {@link FieldModeType} depending on the {@link DomainObjectProperty} type
 * </p>
 * 
 * @author nilsth
 *
 */
public class FieldModeFactory {
//TODO Make FieldMode classes obsolete. The userLayer should contain fields (registrated by the IntrospectApp). These Field components should indicate if they can represent a property (e.g. with a boolean canRepresent(ProprertyInfomethod)
	public static FieldModeType create(Method getterMethod, String formatPattern) {
		Class<?> type = getterMethod.getReturnType();
		type = TypeUtil.getComplexType(type);
		if (String.class.isAssignableFrom(type)) {
			return getFieldModeForString(getterMethod);
		} else if (Character.class.isAssignableFrom(type)) {
			return FieldModeType.CHAR;
		} else if (Date.class.isAssignableFrom(type)) {
			return getFieldModeTypeForDateTime(formatPattern);
		} else if (Calendar.class.isAssignableFrom(type)) {
			return FieldModeType.DATE;
		} else if (Enum.class.isAssignableFrom(type)) {
			return FieldModeType.COMBO_BOX;
		} else if (Number.class.isAssignableFrom(type)) {
			return FieldModeType.NUMBER;
		} else if (Boolean.class.isAssignableFrom(type)) {
			return FieldModeType.CHECK_BOX;
		} else if (TypeUtil.isDomainType(type)) {
			return FieldModeType.ONE_TO_ONE_OR_MANY;
		} else if (Collection.class.isAssignableFrom(type)) {
			return FieldModeType.MANY_TO_ONE_OR_MANY;
		} else {
			return FieldModeType.UNKNOWN;
		}
	}

	private static FieldModeType getFieldModeForString(Method getterMethod) {
		TextFieldMode textFieldMode = getterMethod
				.getAnnotation(TextFieldMode.class);
		if (textFieldMode == null) {
			return FieldModeType.TEXT;
		} else {
			TextFieldModeType textFieldModeType = textFieldMode.mode();
			return TextFieldModeType.toFieldMode(textFieldModeType);
		}
	}

	private static FieldModeType getFieldModeTypeForDateTime(
			String formatPattern) {
		boolean hasDate = false;
		boolean hasTime = false;
		if (StringUtil.containsCharacter(formatPattern, "GyYMwWDdFEu")) {
			hasDate = true;
		}
		if (StringUtil.containsCharacter(formatPattern, "aHkKhmsSzZX")) {
			hasTime = true;
		}
		if (hasDate && hasTime) {
			return FieldModeType.DATE_TIME;
		} else if (hasDate) {
			return FieldModeType.DATE;
		}
		return FieldModeType.TIME;

	}
}
