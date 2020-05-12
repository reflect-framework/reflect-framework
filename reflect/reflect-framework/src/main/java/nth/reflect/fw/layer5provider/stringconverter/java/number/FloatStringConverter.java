package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class FloatStringConverter extends NumberStringConverter<Float> {

	public FloatStringConverter(LanguageProvider languageProvider, String formatPattern) {
		super(languageProvider, formatPattern);
	}

	@Override
	protected Float getValue(Number number) {
		return number.floatValue();
	}

}
