package nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ActionMethodMayNotBeStaticException extends ActionMethodException {

	private static final long serialVersionUID = -3910060083387268678L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ActionMethodMayNotBeStaticException.class.getCanonicalName() + ".message",
			"ActionMethod: %s may not be be static");

	public ActionMethodMayNotBeStaticException(Method method) {
		super(MESSAGE, method);
	}

}
