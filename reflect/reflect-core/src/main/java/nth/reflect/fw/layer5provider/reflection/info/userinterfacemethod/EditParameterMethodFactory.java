package nth.reflect.fw.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.Method;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.FirstParameterTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class EditParameterMethodFactory {

	private static final String EDIT_ACTION_METHOD_PARAMETER = "editActionMethodParameter";

	public static Method create(ReflectApplication application,
			Class<? extends UserInterfaceController> controllerClass, ExecutionModeType executionMode,
			Method actionMethod) {
		if (executionMode != ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL) {
			return null;
		}
		Method editMethod = findEditParameterMethod(application, controllerClass, actionMethod);
		return editMethod;
	}

	private static Method findEditParameterMethod(ReflectApplication application,
			Class<? extends UserInterfaceController> controllerClass, Method actionMethod) {
		Class<?> parameterType = actionMethod.getParameterTypes()[0];
		Class<?>[] parameterTypes = new Class[] { Object.class, ActionMethodInfo.class, parameterType };
		try {
			Method method = findMethod(controllerClass, parameterTypes, actionMethod);
			return method;
		} catch (Exception e) {
			// method with specific parameter type not found found!
			// try to find a method that takes a domainObject as argument
			TypeInfo typeInfo = new FirstParameterTypeInfo(application, actionMethod);
			if (typeInfo.isDomainClass()) {
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
