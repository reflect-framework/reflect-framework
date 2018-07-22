package nth.reflect.fw.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.Method;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.InvalidActionMethodException;

public class MethodParameterTypeNotSupported extends InvalidActionMethodException {

	private static final long serialVersionUID = -6178835316096085356L;

	public MethodParameterTypeNotSupported(Class<? extends UserInterfaceController> controllerClass,
			String processMethodName, Method actionMethod) {
		super(createMessage(controllerClass, processMethodName, actionMethod));
	}

	private static String createMessage(Class<? extends UserInterfaceController> controllerClass,
			String processMethodName, Method actionMethod) {
		String message = "Action method: %1$s can not be processed because it's parameter type: %2$s is not supported (the %3$s class does not contain a method: %4$s with parameter type: %2$s)";
		String actionMethodName = ActionMethodInfo.createCanonicalName(actionMethod);
		Class<?> parameterType = getParameterType(actionMethod);
		String actionMethodParameterName = parameterType.getCanonicalName();
		String userInterfaceControllerName = controllerClass.getCanonicalName();
		return String.format(message, actionMethodName, actionMethodParameterName, userInterfaceControllerName,
				processMethodName);
	}

	private static Class<?> getParameterType(Method actionMethod) {
		Class<?>[] parameterTypes = actionMethod.getParameterTypes();
		Class<?> parameterType;
		if (parameterTypes.length == 0) {
			parameterType = Object.class;
		} else {
			parameterType = parameterTypes[0];
		}
		return parameterType;
	}

}
