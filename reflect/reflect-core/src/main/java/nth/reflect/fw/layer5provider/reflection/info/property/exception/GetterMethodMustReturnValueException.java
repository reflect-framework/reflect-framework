package nth.reflect.fw.layer5provider.reflection.info.property.exception;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class GetterMethodMustReturnValueException extends TranslatableException {

	private static final long serialVersionUID = 478688934744023795L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			GetterMethodMustReturnValueException.class.getCanonicalName() + ".message",
			"Getter method: %s may not contain a parameter.");

	public GetterMethodMustReturnValueException(Method method) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(method)));
	}

}
