package nth.introspect.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * A {@link EditActionMethodParameterInfo} is a method in the
 * {@link UserInterfaceController} (or in one of its sub classes) that helps to
 * execute an {@link ActionMethod}. The {@link UserInterfaceController} supports
 * different {@link ActionMethod} parameter types with different
 * {@link EditActionMethodParameterInfo}s that have different parameter types.
 * 
 * @author nilsth
 *
 */
public class EditActionMethodParameterInfo {

	private static final String EDIT_ACTION_METHOD_PARAMETER = "editActionMethodParameter";
	private final ActionMethodInfo methodInfo;
	private final Method editActionMethodParameterMethod;

	public EditActionMethodParameterInfo(
			Class<? extends UserInterfaceController> userInterfaceControllerClass,
			ActionMethodInfo methodInfo) {
		this.methodInfo = methodInfo;
		this.editActionMethodParameterMethod = getEditActionMethodParameterMethod(userInterfaceControllerClass);
	}

	private Method getEditActionMethodParameterMethod(
			Class<? extends UserInterfaceController> userInterfaceControllerClass) {

		Class<?> parameterType = methodInfo.getParameterType().getType();
		Class<?>[] parameterTypes = new Class[] { Object.class,
				ActionMethodInfo.class, parameterType };
		try {
			Method method = findMethod(userInterfaceControllerClass,
					parameterTypes);
			return method;
		} catch (Exception e) {
			// method with specific parameter type not found found!
			// try to find a method that takes a domainObject as argument
			if (TypeUtil.isDomainType(parameterType)) {
				parameterTypes = new Class[] { Object.class,
						ActionMethodInfo.class, Object.class };
				Method method = findMethod(userInterfaceControllerClass,
						parameterTypes);
				return method;
			} else {
				throw new MethodParameterTypeNotSupported(
						userInterfaceControllerClass,
						EDIT_ACTION_METHOD_PARAMETER, methodInfo);
			}
		}

	}

	private Method findMethod(
			Class<? extends UserInterfaceController> userInterfaceControllerClass,
			Class<?>[] methodArguments) {
		try {
			Method method = userInterfaceControllerClass.getMethod(
					EDIT_ACTION_METHOD_PARAMETER, methodArguments);
			return method;
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new MethodParameterTypeNotSupported(
					userInterfaceControllerClass, EDIT_ACTION_METHOD_PARAMETER,
					methodInfo);
		}
	}

	private Object[] getProcessMethodParameterValues(Object methodOwner,
			Object methodParameter) {
		return new Object[] { methodOwner, methodInfo, methodParameter };
	}

	public void invoke(UserInterfaceController userInterfaceController,
			Object methodOwner, Object methodParameter)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Object[] parameterValues = getProcessMethodParameterValues(methodOwner,
				methodParameter);
		editActionMethodParameterMethod.invoke(userInterfaceController,
				parameterValues);
	}

}
