package nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ActionMethodMayNotBeSetterMethodException extends ActionMethodException {

	private static final long serialVersionUID = -7074915357044549746L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ActionMethodMayNotBeSetterMethodException.class.getCanonicalName() + ".message",
			"ActionMethod: %s may not be a setter method");

	public ActionMethodMayNotBeSetterMethodException(Method method) {
		super(MESSAGE, method);
	}
}
