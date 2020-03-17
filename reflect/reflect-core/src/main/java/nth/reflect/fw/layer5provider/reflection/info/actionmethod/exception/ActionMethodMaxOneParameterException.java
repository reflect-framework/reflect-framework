package nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ActionMethodMaxOneParameterException extends ActionMethodException {

	private static final long serialVersionUID = -1386603357738923117L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ActionMethodMaxOneParameterException.class.getCanonicalName() + ".message",
			"ActionMethod: %s has more than 1 parameter");

	public ActionMethodMaxOneParameterException(Method method) {
		super(MESSAGE, method);
	}

}
