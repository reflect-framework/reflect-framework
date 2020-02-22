package nth.reflect.fw.generic.exception;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.Translatable;

public class TypeNotSupportedException extends ReflectTranslatableException {

	private static final long serialVersionUID = 2460695691202447353L;
	@Translatable
	private static final String TYPE_NOT_SUPPORTED_MESSAGE = "Type: %s, is not supported in class: %s";

	public TypeNotSupportedException(LanguageProvider languageProvider, Class<?> notSupportedType, Class<? > ownerClass) {
		super(languageProvider, TYPE_NOT_SUPPORTED_MESSAGE, notSupportedType, ownerClass.getCanonicalName());
	}


}
