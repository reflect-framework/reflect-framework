package nth.introspect.layer1userinterface.controller.processmethod;

import java.lang.reflect.Method;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.MethodReturnTypeNotSupported;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ShowActionMethodResult extends ProcessMethod {

	private final static String SHOW_ACTION_METHOD_RESULT = "showActionMethodResult";
	private static final Class<?> VOID_TYPE = null;
	private UserInterfaceController userInterfaceController;
	private Object methodOwner;
	private ActionMethodInfo methodInfo;
	private Object methodParameter;
	private Object methodResult;

	public ShowActionMethodResult(
			UserInterfaceController userInterfaceController,
			Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		this.userInterfaceController = userInterfaceController;
		this.methodOwner = methodOwner;
		this.methodInfo = methodInfo;
		this.methodParameter = methodParameter;
		this.methodResult = methodResult;

	}

	@Override
	protected Method getProcessMethod() {

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
			Method method = findMethod(parameterTypes);
			return method;
		} catch (Exception e) {
			// method with specific result type not found found!
			// try to find a method that takes a domainObject as argument
			if (TypeUtil.isDomainType(returnType)) {
				parameterTypes = new Class[] { Object.class,
						ActionMethodInfo.class, Object.class, Object.class };
				Method method = findMethod(parameterTypes);
				return method;
			} else {
				throw new MethodReturnTypeNotSupported(userInterfaceController,
						SHOW_ACTION_METHOD_RESULT, methodInfo);
			}
		}

	}

	private Method findMethod(Class<?>[] methodArguments) {
		try {
			Method method = userInterfaceController.getClass().getMethod(
					SHOW_ACTION_METHOD_RESULT, methodArguments);
			return method;
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new MethodReturnTypeNotSupported(userInterfaceController,
					SHOW_ACTION_METHOD_RESULT, methodInfo);
		}
	}

	@Override
	protected Class<?>[] getProcessMethodParameterTypes() {
		return new Class<?>[] { Object.class, ActionMethodInfo.class,
				methodInfo.getParameterType().getType() };
	}

	@Override
	protected Object[] getProcessMethodParameterValues() {
		return new Object[] { methodOwner, methodInfo, methodParameter,
				methodResult };
	}

	@Override
	protected UserInterfaceController getUserInterfaceController() {
		return userInterfaceController;
	}

}
