package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigDecimal;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class BigDecimalStringConverter extends NumberStringConverter<BigDecimal> {

	public BigDecimalStringConverter(LanguageProvider languageProvider, String formatPattern) {
		super(languageProvider, formatPattern);
	}

	@Override
	protected BigDecimal getValue(Number number) {
		return new BigDecimal(number.toString());
	}

}
