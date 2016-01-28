package nth.introspect.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.Method;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ConfirmMethodFactory {

	private static final String CONFIRM_ACTION_METHOD = "confirmActionMethod";

	public static Method create(Class<? extends UserInterfaceController> controllerClass,
			ExecutionModeType executionMode, Method actionMethod) {
		if (executionMode != ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION) {
			return null;
		}
		Method confirmMethod = findConfirmMethod(controllerClass, actionMethod);
		return confirmMethod;
	}

	private static Method findConfirmMethod(
			Class<? extends UserInterfaceController> controllerClass, Method actionMethod) {
		Class<?> parameterType = getParameterType(actionMethod);
		Class<?>[] parameterTypes = new Class[] { Object.class, ActionMethodInfo.class,
				parameterType };
		try {
			Method method = findMethod(controllerClass, parameterTypes, actionMethod);
			return method;
		} catch (Exception e) {
			// method with specific parameter type not found found!
			// try to find a method that takes a domainObject as argument
			if (TypeUtil.isDomainType(parameterType)) {
				parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, Object.class };
				Method method = findMethod(controllerClass, parameterTypes, actionMethod);
				return method;
			} else {
				throw new MethodParameterTypeNotSupported(controllerClass, CONFIRM_ACTION_METHOD,
						actionMethod);
			}
		}
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

	private static Method findMethod(Class<? extends UserInterfaceController> controllerClass,
			Class<?>[] parameterTypes, Method actionMethod) {
		try {
			Method method = controllerClass.getMethod(CONFIRM_ACTION_METHOD, parameterTypes);
			return method;
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new MethodParameterTypeNotSupported(controllerClass, CONFIRM_ACTION_METHOD,
					actionMethod);
		}
	}

}
