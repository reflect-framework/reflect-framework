package nth.introspect.provider.domain.info.valuemodel.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import nth.introspect.provider.domain.info.property.FieldModeType;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.util.TypeUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class FieldModeValue implements ReadOnlyValueModel {

	private final String PASSWORD = "password";
	private final FieldModeType fieldModeType;

	public FieldModeValue(PropertyInfo propertyInfo) {
		// XXX consider if we can use the NumberConverterFactory
		Class<?> type = propertyInfo.getPropertyType().getType();
		type = TypeUtil.getComplexType(type);
		if (String.class.isAssignableFrom(type)) {
			if (propertyInfo.getName().toLowerCase().contains(PASSWORD)) {
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
		} else if (!type.getCanonicalName().startsWith("java")) {
			// Class<?> declaringClass =
			// propertyDescriptor.getReadMethod().getDeclaringClass();
			// String propertyName=propertyDescriptor.getName();
			// PropertyInfo propertyProperty =
			// NakedGuiConfig.getPropertyInfo(declaringClass, propertyName);
			// MultiplicityType multiplicity = propertyInfo.getMultiplicity();
			// if (multiplicity==MultiplicityType.ONE_TO_ONE) {
			// fieldModeType = FieldModeType.ONE_TO_ONE;
			// } else if (multiplicity==MultiplicityType.ONE_TO_MANY) {
			// fieldModeType = FieldModeType.ONE_TO_MANY;
			// } else {
			// StringBuffer message=new StringBuffer("Property ");
			// message.append(type.getCanonicalName());
			// message.append(".");
			// message.append(propertyDescriptor.getName());
			// message.append(" must have a Multiplicity annotation with a value ONE_TO_ONE or ONE_TO_MANY.");
			// throw new RuntimeException(message.toString());
			// }
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
