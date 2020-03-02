package nth.reflect.fw.layer5provider;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.Translatable;

public class ProviderNotAttainableException extends ReflectTranslatableException {

	private static final long serialVersionUID = -8235951268136959328L;

	@Translatable
	private static final String MESSAGE = "Error trying to get a provider from method: %s.%s";

	public ProviderNotAttainableException(Method applicationMethodToGetProvider, Exception exception) {
		super(exception, MESSAGE, applicationMethodToGetProvider.getDeclaringClass().getCanonicalName(),
				applicationMethodToGetProvider.getClass().getCanonicalName());
	}

	public ProviderNotAttainableException(Method applicationMethodToGetProvider) {
		super(MESSAGE, applicationMethodToGetProvider.getDeclaringClass().getCanonicalName(),
				applicationMethodToGetProvider.getName());
	}

}
