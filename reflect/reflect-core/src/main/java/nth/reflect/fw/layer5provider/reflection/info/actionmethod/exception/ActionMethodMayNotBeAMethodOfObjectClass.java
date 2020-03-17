package nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ActionMethodMayNotBeAMethodOfObjectClass extends ActionMethodException {

	private static final long serialVersionUID = -7791749588582891471L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ActionMethodMayNotBeAMethodOfObjectClass.class.getCanonicalName() + ".message",
			"ActionMethod: %s may not be a method of the Object Class");

	public ActionMethodMayNotBeAMethodOfObjectClass(Method method) {
		super(MESSAGE, method);
	}
}
