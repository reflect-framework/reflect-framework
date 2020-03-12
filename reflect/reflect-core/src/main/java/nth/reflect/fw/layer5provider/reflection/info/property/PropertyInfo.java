package nth.reflect.fw.layer5provider.reflection.info.property;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.generic.util.PrimitiveType;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledModel;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatPatternFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenModel;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsModel;
import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.order.OrderFactory;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfoFactory;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

/**
 * Provides information on a {@link DomainObject} property.<br>
 * This class is inspired by the PropertyDiscriptor class, which can not be used
 * because it is not implemented by Android
 * 
 * @author nilsth
 * 
 */
public class PropertyInfo implements NameInfo {
	public static final String IS_PREFIX = "is";
	public static final String GET_PREFIX = "get";
	private static final String SET_PREFIX = "set";

	private final String simpleName;
	private final String canonicalName;
	private final Method getterMethod;
	private final Method setterMethod;
	private final DisplayNameModel displayNameModel;
	private final DescriptionModel descriptionModel;
	private final TypeInfo typeInfo;
	private final double order;
	private final String formatPattern;
	private final DisabledModel disabledModel;
	private final HiddenModel hiddenModel;
	private final OptionsModel optionModel;
	private final List<ActionMethodInfo> actionMethodInfos;
	private final Optional<StringConverter> stringConverter;

	public PropertyInfo(ProviderContainer providerContainer, Class<?> domainObjectClass, Method getterMethod) {
		checkGetterMethodReturnType(getterMethod);
		checkGetterMethodHasNoParameter(getterMethod);

		ReflectApplication reflectApplication = providerContainer.get(ReflectApplication.class);
		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		AuthorizationProvider authorizationProvider = providerContainer.get(AuthorizationProvider.class);

		this.simpleName = getSimpleName(getterMethod);
		this.canonicalName = getCanonicalName(getterMethod, simpleName);
		this.displayNameModel = new DisplayNameModel(languageProvider, getterMethod, simpleName, canonicalName);
		this.descriptionModel = new DescriptionModel(languageProvider, getterMethod, simpleName, canonicalName);
		this.typeInfo = new ReturnTypeInfo(reflectApplication, getterMethod);
		this.getterMethod = getterMethod;
		this.setterMethod = getSetterMethod(getterMethod, simpleName, typeInfo.getType());
		this.order = OrderFactory.create(getterMethod);
		this.formatPattern = FormatPatternFactory.create(getterMethod);
		this.stringConverter = createStringConverter(providerContainer);
		this.optionModel = OptionsModelFactory.create(getterMethod);
		this.disabledModel = DisabledModelFactory.create(authorizationProvider, getterMethod, setterMethod);
		this.hiddenModel = HiddenModelFactory.create(authorizationProvider, getterMethod, setterMethod);
		this.actionMethodInfos = ActionMethodInfoFactory.createSorted(providerContainer, domainObjectClass, simpleName);
	}

	private Optional<StringConverter> createStringConverter(ProviderContainer providerContainer) {
		StringConverterFactoryInfo stringConverterInfo = new StringConverterFactoryInfo(typeInfo, providerContainer,
				formatPattern);
		StringConverterProvider stringConverterProvider = providerContainer.get(StringConverterProvider.class);
		try {
			StringConverter stringConverter = stringConverterProvider.create(stringConverterInfo);
			return Optional.of(stringConverter);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	private void checkGetterMethodHasNoParameter(Method getterMethod) {
		if (getterMethod.getParameterTypes().length > 0) {
			throw new RuntimeException("Getter method: " + getterMethod.getClass().getCanonicalName() + "."
					+ getterMethod.getName() + " may not contain a parameter");
		}
	}

	private void checkGetterMethodReturnType(Method getterMethod) {
		if (getterMethod.getReturnType() == Void.class) {
			throw new RuntimeException("Getter method: " + getterMethod.getClass().getCanonicalName() + "."
					+ getterMethod.getName() + " must return a value");
		}
	}

	public TypeInfo getTypeInfo() {
		return typeInfo;
	}

	private Method getSetterMethod(Method getterMethod, String name, Class<?> propertyType) {
		Class<?> methodOwner = getterMethod.getDeclaringClass();
		StringBuffer getterMethodName = new StringBuffer();
		getterMethodName.append(SET_PREFIX);
		getterMethodName.append(Character.toUpperCase(name.charAt(0)));
		getterMethodName.append(name.substring(1));
		try {
			Method writeMethod = methodOwner.getMethod(getterMethodName.toString(), propertyType);
			return writeMethod;
		} catch (Exception e1) {
			try {
				Optional<Class<?>> primitiveWrapperType = PrimitiveType.primitiveToWrapper(propertyType);
				if (primitiveWrapperType.isPresent()) {
					propertyType = primitiveWrapperType.get();
				}
				Method writeMethod = methodOwner.getMethod(getterMethodName.toString(), propertyType);
				return writeMethod;
			} catch (Exception e2) {
				return null;
			}
		}
	}

	private String getCanonicalName(Method readMethod, String name) {
		StringBuffer canonicalName = new StringBuffer();
		canonicalName.append(readMethod.getDeclaringClass().getCanonicalName());
		canonicalName.append(".");
		canonicalName.append(name);
		return canonicalName.toString();
	}

	private String getSimpleName(Method readMethod) {
		String readMethodName = readMethod.getName();
		if (readMethodName.startsWith(IS_PREFIX)) {
			StringBuffer name = new StringBuffer();
			name.append(Character.toLowerCase(readMethodName.charAt(IS_PREFIX.length())));
			name.append(readMethodName.substring(IS_PREFIX.length() + 1));
			return name.toString();
		} else if (readMethodName.startsWith(GET_PREFIX)) {
			StringBuffer name = new StringBuffer();
			name.append(Character.toLowerCase(readMethodName.charAt(GET_PREFIX.length())));
			name.append(readMethodName.substring(GET_PREFIX.length() + 1));
			return name.toString();
		} else {
			throw new RuntimeException("Method: " + readMethod.getClass().getCanonicalName() + "."
					+ readMethod.getName() + " is not a getter method");
		}
	}

	@Override
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

	public Boolean isVisibleInForm(Object obj) {
		return !hiddenModel.isPropertyHiddenInForm(obj);
	}

	public Boolean isVisibleInTable() {
		return !hiddenModel.isPropertyHiddenInTable();
	}

	public boolean isEnabled(Object domainObject) {
		return !disabledModel.isDisabled(domainObject);
	}

	public void setValue(Object domainObject, Object value) {
		if (!isEnabled(domainObject)) {
			throw new RuntimeException(
					"Could not set value of property: " + canonicalName + " when it is disabled or read only");
		}
		try {
			setterMethod.invoke(domainObject, new Object[] { value });
		} catch (Exception e) {
			if (value == null) {
				throw new RuntimeException("Could not set value of property: " + canonicalName + " with value: null",
						e);
			} else {
				throw new RuntimeException("Could not set value of property: " + canonicalName + " with value: " + value
						+ " of type" + value.getClass().getCanonicalName(), e);
			}
		}
	}

	public Object getValue(Object obj) {
		try {
			return getGetterMethod().invoke(obj);
		} catch (Exception e) {
			throw new RuntimeException("Could not read value of property: " + canonicalName, e);
		}
	}

	@Override
	public String toString() {
		return canonicalName;
	}

	public boolean isReadOnly() {
		return setterMethod == null;
	}

	public String getFormatPattern() {
		return formatPattern;// TODO optional
	}

	public Optional<StringConverter> getStringConverter() {
		return stringConverter;
	}

	public String getStringValue(Object domainObject) {
		try {
			Object value = getValue(domainObject);
			if (value == null || !stringConverter.isPresent()) {
				return "";
			} else {
				return stringConverter.get().toString(value);
			}
		} catch (Exception e) {
			return "";
		}
	}

	public static boolean isGetterMethod(Method method) {
		String methodName = method.getName();
		boolean isGetClassMethod = "getClass".equals(methodName);
		if (isGetClassMethod) {
			return false;
		}
		boolean hasNoReturnValue = method.getReturnType() == Void.class;
		if (hasNoReturnValue) {
			return false;
		}
		boolean hasParameters = method.getParameterTypes().length > 0;
		if (hasParameters) {
			return false;
		}
		boolean isEnumGetDeclairingClass = method.getDeclaringClass().isAssignableFrom(Enum.class)
				&& "getDeclaringClass".equals(methodName);
		if (isEnumGetDeclairingClass) {
			return false;
		}
		boolean startsWithGet = methodName.startsWith(PropertyInfo.GET_PREFIX);
		if (startsWithGet) {
			return true;
		}
		boolean startsWithIs = methodName.startsWith(PropertyInfo.IS_PREFIX);
		if (startsWithIs) {
			return true;
		}
		return false;
	}

	/**
	 * @return {@link ActionMethodInfo}s that belong to this property
	 */
	public List<ActionMethodInfo> getActionMethodInfos() {
		return actionMethodInfos;
	}

	public boolean hasOptions() {
		return optionModel.hasOptions();
	}

	/**
	 * Note that you first need to check {@link #hasOptions()} before calling this
	 * method
	 * 
	 * @param domainObject
	 * @return available option values to choose from
	 */
	public List<Object> getOptions(Object domainObject) {
		return optionModel.getOptions(domainObject);
	}

}
