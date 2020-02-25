package nth.reflect.fw.layer5provider.stringconverter.abstractconverter;

import java.text.ParseException;

public class StringConversionException extends RuntimeException {

	private static final long serialVersionUID = 1870648696919941340L;

	public StringConversionException(ParseException e) {
		super(e);//TODO add a understandable description and extend ReflectTranslatableException
	}

}
