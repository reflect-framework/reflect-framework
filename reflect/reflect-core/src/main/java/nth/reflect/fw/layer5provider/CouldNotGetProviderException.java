package nth.reflect.fw.layer5provider;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.Translatable;

public class CouldNotGetProviderException extends ReflectTranslatableException {

	private static final long serialVersionUID = -8235951268136959328L;

	@Translatable
	private static final String MESSAGE = "Error trying to get a provider from method: %s.%s";

	public CouldNotGetProviderException(LanguageProvider languageProvider, Method applicationMethodToGetProvider,
			Exception exception) {
		super(languageProvider, exception, MESSAGE, applicationMethodToGetProvider.getDeclaringClass().getCanonicalName(),applicationMethodToGetProvider.getClass().getCanonicalName());
	}

	public CouldNotGetProviderException(LanguageProvider languageProvider, Method applicationMethodToGetProvider) {
		super(languageProvider,  MESSAGE, applicationMethodToGetProvider.getDeclaringClass().getCanonicalName(), applicationMethodToGetProvider.getName());
	}

}
