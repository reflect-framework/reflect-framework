package nth.reflect.fw.layer5provider.stringconverter.generic;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class StringConverterException extends ReflectTranslatableException {

	private static final long serialVersionUID = 1870648696919941340L;
	private static final TranslatableString MESSAGE = new TranslatableString(
			StringConverterException.class.getCanonicalName() + ".message", "%s: Error converting value: %s");

	public StringConverterException(StringConverter stringConverter, Object value, Exception e) {
		super(MESSAGE.withParameters(stringConverter.getClass().getSimpleName(), value), e);
	}

	public StringConverterException(StringConverter stringConverter, Object value) {
		super(MESSAGE.withParameters(stringConverter.getClass().getSimpleName(), value));
	}

	public StringConverterException(TranslatableString message) {
		super(message);
	}

}
