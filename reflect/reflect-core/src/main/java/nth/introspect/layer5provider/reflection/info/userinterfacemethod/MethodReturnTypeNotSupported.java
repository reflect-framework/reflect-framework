package nth.introspect.layer5provider.reflection.info.userinterfacemethod;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class MethodReturnTypeNotSupported extends
		nth.introspect.generic.exception.IntrospectException {

	private static final long serialVersionUID = 6100808275699979120L;
	private static final Class<?> NO_PARAMETER = null;

	public MethodReturnTypeNotSupported(
			Class<? extends UserInterfaceController> userInterfaceControllerClass,
			String processMethodName, ActionMethodInfo actionMethodInfo) {
		super(createMessage(userInterfaceControllerClass, processMethodName,
				actionMethodInfo));
	}

	private static String createMessage(
			Class<? extends UserInterfaceController> userInterfaceControllerClass,
			String processMethodName, ActionMethodInfo actionMethodInfo) {
		String message = "Action method: %1$s can not be processed because it's return type: %2$s is not supported (the %3$s class does not contain a method: %4$s with parameter type: %2$s)";
		String actionMethodName = actionMethodInfo.getCanonicalName();
		Class<?> returnType = actionMethodInfo.getParameterType().getType();
		if (returnType == NO_PARAMETER) {
			returnType = Object.class;
		}
		String actionMethodParameterName = returnType.getCanonicalName();
		String userInterfaceControllerName = userInterfaceControllerClass
				.getCanonicalName();
		return String.format(message, actionMethodName,
				actionMethodParameterName, userInterfaceControllerName,
				processMethodName);
	}

}
