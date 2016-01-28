package nth.introspect.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethods;
import nth.introspect.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.introspect.layer5provider.reflection.behavior.disabled.DisabledModel;
import nth.introspect.layer5provider.reflection.behavior.disabled.DisabledModelFactory;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeFactory;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenModel;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenModelFactory;
import nth.introspect.layer5provider.reflection.behavior.icon.IconModel;
import nth.introspect.layer5provider.reflection.behavior.icon.IconModelFactory;
import nth.introspect.layer5provider.reflection.behavior.order.OrderFactory;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModel;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModelFactory;
import nth.introspect.layer5provider.reflection.info.NameInfo;
import nth.introspect.layer5provider.reflection.info.type.MethodParameterType;
import nth.introspect.layer5provider.reflection.info.type.MethodReturnType;
import nth.introspect.layer5provider.reflection.info.userinterfacemethod.ConfirmMethodFactory;
import nth.introspect.layer5provider.reflection.info.userinterfacemethod.EditParameterMethodFactory;
import nth.introspect.layer5provider.reflection.info.userinterfacemethod.ShowMethodFactory;

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
	private final String linkedPropertyName;
	private final MethodParameterType parameterType;
	private final MethodReturnType returnType;
	private final double order;
	private final DisplayNameModel displayNameModel;
	private final DescriptionModel descriptionModel;
	private final DisabledModel disabledModel;
	private final HiddenModel hiddenModel;
	private final ParameterFactoryModel parameterFactoryModel;
	private ExecutionModeType executionMode;
	private final IconModel iconModel;
	private final Method editParameterMethod;
	private final Method cofirmMethod;
	private final Method showResultMethod;

	public ActionMethodInfo(ProviderContainer providerContainer, Method method) {
		this(providerContainer, method, null);
	}

	public ActionMethodInfo(ProviderContainer providerContainer, Method method, String linkedPropertyName) {
		validateNoObjectClassMethod(method);
		validateNoGettersOrSetterMethod(method);
		validateNoBehavioralMethod(method);
		validateNoStaticMethod(method);
		validateNoOrSingleParameter(method);

		IntrospectApplication application = providerContainer.get(IntrospectApplication.class);
		Class<? extends UserInterfaceController> controllerClass = application.getUserInterfaceControllerClass();
		this.executionMode = ExecutionModeFactory.create(method);
		this.editParameterMethod = EditParameterMethodFactory.create(controllerClass, executionMode, method);
		this.cofirmMethod = ConfirmMethodFactory.create(controllerClass, executionMode, method);
		this.showResultMethod = ShowMethodFactory.create(controllerClass, executionMode, method);

		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		AuthorizationProvider authorizationProvider = providerContainer.get(AuthorizationProvider.class);
		PathProvider pathProvider = providerContainer.get(PathProvider.class);

		this.actionMethod = method;
		this.linkedPropertyName = linkedPropertyName;
		this.simpleName = method.getName();
		this.canonicalName = createCanonicalName(method);
		this.displayNameModel = new DisplayNameModel(languageProvider, method, simpleName, canonicalName,
				linkedPropertyName);
		this.descriptionModel = new DescriptionModel(languageProvider, method, simpleName, canonicalName,
				linkedPropertyName);
		this.returnType = new MethodReturnType(method);
		this.parameterType = new MethodParameterType(method);
		this.order = OrderFactory.create(method);
		this.disabledModel = DisabledModelFactory.create(authorizationProvider, method);
		this.hiddenModel = HiddenModelFactory.create(authorizationProvider, method);
		this.parameterFactoryModel = ParameterFactoryModelFactory.create(method, parameterType.getType());
		this.iconModel = IconModelFactory.create(method, pathProvider.getImagePath());

	}

	private void validateNoOrSingleParameter(Method method) {
		if (method.getParameterTypes().length > 1) {
			throw new InvalidActionMethodException("ActionMethod: %s has more than 1 parameter", method);
		}
	}

	private void validateNoStaticMethod(Method method) {
		if (Modifier.isStatic(method.getModifiers())) {
			throw new InvalidActionMethodException("ActionMethod: %s may not be be static", method);
		}
	}

	private void validateNoBehavioralMethod(Method method) {
		if (BehavioralMethods.isBehavioralMethod(method)) {
			throw new InvalidActionMethodException("ActionMethod: %s may not be a BehavioralMethod", method);
		}
	}

	private void validateNoObjectClassMethod(Method method) {
		String methodName = method.getName();
		for (Method methodOfObjectClass : Object.class.getMethods()) {
			if (methodOfObjectClass.getName().equals(methodName)) {
				throw new InvalidActionMethodException("ActionMethod: %s may not be a method of the Object Class",
						method);
			}
		}
	}

	private void validateNoGettersOrSetterMethod(Method method) {
		String methodName = method.getName();
		if (methodName.startsWith("get") || methodName.startsWith("is")) {
			throw new InvalidActionMethodException("ActionMethod: %s may not be a getter method", method);
		}
		if (methodName.startsWith("set")) {
			throw new InvalidActionMethodException("ActionMethod: %s may not be a setter method", method);
		}
	}

	public static String createCanonicalName(Method method) {
		StringBuffer conicalName = new StringBuffer();
		conicalName.append(method.getDeclaringClass().getCanonicalName());
		conicalName.append(".");
		conicalName.append(method.getName());
		return conicalName.toString();
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	public String getCanonicalName() {
		return canonicalName;
	}

	public String getDisplayName() {
		return displayNameModel.getText();
	}

	public String getDescription() {
		return descriptionModel.getText();
	}

	public URI getIconURI(Object obj) {
		return iconModel.getURI(obj);
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

	// TODO try to remove method
	public void setExecutionMode(ExecutionModeType executeMethod) {
		this.executionMode = executeMethod;
	}

	public MethodParameterType getParameterType() {
		return parameterType;
	}

	public MethodReturnType getReturnType() {
		return returnType;
	}

	public boolean hasParameterFactory() {
		return parameterFactoryModel != null;
	}

	public Object createMethodParameter(Object obj) throws InstantiationException, IllegalAccessException {
		if (hasParameterFactory()) {
			return parameterFactoryModel.createNewMethodParameter(obj);
		} else {
			throw new NoParameterFactoryException(getCanonicalName());
		}
	}

	public Object invoke(Object methodOwner, Object methodParameter) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Object[] parameterValue = null;
		switch (getParameterType().getTypeCategory()) {
		case NONE:
			parameterValue = null;
			break;
		case DOMAIN_TYPE:
			parameterValue = new Object[] {methodParameter};
			break;
		default:
			break;
		}
		return actionMethod.invoke(methodOwner, parameterValue);
	}

	public String getLinkedPropertyName() {
		return linkedPropertyName;
	}

	@Override
	public String toString() {
		return canonicalName;
	}

	public boolean hasParameter() {
		return actionMethod.getParameterTypes().length > 0;
	}

	/**
	 * Method to invoke a editAtionMethodParameter method of the
	 * {@link UserInterfaceController}
	 */
	public void invokeEditParameterMethod(UserInterfaceController userInterfaceController, Object methodOwner,
			Object methodParameter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		editParameterMethod.invoke(userInterfaceController, methodOwner, this, methodParameter);
	}

	/**
	 * Method to invoke a ConfirmActionMethod method of the
	 * {@link UserInterfaceController}
	 */

	public void invokeConfirmMethod(UserInterfaceController userInterfaceController, Object methodOwner,
			Object methodParameter) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		cofirmMethod.invoke(userInterfaceController, methodOwner, this, methodParameter);

	}

	/**
	 * Method to invoke a ShowActionMethodResult method of the
	 * {@link UserInterfaceController}
	 */
	public void invokeShowResult(UserInterfaceController userInterfaceController, Object methodOwner,
			Object methodParameter, Object methodResult) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		showResultMethod.invoke(userInterfaceController, methodOwner, this, methodParameter, methodResult);

	}

}
