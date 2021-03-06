package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandlerProvider;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandlerProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethods;
import nth.reflect.fw.layer5provider.reflection.behavior.description.TranslatedMethodDescription;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledModel;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.TranslatedDisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.TranslatedMethodDisplayName;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenModel;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.order.OrderFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModel;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.title.TitleModel;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodHasNoParameterFactoryException;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodMaxOneParameterException;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodMayNotBeAMethodOfObjectClass;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodMayNotBeBehavioralMethodException;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodMayNotBeGetterMethodException;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodMayNotBeSetterMethodException;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodMayNotBeStaticException;
import nth.reflect.fw.layer5provider.reflection.info.type.FirstParameterTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * <p>
 * Provides (reflection) information on a {@link ActionMethod}.
 * </p>
 * 
 * @author nilsth
 * 
 */

public class ActionMethodInfo implements NameInfo {

	private final String simpleName;
	private final String canonicalName;
	private final Method actionMethod;
	private final double order;
	private final TranslatedString displayName;
	private final TranslatedString description;
	private final DisabledModel disabledModel;
	private final HiddenModel hiddenModel;
	private final ParameterFactoryModel parameterFactoryModel;
	private final ExecutionModeType executionMode;
	private final FontIconModel fontIconModel;
	private final TypeInfo returnTypeInfo;
	private final TypeInfo firstParameterTypeInfo;
	private final boolean isReadOnly;
	private final ReflectionProvider reflectionProvider;
	private final ActionMethodResultHandler resultHandler;
	private final ActionMethodPreHandler preHandler;

	public ActionMethodInfo(ProviderContainer container, Method method, String propertyName) {
		this.simpleName = createSimpleName(method, propertyName);

		validateNoObjectClassMethod(method);
		validateNoGettersOrSetterMethod(method);
		validateNoBehavioralMethod(method);
		validateNoStaticMethod(method);
		validateNoOrSingleParameter(method);

		ReflectApplication application = container.get(ReflectApplication.class);
		LanguageProvider languageProvider = container.get(LanguageProvider.class);
		AuthorizationProvider authorizationProvider = container.get(AuthorizationProvider.class);
		this.reflectionProvider = container.get(ReflectionProvider.class);
		this.executionMode = ExecutionModeFactory.create(method);
		this.returnTypeInfo = createReturnTypeInfo(application, method);
		this.firstParameterTypeInfo = createFirstParameterTypeInfo(application, method);
		this.actionMethod = method;
		this.canonicalName = MethodCanonicalName.getFor(method);
		this.displayName = new TranslatedMethodDisplayName(languageProvider, method, this);
		this.description = new TranslatedMethodDescription(languageProvider, method, this);
		this.order = OrderFactory.create(method);
		this.disabledModel = DisabledModelFactory.create(authorizationProvider, method);
		this.hiddenModel = HiddenModelFactory.create(authorizationProvider, method);
		this.parameterFactoryModel = ParameterFactoryModelFactory.create(method, firstParameterTypeInfo.getType());
		this.fontIconModel = FontIconModelFactory.create(method);
		this.isReadOnly = method.isAnnotationPresent(ReadOnlyActionMethod.class);

		ActionMethodPreHandlerProvider preHandlerProvider = container.get(ActionMethodPreHandlerProvider.class);
		this.preHandler = preHandlerProvider.get(this);
		ActionMethodResultHandlerProvider resultHandlerProvider = container
				.get(ActionMethodResultHandlerProvider.class);
		this.resultHandler = resultHandlerProvider.get(container, this);

	}

	private String createSimpleName(Method method, String propertyName) {
		if (propertyName == null) {
			return method.getName();
		} else {
			String methodNameWithoutPropertyName = method.getName().replaceFirst("^" + propertyName, "");
			String simpleName = StringUtil.firstCharToLowerCase(methodNameWithoutPropertyName);
			return simpleName;
		}
	}

	public ActionMethodInfo(ProviderContainer container, Method method) {
		this(container, method, null);
	}

	/**
	 * 
	 * @param application
	 * @param method
	 * @return a {@link TypeInfo} for the return type of an {@link ActionMethod}<br>
	 *         It will return a {@link TypeInfo} of type {@link Void} when the
	 *         {@link ActionMethod} has no parameters
	 */
	private ReturnTypeInfo createReturnTypeInfo(ReflectApplication application, Method method) {
		return new ReturnTypeInfo(application, method);
	}

	/**
	 * @param application
	 * @param actionMethod
	 * @return a {@link TypeInfo} for the first parameter type of an
	 *         {@link ActionMethod}<br>
	 *         It will return a {@link TypeInfo} of type {@link Void} when the
	 *         {@link ActionMethod} has no parameters
	 */
	private TypeInfo createFirstParameterTypeInfo(ReflectApplication application, Method actionMethod) {
		if (actionMethod.getParameterTypes().length == 0) {
			return new TypeInfo(application, Void.TYPE, Void.TYPE);
		} else {
			return new FirstParameterTypeInfo(application, actionMethod);
		}
	}

	private void validateNoOrSingleParameter(Method method) {
		if (method.getParameterTypes().length > 1) {
			throw new ActionMethodMaxOneParameterException(method);
		}
	}

	private void validateNoStaticMethod(Method method) {
		if (Modifier.isStatic(method.getModifiers())) {
			throw new ActionMethodMayNotBeStaticException(method);
		}
	}

	private void validateNoBehavioralMethod(Method method) {
		if (BehavioralMethods.isBehavioralMethod(method)) {
			throw new ActionMethodMayNotBeBehavioralMethodException(method);
		}
	}

	private void validateNoObjectClassMethod(Method method) {
		for (Method methodOfObjectClass : Object.class.getMethods()) {
			String methodName = method.getName();
			if (methodOfObjectClass.getName().equals(methodName)) {
				throw new ActionMethodMayNotBeAMethodOfObjectClass(method);
			}
		}
	}

	private void validateNoGettersOrSetterMethod(Method method) {
		String methodName = method.getName();
		if (methodName.startsWith("get") || methodName.startsWith("is")) {
			throw new ActionMethodMayNotBeGetterMethodException(method);
		}
		if (methodName.startsWith("set")) {
			throw new ActionMethodMayNotBeSetterMethodException(method);
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

	@Override
	public TranslatedString getDisplayName() {
		return displayName;
	}

	@Override
	public TranslatedString getDescription() {
		return description;
	}

	public URL getFontIconUrl(Object obj) {
		return fontIconModel.getFontIconUrl(obj);
	}

	public double getOrder() {
		return order;
	}

	public Boolean isVisible(Object obj) {
		return !hiddenModel.isHiddenActionMethod(obj);
	}

	public Boolean isEnabled(Object obj) {
		return !disabledModel.isDisabled(obj);
	}

	public ExecutionModeType getExecutionMode() {
		return executionMode;
	}

	public TypeInfo getReturnTypeInfo() {
		return returnTypeInfo;
	}

	public TranslatableString getTitle(Object methodParameter) {
		String key = getCanonicalName() + TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		String englishText = getDisplayName().getTranslation();
		if (methodParameter != null) {
			TitleModel titleModel = new TitleModel(reflectionProvider);
			String parameterTitle = titleModel.getTitle(methodParameter);
			if (!parameterTitle.trim().isEmpty()) {
				englishText = englishText + " (" + parameterTitle + ")";
			}
		}
		return new TranslatableString(key, englishText);
	}

	/**
	 * See {@link #createFirstParameterTypeInfo(ReflectApplication, Method)}
	 * 
	 * @return
	 */
	public TypeInfo getFirstParameterTypeInfo() {
		return firstParameterTypeInfo;
	}

	public boolean hasParameterFactory() {
		return parameterFactoryModel != null;
	}

	public Object createMethodParameter(Object obj) throws InstantiationException, IllegalAccessException {
		if (hasParameterFactory()) {
			return parameterFactoryModel.createNewMethodParameter(obj);
		} else {
			throw new ActionMethodHasNoParameterFactoryException(actionMethod);
		}
	}

	@Override
	public String toString() {
		return canonicalName;
	}

	public boolean hasParameter() {
		return actionMethod.getParameterTypes().length > 0;
	}

	public boolean hasReturnValue() {
		return actionMethod.getReturnType() != Void.TYPE;
	}

	public ActionMethodPreHandler getPreHandler() {
		return preHandler;
	}

	public ActionMethodResultHandler getResultHandler() {
		return resultHandler;
	}

	/**
	 * 
	 * @return True if the {@link ActionMethod} has the {@link ReadOnlyActionMethod}
	 *         when it does not modify a {@link DomainObject}.
	 */
	public boolean isReadOnly() {
		return isReadOnly;
	}

	public Method getMethod() {
		return actionMethod;
	}

	public boolean needsToCreateParameter(Object methodParameter) {
		return methodParameter == null && hasParameter() || hasParameterFactory();
	}

}
