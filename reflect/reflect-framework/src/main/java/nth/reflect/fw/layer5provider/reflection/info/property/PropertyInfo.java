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
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.behavior.description.TranslatedMethodDescription;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledModel;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.TranslatedMethodDisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatPatternFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenModel;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsModel;
import nth.reflect.fw.layer5provider.reflection.behavior.option.OptionsModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.order.OrderFactory;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfoFactory;
import nth.reflect.fw.layer5provider.reflection.info.property.exception.CouldNotReadPropertyValue;
import nth.reflect.fw.layer5provider.reflection.info.property.exception.CouldNotSetDisabledPropertyValueException;
import nth.reflect.fw.layer5provider.reflection.info.property.exception.CouldNotSetPropertyValueException;
import nth.reflect.fw.layer5provider.reflection.info.property.exception.CouldNotSetPropertyValueWithNullException;
import nth.reflect.fw.layer5provider.reflection.info.property.exception.GetterMethodMayNotContainParameterException;
import nth.reflect.fw.layer5provider.reflection.info.property.exception.GetterMethodMustReturnValueException;
import nth.reflect.fw.layer5provider.reflection.info.property.exception.NotGetterMethodException;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

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
	private final TranslatedString displayName;
	private final TranslatedString description;
	private final TypeInfo typeInfo;
	private final double order;
	private final String formatPattern;
	private final DisabledModel disabledModel;
	private final HiddenModel hiddenModel;
	private final OptionsModel optionModel;
	private final List<ActionMethodInfo> actionMethodInfos;
	private final Optional<StringConverter> stringConverter;
	private final Class<?> domainClass;

	public PropertyInfo(ProviderContainer providerContainer, Class<?> domainClass, Method getterMethod) {
		checkGetterMethodReturnType(getterMethod);
		checkGetterMethodHasNoParameter(getterMethod);

		ReflectApplication reflectApplication = providerContainer.get(ReflectApplication.class);
		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		AuthorizationProvider authorizationProvider = providerContainer.get(AuthorizationProvider.class);

		this.domainClass = domainClass;
		this.simpleName = getSimpleName(getterMethod);
		this.canonicalName = getCanonicalName(domainClass, simpleName);
		this.displayName = new TranslatedMethodDisplayName(languageProvider, getterMethod, this);
		this.description = new TranslatedMethodDescription(languageProvider, getterMethod, this);
		this.typeInfo = new ReturnTypeInfo(reflectApplication, getterMethod);
		this.getterMethod = getterMethod;
		this.setterMethod = getSetterMethod(getterMethod, simpleName, typeInfo.getType());
		this.order = OrderFactory.create(getterMethod);
		this.formatPattern = FormatPatternFactory.create(getterMethod);
		this.stringConverter = createStringConverter(providerContainer);
		this.optionModel = OptionsModelFactory.create(getterMethod);
		this.disabledModel = DisabledModelFactory.create(authorizationProvider, getterMethod, setterMethod);
		this.hiddenModel = HiddenModelFactory.create(authorizationProvider, getterMethod, setterMethod);
		this.actionMethodInfos = ActionMethodInfoFactory.createSorted(providerContainer, this);
	}

	private Optional<StringConverter> createStringConverter(ProviderContainer providerContainer) {
		StringConverterProvider stringConverterProvider = providerContainer.get(StringConverterProvider.class);
		try {
			StringConverter stringConverter = stringConverterProvider.create(typeInfo, formatPattern);
			return Optional.of(stringConverter);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	private void checkGetterMethodHasNoParameter(Method getterMethod) {
		if (getterMethod.getParameterTypes().length > 0) {
			throw new GetterMethodMayNotContainParameterException(getterMethod);
		}
	}

	private void checkGetterMethodReturnType(Method getterMethod) {
		if (getterMethod.getReturnType() == Void.class) {
			throw new GetterMethodMustReturnValueException(getterMethod);
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

	private String getCanonicalName(Class<?> domainClass, String simplePropertyName) {
		StringBuffer canonicalName = new StringBuffer();
		canonicalName.append(domainClass.getCanonicalName());
		canonicalName.append(".");
		canonicalName.append(simplePropertyName);
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
			throw new NotGetterMethodException(readMethod);
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

	@Override
	public TranslatedString getDisplayName() {
		return displayName;
	}

	@Override
	public TranslatedString getDescription() {
		return description;
	}

	public double getOrder() {
		return order;
	}

	public Boolean isVisibleInForm(Object obj) {
		return !hiddenModel.isPropertyHiddenInForm(obj);
	}

	public Boolean isVisibleInTable() {
		return stringConverter.isPresent() && !hiddenModel.isPropertyHiddenInTable();
	}

	public boolean isEnabled(Object domainObject) {
		return !disabledModel.isDisabled(domainObject);
	}

	public void setValue(Object domainObject, Object value) {
		if (!isEnabled(domainObject)) {
			throw new CouldNotSetDisabledPropertyValueException(canonicalName);
		}
		try {
			setterMethod.invoke(domainObject, new Object[] { value });
		} catch (Exception e) {
			if (value == null) {
				throw new CouldNotSetPropertyValueWithNullException(canonicalName, e);
			} else {
				throw new CouldNotSetPropertyValueException(canonicalName, value, e);
			}
		}
	}

	public Object getValue(Object obj) {
		try {
			return getGetterMethod().invoke(obj);
		} catch (Exception e) {
			throw new CouldNotReadPropertyValue(canonicalName, e);
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

	public Class<?> getDomainClass() {
		return domainClass;
	}

}
