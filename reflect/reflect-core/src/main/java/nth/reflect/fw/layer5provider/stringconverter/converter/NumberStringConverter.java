package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.stringconverter.abstractconverter.StringConversionException;
import nth.reflect.fw.layer5provider.stringconverter.abstractconverter.StringConverter;

public class NumberStringConverter extends StringConverter  {

	private final DecimalFormat decimalFormat;

	public NumberStringConverter(DependencyInjectionContainer container, Optional<String> formatPattern) {
		super(container, formatPattern);
		decimalFormat=createDecimalFormat(container, formatPattern);
	}

	private DecimalFormat createDecimalFormat(DependencyInjectionContainer container, Optional<String> formatPattern) {
		if (formatPattern.isPresent()) {
			LanguageProvider languageProvider = container.get(LanguageProvider.class);
			Locale locale = languageProvider.getDefaultLocale();
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
			return new DecimalFormat(formatPattern.get(), symbols);
		} else {
			return new DecimalFormat();
		}
	}

	@Override
	public String toString(Object value) {
		if (value == null) {
			return "";
		}

		return decimalFormat.format(value);
	}

	@Override
	public Object fromStringConverter(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			Number number = decimalFormat.parse(value);
			return number;
		} catch (ParseException e) {
			throw new StringConversionException(e);
		}
	}

}
