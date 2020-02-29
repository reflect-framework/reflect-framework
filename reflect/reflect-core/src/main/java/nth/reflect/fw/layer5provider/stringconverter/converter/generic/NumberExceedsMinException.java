package nth.reflect.fw.layer5provider.stringconverter.converter.generic;

import nth.reflect.fw.layer5provider.language.translatable.Translatable;
import nth.reflect.fw.layer5provider.stringconverter.converter.NumberStringConverter;

public class NumberExceedsMinException extends StringConverterException {

	private static final long serialVersionUID = -7300055398845330240L;

	@Translatable
	private static final String MESSAGE = "%s: Value %s must be greater or equal than %s.";

	public NumberExceedsMinException(NumberStringConverter numberStringConverter, Number value, Number minValue) {
		super(numberStringConverter, MESSAGE,  value, minValue);
	}

}
