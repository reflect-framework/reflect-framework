package nth.reflect.fw.layer5provider.actionmethodexecution;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodException;

public class NoActionMethodResultHandlerFoundException extends ActionMethodException {

	private static final long serialVersionUID = -4206493834374235530L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			NoActionMethodResultHandlerFoundException.class.getCanonicalName() + ".message",
			"Could not found a %s for: %s.");

	public NoActionMethodResultHandlerFoundException(ActionMethodInfo actionMethodInfo) {
		super(MESSAGE.withParameters(ActionMethodResultHandler.class.getSimpleName(), actionMethodInfo.toString()));
	}
}
