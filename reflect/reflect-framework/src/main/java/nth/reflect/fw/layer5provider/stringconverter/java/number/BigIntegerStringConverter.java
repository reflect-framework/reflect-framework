package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigInteger;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class BigIntegerStringConverter extends NumberStringConverter<BigInteger> {

	public BigIntegerStringConverter(LanguageProvider languageProvider, String formatPattern) {
		super(languageProvider, formatPattern);
	}

	@Override
	protected BigInteger getValue(Number number) {
		return new BigInteger(number.toString());
	}

}
