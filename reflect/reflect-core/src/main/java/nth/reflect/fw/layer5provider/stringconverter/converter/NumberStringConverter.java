package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterException;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;

public abstract class NumberStringConverter<T extends Number> extends StringConverter<T>  {

	private final DecimalFormat decimalFormat;

	public NumberStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
		decimalFormat=createDecimalFormat();
	}

	private DecimalFormat createDecimalFormat() {
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
	public String toString(T value) {
		if (value == null) {
			return "";
		}

		return decimalFormat.format(value);
	}

	@Override
	public T fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			Number number = decimalFormat.parse(value);
			return getValue(number);
		} catch (ParseException e) {
			throw new StringConverterException(this, value, e);
		}
	}

	protected abstract T getValue(Number number);

}
