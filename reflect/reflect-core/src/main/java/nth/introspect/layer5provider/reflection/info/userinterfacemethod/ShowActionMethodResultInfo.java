package nth.introspect.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ShowActionMethodResultInfo {

	private final static String SHOW_ACTION_METHOD_RESULT = "showActionMethodResult";
	private static final Class<?> VOID_TYPE = null;
	private final ActionMethodInfo methodInfo;
	private final Method showActionMethodResultMethod;

	public ShowActionMethodResultInfo(
			Class<? extends UserInterfaceController> userInterfaceControllerClass,
			ActionMethodInfo methodInfo) {
		this.methodInfo = methodInfo;
		this.showActionMethodResultMethod = getShowActionMethodResultMethod(userInterfaceControllerClass);
	}

	private Method getShowActionMethodResultMethod(
			Class<? extends UserInterfaceController> userInterfaceControllerClass) {
		Class<?> returnType = methodInfo.getReturnType().getType();
		Class<?>[] parameterTypes;
		if (returnType == VOID_TYPE) {
			parameterTypes = new Class[] { Object.class,
					ActionMethodInfo.class, Object.class };
		} else {
			parameterTypes = new Class[] { Object.class,
					ActionMethodInfo.class, Object.class, returnType };
		}
		try {
			Method method = findMethod(userInterfaceControllerClass,
					parameterTypes);
			return method;
		} catch (Exception e) {
			// method with specific result type not found found!
			// try to find a method that takes a domainObject as argument
			if (TypeUtil.isDomainType(returnType)) {
				parameterTypes = new Class[] { Object.class,
						ActionMethodInfo.class, Object.class, Object.class };
				Method method = findMethod(userInterfaceControllerClass,
						parameterTypes);
				return method;
			} else {
				throw new MethodReturnTypeNotSupported(
						userInterfaceControllerClass,
						SHOW_ACTION_METHOD_RESULT, methodInfo);
			}
		}
	}

	private Method findMethod(
			Class<? extends UserInterfaceController> userInterfaceControllerClass,
			Class<?>[] methodArguments) {
		try {
			Method method = userInterfaceControllerClass.getMethod(
					SHOW_ACTION_METHOD_RESULT, methodArguments);
			return method;
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new MethodReturnTypeNotSupported(
					userInterfaceControllerClass, SHOW_ACTION_METHOD_RESULT,
					methodInfo);
		}
	}

	private Object[] getProcessMethodParameterValues(Object methodOwner,
			Object methodParameter, Object methodResult) {
		return new Object[] { methodOwner, methodInfo, methodParameter,
				methodResult };
	}

	public void invoke(Object userInterfaceController, Object methodOwner,
			Object methodParameter, Object methodResult)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Object[] parameterValues = getProcessMethodParameterValues(methodOwner,
				methodParameter, methodResult);
		showActionMethodResultMethod.invoke(userInterfaceController,
				parameterValues);
	}
}
