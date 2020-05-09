package nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ActionMethodHasNoParameterFactoryException extends ActionMethodException {

	private static final long serialVersionUID = 8336535857046630049L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			ActionMethodHasNoParameterFactoryException.class.getCanonicalName() + ".message",
			"%s does not have a ParameterFactory annotation or method!");

	public ActionMethodHasNoParameterFactoryException(Method method) {
		super(MESSAGE, method);
	}

}
