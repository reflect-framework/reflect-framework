package nth.introspect.provider.domain.info.property;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.provider.domain.format.PropertyInfoFormatFactory;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.IntrospectionInfo;
import nth.introspect.provider.domain.info.type.PropertyType;
import nth.introspect.provider.domain.info.type.TypeCategory;
import nth.introspect.provider.domain.info.valuemodel.factories.AnnotationValueModelFactory;
import nth.introspect.provider.domain.info.valuemodel.factories.MethodValueModelFactory;
import nth.introspect.provider.domain.info.valuemodel.impl.FieldModeValue;
import nth.introspect.provider.domain.info.valuemodel.impl.SimpleValue;
import nth.introspect.provider.domain.info.valuemodel.impl.TextValue;
import nth.introspect.util.StringUtil;
import nth.introspect.util.TypeUtil;
import nth.introspect.valuemodel.ValueModels;

/**
 * Provides information on a bean property.<br>
 * This class is inspired by the PropertyDiscriptor class, which I do not use
 * because it is not implemented by Android
 * 
 * @author nilsth
 * 
 */
public class PropertyInfo implements IntrospectionInfo {
	// extends PropertyDescriptor {
	static final String IS_PREFIX = "is";
	static final String GET_PREFIX = "get";
	private static final String SET_PREFIX = "set";

	private ValueModels valueModels;
	public final static String TEXT = "text";
	public final static String DESCRIPTION = "description";
	public final static String ACCESS_KEY = "accessKey";
	public final static String ORDER_IN_FORM = "orderInForm";
	public final static String ORDER_IN_TABLE = "orderInTable";
	public final static String VISIBLE_IN_TABLE = "visibleInTable";
	public final static String COLUMN_WIDTH = "columnWidth";
	public final static int COLUMN_WIDTH_DEFAULT = -1;
	public final static String VISIBLE_IN_FORM = "visibleInForm";
	public final static String ENABLED = "enabled";
	public final static String VALIDATION = "validation";
	public final static String FIELD_MODE = "fieldMode";
	public static final String FORMAT = "format";
	public static final String MULTIPLICITY = "multiplicity";
	// TODO public final String UNIT_OF_MEASUREMENT = "unitOfMeasurement";
	public final String[] ANNOTATION_NAMES = new String[] { ORDER_IN_FORM,
			ORDER_IN_TABLE, VISIBLE_IN_FORM, VISIBLE_IN_TABLE, COLUMN_WIDTH,
			ENABLED, RETURN_CLASS, FORMAT, MULTIPLICITY, FIELD_MODE };
	public final static String[] METHOD_NAMES = new String[] { VISIBLE_IN_FORM,
			ENABLED, VALIDATION };
	public static final String RETURN_CLASS = "returnClass";

	private final String name;
	private final String namePath;
	private final Method readMethod;
	private final Method writeMethod;
	private final PropertyType propertyType;
	private Format format;

	public PropertyInfo(DomainInfoProvider domainInfoProvider, Method readMethod) {
		if (readMethod.getReturnType() == Void.class) {
			throw new RuntimeException("Method: "
					+ readMethod.getClass().getCanonicalName() + "."
					+ readMethod.getName() + " is not a getter method");
		}
		if (readMethod.getParameterTypes().length > 0) {
			throw new RuntimeException("Method: "
					+ readMethod.getClass().getCanonicalName() + "."
					+ readMethod.getName() + " may not contain a parameter");
		}

		this.name = getName(readMethod);
		this.namePath = getNamePath(readMethod, name);
		this.propertyType = new PropertyType(readMethod);
		this.readMethod = readMethod;
		this.writeMethod = getWiteMethod(readMethod, name,
				propertyType.getType());

		valueModels = new ValueModels();

		// create default value getters
		valueModels.put(TEXT, new TextValue(this, TEXT));
		valueModels.put(DESCRIPTION, new TextValue(this, DESCRIPTION));
		// valueModels.put(ACCESS_KEY, new AccessKeyValue(this, TEXT));

		// those properties that overwrite the order value come first
		valueModels.put(ORDER_IN_FORM, new SimpleValue(Integer.MAX_VALUE));
		// those properties that overwrite the order value come first
		valueModels.put(ORDER_IN_TABLE, new SimpleValue(Integer.MAX_VALUE));
		valueModels.put(VISIBLE_IN_FORM, new SimpleValue(true));
		valueModels.put(VISIBLE_IN_TABLE, new SimpleValue(true));
		valueModels.put(COLUMN_WIDTH, new SimpleValue(COLUMN_WIDTH_DEFAULT));
		valueModels.put(ENABLED, new SimpleValue(true));
		valueModels.put(FIELD_MODE, new FieldModeValue(this));

		// create value getters from annotations
		valueModels.putAll(AnnotationValueModelFactory.create(this,
				ANNOTATION_NAMES));

		// create method value getters
		valueModels.putAll(MethodValueModelFactory.create(this, METHOD_NAMES));

		// create xml value getters
		// TODO valueModels.putAll(XmlValueModelFactory.create( this));

		// override VISIBLE_IN_TABLE (collections may not be visible in a table
		// column)
		if (TypeCategory.COLLECTION_TYPE == getPropertyType().getTypeCategory()) {
			valueModels.put(VISIBLE_IN_TABLE, new SimpleValue(false));
		}

		// override ENABLED (if there is no write method, the property is read
		// only and the property must be disabled)
		if (writeMethod == null) {
			// there is no write method, so disable property (read only)
			valueModels.put(ENABLED, new SimpleValue(false));
		}

		// overridde FIELD_MODE for Date and or time depending on format pattern
		String formatPattern = getFormatPattern();
		if (formatPattern != null && getFieldMode() == FieldModeType.DATE) {
			boolean hasDate = false;
			boolean hasTime = false;
			if (StringUtil.containsCharacter(formatPattern, "GyYMwWDdFEu")) {
				hasDate = true;
			}
			if (StringUtil.containsCharacter(formatPattern, "aHkKhmsSzZX")) {
				hasTime = true;
			}
			if (hasDate && hasTime) {
				valueModels.put(FIELD_MODE, new SimpleValue(
						FieldModeType.DATE_TIME));
			} else if (hasDate) {
				valueModels
						.put(FIELD_MODE, new SimpleValue(FieldModeType.DATE));
			} else if (hasTime) {
				valueModels
						.put(FIELD_MODE, new SimpleValue(FieldModeType.TIME));
			}
		}

		//create formater
		format = new PropertyInfoFormatFactory(domainInfoProvider).create(this);
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	private Method getWiteMethod(Method readMethod, String name,
			Class<?> propertyClass) {
		Class<?> methodOwner = readMethod.getDeclaringClass();
		StringBuffer writeMethodName = new StringBuffer();
		writeMethodName.append(SET_PREFIX);
		writeMethodName.append(Character.toUpperCase(name.charAt(0)));
		writeMethodName.append(name.substring(1));
		try {
			Method writeMethod = methodOwner.getMethod(
					writeMethodName.toString(), propertyClass);
			return writeMethod;
		} catch (Exception e1) {
			try {
				// try to get writeMethod with a simple type parameter
				Class<?> simplePropertyClass = TypeUtil
						.getSimpleType(propertyClass);
				Method writeMethod = methodOwner.getMethod(
						writeMethodName.toString(), simplePropertyClass);
				return writeMethod;
			} catch (Exception e2) {
				// No proper write method found: set enabled=false!
				return null;
			}
		}
	}

	private String getNamePath(Method readMethod, String name) {
		StringBuffer namePath = new StringBuffer();
		namePath.append(readMethod.getDeclaringClass().getCanonicalName());
		namePath.append(".");
		namePath.append(name);
		return namePath.toString();
	}

	private String getName(Method readMethod) {
		String readMethodName = readMethod.getName();
		if (readMethodName.startsWith(IS_PREFIX)) {
			StringBuffer name = new StringBuffer();
			name.append(Character.toLowerCase(readMethodName.charAt(IS_PREFIX
					.length())));
			name.append(readMethodName.substring(IS_PREFIX.length() + 1));
			return name.toString();
		} else if (readMethodName.startsWith(GET_PREFIX)) {
			StringBuffer name = new StringBuffer();
			name.append(Character.toLowerCase(readMethodName.charAt(GET_PREFIX
					.length())));
			name.append(readMethodName.substring(GET_PREFIX.length() + 1));
			return name.toString();
		} else {
			throw new RuntimeException("Method: "
					+ readMethod.getClass().getCanonicalName() + "."
					+ readMethod.getName() + " is not a getter method");
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public String getNamePath() {
		return namePath;
	}

	public Method getReadMethod() {
		return readMethod;
	}

	public Method getWriteMethod() {
		return writeMethod;
	}

	public String getText() {
		return valueModels.getStringValue(TEXT);
	}

	public String getDescription() {
		return valueModels.getStringValue(DESCRIPTION);
	}

	public Integer getOrderInForm() {
		return valueModels.getIntegerValue(ORDER_IN_FORM);
	}

	public Integer getOrderInTable() {
		return valueModels.getIntegerValue(ORDER_IN_TABLE);
	}

	public int getColumnWidth() {
		return valueModels.getIntegerValue(COLUMN_WIDTH);
	}

	public Boolean isVisibleInForm(Object domainObject) {
		return valueModels.getBooleanValue(VISIBLE_IN_FORM, domainObject);
	}

	public Boolean isVisibleInTable() {
		return valueModels.getBooleanValue(VISIBLE_IN_TABLE);
	}

	public Boolean isEnabled(Object domainObject) {
		return valueModels.getBooleanValue(ENABLED, domainObject);
	}

	public String getFormatPattern() {
		return valueModels.getStringValue(FORMAT);
	}

	public FieldModeType getFieldMode() {
		return (FieldModeType) valueModels.getValue(FIELD_MODE);
	}

	public void setValue(Object domainObject, Object value) {
		if (!isEnabled(domainObject)) {
			throw new RuntimeException("Could not set value of property: "
					+ namePath + " when it is disabled or read only");
		}
		try {
			writeMethod.invoke(domainObject, new Object[] { value });
		} catch (Exception e) {
			if (value == null) {
				throw new RuntimeException("Could not set value of property: "
						+ namePath + " with value: null", e);
			} else {
				throw new RuntimeException("Could not set value of property: "
						+ namePath + " with value: " + value + " of type"
						+ value.getClass().getCanonicalName(), e);
			}
		}
	}

	public Object getValue(Object introspectedObject) {
		try {
			return getReadMethod().invoke(introspectedObject, new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException("Could not read value of property: "
					+ namePath, e);
		}
	}

	// TODO getValueAsText(domainObject), while using formatPattern or enum
	// converter

	public ValueModels getValueModels() {
		return valueModels;
	}

	@Override
	public String toString() {
		return namePath;
	}

	/**
	 * @deprecated use getFormatter.setValue()
	 * @param domainObject
	 * @param stringValue
	 */
	public void setValueFromString(Object domainObject, String stringValue) {
		Object value = null;
		Class<?> propertyClass = propertyType.getType();
		if (AtomicInteger.class.isAssignableFrom(propertyClass)) {
			value = new AtomicInteger(Integer.parseInt(stringValue));
		} else if (AtomicLong.class.isAssignableFrom(propertyClass)) {
			value = new AtomicLong(Long.parseLong(stringValue));
		} else if (BigDecimal.class.isAssignableFrom(propertyClass)) {
			value = new BigDecimal(stringValue);
		} else if (BigInteger.class.isAssignableFrom(propertyClass)) {
			value = new BigInteger(stringValue);
		} else if (Byte.class.isAssignableFrom(propertyClass)) {
			value = new Byte(stringValue);
		} else if (Double.class.isAssignableFrom(propertyClass)) {
			value = new Double(stringValue);
		} else if (Float.class.isAssignableFrom(propertyClass)) {
			value = new Float(stringValue);
		} else if (Integer.class.isAssignableFrom(propertyClass)) {
			value = new Integer(stringValue);
		} else if (Long.class.isAssignableFrom(propertyClass)) {
			value = new Long(stringValue);
		} else if (Short.class.isAssignableFrom(propertyClass)) {
			value = new Short(stringValue);
		} else if (String.class.isAssignableFrom(propertyClass)) {
			value = stringValue;
		} else if (Boolean.class.isAssignableFrom(propertyClass)) {
			value = new Boolean(stringValue);
		} else {
			throw new IllegalArgumentException("Property type:"
					+ propertyClass.getSimpleName()
					+ " is not supported for property:" + namePath);
		}
		setValue(domainObject, value);
	}

	public boolean isReadOnly() {
		return writeMethod == null;
	}

	public Format getFormat() {
		return format;
	}

	public String getFormatedValue(Object introspectedObject) {
		Object value = getValue(introspectedObject);
		if (value == null) {
			return "";
		} else {
			return getFormat().format(value);
		}
	}
}
