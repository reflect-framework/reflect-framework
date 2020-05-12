package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class LongStringConverter extends NumberStringConverter<Long> {

	public LongStringConverter(LanguageProvider languageProvider, String formatPattern) {
		super(languageProvider, formatPattern);
	}

	@Override
	protected Long getValue(Number number) {
		return number.longValue();
	}

}
