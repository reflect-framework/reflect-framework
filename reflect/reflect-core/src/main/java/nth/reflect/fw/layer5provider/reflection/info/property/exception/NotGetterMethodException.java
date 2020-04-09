package nth.reflect.fw.layer5provider.reflection.info.property.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class NotGetterMethodException extends TranslatableException {

	private static final long serialVersionUID = 478688934744023795L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			NotGetterMethodException.class.getCanonicalName() + ".message", "Method: %s is not a getter method.");

	public NotGetterMethodException(Method method) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(method)));
	}

}
