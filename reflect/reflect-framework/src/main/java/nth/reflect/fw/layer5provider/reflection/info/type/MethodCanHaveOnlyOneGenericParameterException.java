package nth.reflect.fw.layer5provider.reflection.info.type;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.util.MethodCanonicalName;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class MethodCanHaveOnlyOneGenericParameterException extends TranslatableException {

	private static final long serialVersionUID = 1060065585686328118L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			MethodCanHaveOnlyOneGenericParameterException.class.getCanonicalName() + ".message",
			"Method: %s may have only one generic parameter if you want to create a: %s");

	public MethodCanHaveOnlyOneGenericParameterException(Method method) {
		super(MESSAGE.withParameters(MethodCanonicalName.getFor(method)));
	}

}
