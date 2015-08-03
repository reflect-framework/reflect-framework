package nth.introspect.layer5provider.reflection.info.valuemodel.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public class FieldModeValue implements ReadOnlyValueModel {

	private final String PASSWORD = "password";
	private final FieldModeType fieldModeType;

	public FieldModeValue(PropertyInfo propertyInfo) {
		// XXX consider if we can use the NumberConverterFactory
		Class<?> type = propertyInfo.getPropertyType().getType();
		type = TypeUtil.getComplexType(type);
		if (String.class.isAssignableFrom(type)) {
			if (propertyInfo.getSimpleName().toLowerCase().contains(PASSWORD)) {
				fieldModeType = FieldModeType.PASSWORD;
			} else {
				fieldModeType = FieldModeType.TEXT;
			}
		} else if (Character.class.isAssignableFrom(type)) {
			fieldModeType = FieldModeType.CHAR;
		} else if (Date.class.isAssignableFrom(type)) {
			fieldModeType = FieldModeType.DATE;
		} else if (Calendar.class.isAssignableFrom(type)) {
			fieldModeType = FieldModeType.DATE;
		} else if (Enum.class.isAssignableFrom(type)) {
			fieldModeType = FieldModeType.COMBO_BOX;
		} else if (Number.class.isAssignableFrom(type)) {
			fieldModeType = FieldModeType.NUMBER;
		} else if (Boolean.class.isAssignableFrom(type)) {
			fieldModeType = FieldModeType.CHECK_BOX;
		} else if (TypeUtil.isDomainType(type)) {
			fieldModeType = FieldModeType.ONE_TO_ONE_OR_MANY;
		} else if (Collection.class.isAssignableFrom(type)) {
			fieldModeType = FieldModeType.MANY_TO_ONE_OR_MANY;
		} else {
			fieldModeType = FieldModeType.UNKNOWN;
		}
	}

	public Object getValue() {
		return fieldModeType;
	}

	@Override
	public String toString() {
		return getValue().toString();
	}

	@Override
	public Class<?> getValueType() {
		return FieldModeType.class;
	}

	@Override
	public boolean canGetValue() {
		return true;
	}

}
