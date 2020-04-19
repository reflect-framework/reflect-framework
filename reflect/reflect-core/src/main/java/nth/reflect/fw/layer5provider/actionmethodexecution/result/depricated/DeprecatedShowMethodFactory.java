package nth.reflect.fw.layer5provider.actionmethodexecution.result.depricated;

import java.lang.reflect.Method;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * @deprecated see {@link DeprecatedActionMethodResultHandler}
 * @author nilsth
 *
 */
@Deprecated

public class DeprecatedShowMethodFactory {

	private final static String SHOW_ACTION_METHOD_RESULT = "showActionMethodResult";

	public static Method create(Class<? extends UserInterfaceController> controllerClass, Method actionMethod,
			TypeInfo typeInfo) {

		Class<?> returnType = actionMethod.getReturnType();
		Class<?>[] parameterTypes;
		if (returnType == Void.TYPE) {
			parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, Object.class };
		} else {
			parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, Object.class, returnType };
		}
		try {
			Method method = findMethod(controllerClass, parameterTypes, actionMethod);
			return method;
		} catch (Exception e) {
			// method with specific result type not found found!
			// try to find a method that takes a domainObject as argument
			if (typeInfo.isDomainClass()) {
				parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, Object.class, Object.class };
				Method method = findMethod(controllerClass, parameterTypes, actionMethod);
				return method;
			} else {
				throw new DeprecatedMethodReturnTypeNotSupported(controllerClass, SHOW_ACTION_METHOD_RESULT,
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
			throw new DeprecatedMethodReturnTypeNotSupported(controllerClass, SHOW_ACTION_METHOD_RESULT, actionMethod);
		}
	}

}
