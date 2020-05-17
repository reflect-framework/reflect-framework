package nth.reflect.fw.layer5provider.actionmethod.result;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodException;

public class ActionMethodResultHandlerNotFoundException extends ActionMethodException {

	private static final long serialVersionUID = -4206493834374235530L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ActionMethodResultHandlerNotFoundException.class.getCanonicalName() + ".message",
			"Could not found a %s for: %s.");

	public ActionMethodResultHandlerNotFoundException(ActionMethodInfo actionMethodInfo) {
		super(MESSAGE.withParameters(ActionMethodResultHandler.class.getSimpleName(), actionMethodInfo.toString()));
	}
}
