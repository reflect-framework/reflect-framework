package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethods;
import nth.reflect.fw.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledModel;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenModel;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.order.OrderFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModel;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryModelFactory;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.FirstParameterTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.userinterfacemethod.ConfirmMethodFactory;
import nth.reflect.fw.layer5provider.reflection.info.userinterfacemethod.EditParameterMethodFactory;
import nth.reflect.fw.layer5provider.reflection.info.userinterfacemethod.ShowMethodFactory;

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
	private final DisplayNameModel displayNameModel;
	private final DescriptionModel descriptionModel;
	private final DisabledModel disabledModel;
	private final HiddenModel hiddenModel;
	private final ParameterFactoryModel parameterFactoryModel;
	private final ExecutionModeType executionMode;
	private final FontIconModel fontIconModel;
	private final Method editParameterMethod;
	private final Method confirmMethod;
	private final Method showResultMethod;
	private final TypeInfo returnTypeInfo;
	private final TypeInfo firstParameterTypeInfo;
	private final boolean isReadOnly;

	public ActionMethodInfo(ProviderContainer providerContainer, Method method) {
		validateNoObjectClassMethod(method);
		validateNoGettersOrSetterMethod(method);
		validateNoBehavioralMethod(method);
		validateNoStaticMethod(method);
		validateNoOrSingleParameter(method);

		ReflectApplication application = providerContainer.get(ReflectApplication.class);
		Class<? extends UserInterfaceController> controllerClass = application.getUserInterfaceControllerClass();
		this.executionMode = ExecutionModeFactory.create(method);
		this.returnTypeInfo = createReturnTypeInfo(application, method);
		this.firstParameterTypeInfo = createFirstParameterTypeInfo(application, method);
		this.editParameterMethod = EditParameterMethodFactory.create(controllerClass, executionMode, method,
				firstParameterTypeInfo);
		this.confirmMethod = ConfirmMethodFactory.create(controllerClass, executionMode, method,
				firstParameterTypeInfo);
		this.showResultMethod = ShowMethodFactory.create(controllerClass, executionMode, method, returnTypeInfo);

		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		AuthorizationProvider authorizationProvider = providerContainer.get(AuthorizationProvider.class);

		this.actionMethod = method;
		this.simpleName = method.getName();
		this.canonicalName = createCanonicalName(method);
		this.displayNameModel = new DisplayNameModel(languageProvider, method, simpleName, canonicalName);
		this.descriptionModel = new DescriptionModel(languageProvider, method, simpleName, canonicalName);
		this.order = OrderFactory.create(method);
		this.disabledModel = DisabledModelFactory.create(authorizationProvider, method);
		this.hiddenModel = HiddenModelFactory.create(authorizationProvider, method);
		this.parameterFactoryModel = ParameterFactoryModelFactory.create(method, firstParameterTypeInfo.getType());
		this.fontIconModel = FontIconModelFactory.create(method);
		this.isReadOnly = method.isAnnotationPresent(ReadOnlyActionMethod.class);
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

	@Override
	public String getCanonicalName() {
		return canonicalName;
	}

	public String getDisplayName() {
		return displayNameModel.getText();
	}

	public String getDescription() {
		return descriptionModel.getText();
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
			throw new NoParameterFactoryException(getCanonicalName());
		}
	}

	public Object invoke(Object methodOwner, Object methodParameter)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (hasParameter()) {
			return actionMethod.invoke(methodOwner, methodParameter);
		} else {
			return actionMethod.invoke(methodOwner);
		}
	}

	// public String getLinkedPropertyName() {
	// return linkedPropertyName;
	// }

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
		confirmMethod.invoke(userInterfaceController, methodOwner, this, methodParameter);

	}

	/**
	 * Method to invoke a ShowActionMethodResult method of the
	 * {@link UserInterfaceController}
	 */
	public void invokeShowResult(UserInterfaceController userInterfaceController, Object methodOwner,
			Object methodParameter, Object methodResult)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (hasReturnValue()) {
			showResultMethod.invoke(userInterfaceController, methodOwner, this, methodParameter, methodResult);
		} else {
			showResultMethod.invoke(userInterfaceController, methodOwner, this, methodParameter);
		}

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

}
