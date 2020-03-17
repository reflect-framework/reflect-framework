package nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ActionMethodMayNotBeBehavioralMethodException extends ActionMethodException {

	private static final long serialVersionUID = -336634551002382804L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ActionMethodMayNotBeBehavioralMethodException.class.getCanonicalName() + ".message",
			"ActionMethod: %s may not be a BehavioralMethod");

	public ActionMethodMayNotBeBehavioralMethodException(Method method) {
		super(MESSAGE, method);
	}

}
