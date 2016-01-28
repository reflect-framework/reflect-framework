package nth.introspect.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.Method;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ShowMethodFactory {

	private final static String SHOW_ACTION_METHOD_RESULT = "showActionMethodResult";
	private static final Class<?> VOID_TYPE = null;

	public static Method create(Class<? extends UserInterfaceController> controllerClass,
			ExecutionModeType executionMode, Method actionMethod) {

		Class<?> returnType = actionMethod.getReturnType();
		Class<?>[] parameterTypes;
		if (returnType == VOID_TYPE) {
			parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, Object.class };
		} else {
			parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, Object.class,
					returnType };
		}
		try {
			Method method = findMethod(controllerClass, parameterTypes, actionMethod);
			return method;
		} catch (Exception e) {
			// method with specific result type not found found!
			// try to find a method that takes a domainObject as argument
			if (TypeUtil.isDomainType(returnType)) {
				parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, Object.class,
						Object.class };
				Method method = findMethod(controllerClass, parameterTypes, actionMethod);
				return method;
			} else {
				throw new MethodReturnTypeNotSupported(controllerClass, SHOW_ACTION_METHOD_RESULT,
						actionMethod);
			}
		}
	}

	private static Method findMethod(Class<? extends UserInterfaceController> controllerClass,
			Class<?>[] methodArguments, Method actionMethod) {
		try {
			Method method = controllerClass.getMethod(SHOW_ACTION_METHOD_RESULT, methodArguments);
			return method;
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new MethodReturnTypeNotSupported(controllerClass, SHOW_ACTION_METHOD_RESULT,
					actionMethod);
		}
	}

}
