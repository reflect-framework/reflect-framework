package nth.reflect.fw.layer5provider.validation;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ValidationMethodInvokenationException extends TranslatableException {

	private static final long serialVersionUID = -9046908889355740168L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ValidationMethodInvokenationException.class.getCanonicalName() + ".message",
			"Error invoking validation method: %s");

	public ValidationMethodInvokenationException(Method validationMethod, Throwable throwable) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(validationMethod)), throwable);
	}

}
