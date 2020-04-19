package nth.reflect.fw.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodException;

public class MethodParameterTypeNotSupported extends ActionMethodException {

	private static final long serialVersionUID = -6178835316096085356L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			MethodParameterTypeNotSupported.class.getCanonicalName() + ".message",
			"Action method: %s can not be processed because it's parameter type: %s "
					+ "is not supported (the %s class does not contain a method: %s with this parameter type)");

	public MethodParameterTypeNotSupported(Class<? extends UserInterfaceController> controllerClass,
			String processMethodName, Method actionMethod) {
		super(MESSAGE
				.withParameters(MethodCanonicalName.getFor(actionMethod), getParameterType(actionMethod),
						getParameterType(actionMethod).getCanonicalName(), controllerClass.getCanonicalName(),
						processMethodName));
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

}
