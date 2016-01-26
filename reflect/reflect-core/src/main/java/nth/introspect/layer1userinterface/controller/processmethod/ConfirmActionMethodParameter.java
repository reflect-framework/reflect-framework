package nth.introspect.layer1userinterface.controller.processmethod;

import java.lang.reflect.Method;

import nth.introspect.generic.util.TypeUtil;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.reflection.MethodParameterTypeNotSupported;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ConfirmActionMethodParameter extends ProcessMethod {

	private final static String CONFIRM_ACTION_METHOD_PARAMETER="confirmActionMethodParameter";
	private static final Class<?> NO_PARAMETER = null;
	private UserInterfaceController userInterfaceController;
	private Object methodOwner;
	private ActionMethodInfo methodInfo;
	private Object methodParameter;

	public ConfirmActionMethodParameter(UserInterfaceController userInterfaceController, Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		this.userInterfaceController = userInterfaceController;
		this.methodOwner = methodOwner;
		this.methodInfo = methodInfo;
		this.methodParameter = methodParameter;
		
	}
	
	@Override
	protected Method getProcessMethod() {

		Class<?> parameterType = methodInfo.getParameterType().getType();
		if (parameterType==NO_PARAMETER) {
			parameterType=Object.class;
		}
		Class<?>[] parameterTypes = new Class[] { Object.class,
				ActionMethodInfo.class, parameterType };
		try {
			Method method = findMethod( parameterTypes);
			return method;
		} catch (Exception e) {
			// method with specific parameter type not found found!
			// try to find a method that takes a domainObject as argument
			if (TypeUtil.isDomainType(parameterType)) {
				parameterTypes = new Class[] { Object.class,
						ActionMethodInfo.class, Object.class };
				Method method = findMethod( parameterTypes);
				return method;
			} else {
				throw new MethodParameterTypeNotSupported(userInterfaceController,
						CONFIRM_ACTION_METHOD_PARAMETER, methodInfo);
			}
		}

	}

	private Method findMethod(Class<?>[] methodArguments) {
		try {
			Method method = userInterfaceController.getClass().getMethod(
					CONFIRM_ACTION_METHOD_PARAMETER, methodArguments);
			return method;
		} catch (NoSuchMethodException | SecurityException e1) {
			throw new MethodParameterTypeNotSupported(userInterfaceController,
					CONFIRM_ACTION_METHOD_PARAMETER, methodInfo);
		}
	}
	
	@Override
	protected Class<?>[] getProcessMethodParameterTypes() {
		return new Class<?>[] {Object.class, ActionMethodInfo.class, methodInfo.getParameterType().getType()};
	}

	@Override
	protected Object[] getProcessMethodParameterValues() {
		return new Object[] {methodOwner, methodInfo, methodParameter};
	}

	@Override
	protected UserInterfaceController getUserInterfaceController() {
		return userInterfaceController;
	}

}
