package nth.reflect.fw.layer5provider.reflection.info.userinterfacemethod;

import java.lang.reflect.Method;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodException;

public class MethodReturnTypeNotSupported extends ActionMethodException {

	private static final long serialVersionUID = 6100808275699979120L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			MethodReturnTypeNotSupported.class.getCanonicalName() + ".message",
			"Action method: %s can not be processed because it's return type: "
					+ "%s is not supported (the %s class does not contain a method: %s with this parameter type)");

	public MethodReturnTypeNotSupported(Class<? extends UserInterfaceController> userInterfaceControllerClass,
			String processMethodName, Method actionMethod) {
		super(MESSAGE.withParameters(ActionMethodInfo.createCanonicalName(actionMethod),
				actionMethod.getReturnType() == null ? "null" : actionMethod.getReturnType().getCanonicalName(),
				userInterfaceControllerClass.getCanonicalName(), processMethodName));
	}

}
