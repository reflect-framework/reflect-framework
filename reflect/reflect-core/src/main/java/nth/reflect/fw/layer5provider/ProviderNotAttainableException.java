package nth.reflect.fw.layer5provider;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class ProviderNotAttainableException extends ReflectTranslatableException {

	private static final long serialVersionUID = -8235951268136959328L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			ProviderNotAttainableException.class.getCanonicalName() + ".message",
			"Error trying to get a provider from method: %s.%s");

	public ProviderNotAttainableException(Method applicationMethodToGetProvider, Exception exception) {
		super(MESSAGE.withParameters(applicationMethodToGetProvider.getDeclaringClass().getCanonicalName(),
				applicationMethodToGetProvider.getClass().getCanonicalName()), exception);
	}

	public ProviderNotAttainableException(Method applicationMethodToGetProvider) {
		super(MESSAGE.withParameters(applicationMethodToGetProvider.getDeclaringClass().getCanonicalName(),
				applicationMethodToGetProvider.getName()));
	}

}
