package nth.reflect.fw.layer5provider.actionmethodexecution.result.depricated;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodException;

/**
 * @deprecated see {@link DeprecatedActionMethodResultHandler}
 * @author nilsth
 *
 */
@Deprecated
public class DeprecatedMethodReturnTypeNotSupported extends ActionMethodException {

	private static final long serialVersionUID = 6100808275699979120L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			DeprecatedMethodReturnTypeNotSupported.class.getCanonicalName() + ".message",
			"Action method: %s can not be processed because it's return type: "
					+ "%s is not supported (the %s class does not contain a method: %s with this parameter type)");

	public DeprecatedMethodReturnTypeNotSupported(Class<? extends UserInterfaceController> userInterfaceControllerClass,
			String processMethodName, Method actionMethod) {
		super(MESSAGE
				.withParameters(MethodCanonicalName.getFor(actionMethod),
						actionMethod.getReturnType() == null ? "null" : actionMethod.getReturnType().getCanonicalName(),
						userInterfaceControllerClass.getCanonicalName(), processMethodName));
	}

}
