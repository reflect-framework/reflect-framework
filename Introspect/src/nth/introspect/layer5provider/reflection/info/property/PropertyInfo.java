package nth.introspect.layer5provider.reflection.info.property;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.generic.valuemodel.ValueModels;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.introspect.layer5provider.reflection.behavior.fieldmode.FieldModeFactory;
import nth.introspect.layer5provider.reflection.behavior.fieldmode.FieldModeType;
import nth.introspect.layer5provider.reflection.behavior.format.FormatFactory;
import nth.introspect.layer5provider.reflection.behavior.order.OrderFactory;
import nth.introspect.layer5provider.reflection.info.NameInfo;
import nth.introspect.layer5provider.reflection.info.type.PropertyType;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;
import nth.introspect.layer5provider.reflection.info.valuemodel.factories.AnnotationValueModelFactory;
import nth.introspect.layer5provider.reflection.info.valuemodel.factories.MethodValueModelFactory;
import nth.introspect.layer5provider.reflection.info.valuemodel.impl.SimpleValue;

/**
 * Provides information on a bean property.<br>
 * This class is inspired by the PropertyDiscriptor class, which I do not use
 * because it is not implemented by Android
 * 
 * @author nilsth
 * 
 */
public class PropertyInfo implements NameInfo {
	// extends PropertyDescriptor {
	static final String IS_PREFIX = "is";
	static final String GET_PREFIX = "get";
	private static final String SET_PREFIX = "set";

	private ValueModels valueModels;
	public final static String VISIBLE_IN_TABLE = "visibleInTable";
	public final static String VISIBLE_IN_FORM = "visibleInForm";
	public final static String ENABLED = "enabled";
	public final static String VALIDATION = "validation";
	public static final String VALUES = "values";
	public final String[] ANNOTATION_NAMES = new String[] { VISIBLE_IN_FORM, VISIBLE_IN_TABLE, 	ENABLED, RETURN_CLASS };
	public final static String[] METHOD_NAMES = new String[] { VISIBLE_IN_FORM,
			ENABLED, VALIDATION, VALUES };
	public static final String RETURN_CLASS = "returnClass";

	private final String simpleName;
	private final String canonicalName;
	private final Method getterMethod;
	private final Method setterMethod;
	private final DisplayNameModel displayNameModel;
	private final DescriptionModel descriptionModel;
	private final PropertyType propertyType;
	private final double order;
	private final FieldModeType fieldMode;
	private final String formatPattern;
	private final Format format;


	

	public PropertyInfo(ReflectionProvider reflectionProvider, LanguageProvider languageProvider,  Method getterMethod) {
		checkGetterMethodReturnType(getterMethod);
		checkGetterMethodHasNoParameter(getterMethod);

		this.simpleName = getSimpleName(getterMethod);
		this.canonicalName = getCanonicalName(getterMethod, simpleName);
		this.displayNameModel=new DisplayNameModel(languageProvider, getterMethod, simpleName, canonicalName);
		this.descriptionModel=new DescriptionModel(languageProvider, getterMethod, simpleName, canonicalName);
		this.propertyType = new PropertyType(getterMethod);
		this.getterMethod = getterMethod;
		this.setterMethod = getSetterMethod(getterMethod, simpleName,
				propertyType.getType());
		this.order=OrderFactory.create(getterMethod);
		FormatFactory formatFactory = new FormatFactory(reflectionProvider, languageProvider,getterMethod);
		this.format = formatFactory.getFormat();
		this.formatPattern=formatFactory.getFormatPattern();
		this.fieldMode=FieldModeFactory.create(getterMethod, formatPattern);
		valueModels = new ValueModels();

		// create default value getters

		valueModels.put(VISIBLE_IN_FORM, new SimpleValue(true));
		valueModels.put(VISIBLE_IN_TABLE, new SimpleValue(true));
		valueModels.put(ENABLED, new SimpleValue(true));

		// create value getters from annotations
		valueModels.putAll(AnnotationValueModelFactory.create(this,
				ANNOTATION_NAMES));

		// create method value getters
		valueModels.putAll(MethodValueModelFactory.create(this, METHOD_NAMES));

		// override VISIBLE_IN_TABLE (collections may not be visible in a table
		// column)
		if (TypeCategory.COLLECTION_TYPE == getPropertyType().getTypeCategory()) {
			valueModels.put(VISIBLE_IN_TABLE, new SimpleValue(false));
		}

		// override ENABLED (if there is no write method, the property is read
		// only and the property must be disabled)
		if (setterMethod == null) {
			// there is no write method, so disable property (read only)
			valueModels.put(ENABLED, new SimpleValue(false));
		}

		
		
//	TODO!!!	if (valueModels.containsKey(VALUES)) {
//			valueModels
//			.put(FIELD_MODE, new SimpleValue(FieldModeType.COMBO_BOX));			
//		}

	}

	private void checkGetterMethodHasNoParameter(Method getterMethod) {
		if (getterMethod.getParameterTypes().length > 0) {
			throw new RuntimeException("Method: "
					+ getterMethod.getClass().getCanonicalName() + "."
					+ getterMethod.getName() + " may not contain a parameter");
		}
	}

	private void checkGetterMethodReturnType(Method getterMethod) {
		if (getterMethod.getReturnType() == Void.class) {
			throw new RuntimeException("Method: "
					+ getterMethod.getClass().getCanonicalName() + "."
					+ getterMethod.getName() + " is not a getter method");
		}
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	private Method getSetterMethod(Method getterMethod, String name,
			Class<?> propertyClass) {
		Class<?> methodOwner = getterMethod.getDeclaringClass();
		StringBuffer getterMethodName = new StringBuffer();
		getterMethodName.append(SET_PREFIX);
		getterMethodName.append(Character.toUpperCase(name.charAt(0)));
		getterMethodName.append(name.substring(1));
		try {
			Method writeMethod = methodOwner.getMethod(
					getterMethodName.toString(), propertyClass);
			return writeMethod;
		} catch (Exception e1) {
			try {
				// try to get setterMethod with a simple type parameter
				Class<?> simplePropertyClass = TypeUtil
						.getSimpleType(propertyClass);
				Method writeMethod = methodOwner.getMethod(
						getterMethodName.toString(), simplePropertyClass);
				return writeMethod;
			} catch (Exception e2) {
				// No proper setter method found: set enabled=false!
				return null;
			}
		}
	}

	private String getCanonicalName(Method readMethod, String name) {
		StringBuffer conicalName = new StringBuffer();
		conicalName.append(readMethod.getDeclaringClass().getCanonicalName());
		conicalName.append(".");
		conicalName.append(name);
		return conicalName.toString();
	}

	private String getSimpleName(Method readMethod) {
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

	public String getSimpleName() {
		return simpleName;
	}


	@Override
	public String getCanonicalName() {
		return canonicalName;
	}

	public Method getGetterMethod() {
		return getterMethod;
	}

	public Method getSetterMethod() {
		return setterMethod;
	}

	public String getDisplayName() {
		return displayNameModel.getText();
	}

	public String getDescription() {
		return descriptionModel.getText();
	}

	public double getOrder() {
		return order;
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

	public FieldModeType getFieldMode() {
		return fieldMode;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getValues(Object domainObject) {
		return (List<Object>) valueModels.getValue(VALUES, domainObject);
	}
	
	public void setValue(Object domainObject, Object value) {
		if (!isEnabled(domainObject)) {
			throw new RuntimeException("Could not set value of property: "
					+ canonicalName + " when it is disabled or read only");
		}
		try {
			setterMethod.invoke(domainObject, new Object[] { value });
		} catch (Exception e) {
			if (value == null) {
				throw new RuntimeException("Could not set value of property: "
						+ canonicalName + " with value: null", e);
			} else {
				throw new RuntimeException("Could not set value of property: "
						+ canonicalName + " with value: " + value + " of type"
						+ value.getClass().getCanonicalName(), e);
			}
		}
	}

	public Object getValue(Object obj) {
		try {
			return getGetterMethod().invoke(obj, new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException("Could not read value of property: "
					+ canonicalName, e);
		}
	}

	// TODO getValueAsText(domainObject), while using formatPattern or enum
	// converter


	@Override
	public String toString() {
		return canonicalName;
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
					+ " is not supported for property:" + canonicalName);
		}
		setValue(domainObject, value);
	}

	public boolean isReadOnly() {
		return setterMethod == null;
	}

	public String getFormatPattern() {
		return formatPattern;
	}
	
	public Format getFormat() {
		return format;
	}

	public String getFormatedValue(Object obj) {
		Object value = getValue(obj);
		if (value == null) {
			return "";
		} else {
			return getFormat().format(value);
		}
	}
}
