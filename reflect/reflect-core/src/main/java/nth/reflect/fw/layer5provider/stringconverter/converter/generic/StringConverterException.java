package nth.reflect.fw.layer5provider.stringconverter.converter.generic;

import java.text.ParseException;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.Translatable;

public class StringConverterException extends ReflectTranslatableException {

	private static final long serialVersionUID = 1870648696919941340L;

	@Translatable
	private static final String MESSAGE = "%s: Error converting value: %s";

	public StringConverterException(StringConverter stringConverter, Object value, ParseException e) {
		super(e, MESSAGE, stringConverter.getClass().getSimpleName(), value);
	}

	public StringConverterException(StringConverter stringConverter, String message, Number givenValue,
			Number valueMinOrMax) {
		super(message, stringConverter.getClass().getSimpleName(), givenValue, valueMinOrMax);
	}

}
