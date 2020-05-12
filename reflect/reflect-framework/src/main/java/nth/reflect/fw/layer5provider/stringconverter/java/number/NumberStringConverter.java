package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public abstract class NumberStringConverter<T extends Number> implements StringConverter<T> {

	private final NumberFormat decimalFormat;
	private final LanguageProvider languageProvider;

	public NumberStringConverter(LanguageProvider languageProvider, String formatPattern) {
		this.languageProvider = languageProvider;
		this.decimalFormat = createDecimalFormat(formatPattern);
	}

	private NumberFormat createDecimalFormat(String formatPattern) {
		Locale locale = languageProvider.getDefaultLocale();
		if (StringUtil.isNullOrEmpty(formatPattern)) {
			return DecimalFormat.getNumberInstance(locale);
		} else {
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
			return new DecimalFormat(formatPattern, symbols);
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
