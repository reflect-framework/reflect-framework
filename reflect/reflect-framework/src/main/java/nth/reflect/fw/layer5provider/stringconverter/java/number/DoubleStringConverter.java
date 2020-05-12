package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class DoubleStringConverter extends NumberStringConverter<Double> {

	public DoubleStringConverter(LanguageProvider languageProvider, String formatPattern) {
		super(languageProvider, formatPattern);
	}

	@Override
	protected Double getValue(Number number) {
		return number.doubleValue();
	}

}
