package nth.reflect.fw.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.Method;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class EditParameterMethodFactory {

	private static final String EDIT_ACTION_METHOD_PARAMETER = "editActionMethodParameter";

	public static Method create(Class<? extends UserInterfaceController> controllerClass,
			ExecutionModeType executionMode, Method actionMethod, TypeInfo firstParameterTypeInfo) {
		if (executionMode != ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL) {
			return null;
		}
		Method editMethod = findEditParameterMethod(controllerClass, actionMethod, firstParameterTypeInfo);
		return editMethod;
	}

	private static Method findEditParameterMethod(Class<? extends UserInterfaceController> controllerClass,
			Method actionMethod, TypeInfo firstParameterInfo) {
		Class<?> parameterType = firstParameterInfo.getType();
		Class<?>[] parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, parameterType };
		try {
			Method method = findMethod(controllerClass, parameterTypes, actionMethod);
			return method;
		} catch (Exception e) {
			// method with specific parameter type not found found!
			// try to find a method that takes a domainObject as argument
			if (firstParameterInfo.isDomainClass()) {
				parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, Object.class };
				Method method = findMethod(controllerClass, parameterTypes, actionMethod);
				return method;
			} else {
				throw new MethodParameterTypeNotSupported(controllerClass, EDIT_ACTION_METHOD_PARAMETER, actionMethod);
			}
		}
	}

	private static Method findMethod(Class<? extends UserInterfaceController> controllerClass,
			Class<?>[] parameterTypes, Method actionMethod) {
		try {
			Method method = controllerClass.getMethod(EDIT_ACTION_METHOD_PARAMETER, parameterTypes);
			return method;
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new MethodParameterTypeNotSupported(controllerClass, EDIT_ACTION_METHOD_PARAMETER, actionMethod);
		}
	}

}
