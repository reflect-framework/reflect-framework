package nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ActionMethodMayNotBeGetterMethodException extends ActionMethodException {

	private static final long serialVersionUID = -7149042837568162931L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ActionMethodMayNotBeGetterMethodException.class.getCanonicalName() + ".message",
			"ActionMethod: %s may not be a getter method");

	public ActionMethodMayNotBeGetterMethodException(Method method) {
		super(MESSAGE, method);
	}
}
