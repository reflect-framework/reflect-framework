package nth.introspect.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.Method;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.InvalidActionMethodException;

public class MethodReturnTypeNotSupported extends
		InvalidActionMethodException {

	private static final long serialVersionUID = 6100808275699979120L;
	private static final Class<?> NO_PARAMETER = null;

	public MethodReturnTypeNotSupported(
			Class<? extends UserInterfaceController> userInterfaceControllerClass,
			String processMethodName, Method actionMethod) {
		super(createMessage(userInterfaceControllerClass, processMethodName,
				actionMethod));
	}

	private static String createMessage(
			Class<? extends UserInterfaceController> userInterfaceControllerClass,
			String processMethodName, Method actionMethod) {
		String message = "Action method: %1$s can not be processed because it's return type: %2$s is not supported (the %3$s class does not contain a method: %4$s with parameter type: %2$s)";
		String actionMethodName = ActionMethodInfo.createCanonicalName(actionMethod);
		Class<?> returnType = actionMethod.getReturnType();
		if (returnType == NO_PARAMETER) {
			returnType = Object.class;
		}
		String returnTypeName = returnType.getCanonicalName();
		String userInterfaceControllerName = userInterfaceControllerClass
				.getCanonicalName();
		return String.format(message, actionMethodName,
				returnTypeName, userInterfaceControllerName,
				processMethodName);
	}

}
