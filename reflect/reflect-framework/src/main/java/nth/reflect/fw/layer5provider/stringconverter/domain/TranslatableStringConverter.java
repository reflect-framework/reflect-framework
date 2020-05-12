package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class TranslatableStringConverter implements StringConverter<TranslatableString> {

	private final LanguageProvider languageProvide;

	public TranslatableStringConverter(LanguageProvider languageProvide) {
		this.languageProvide = languageProvide;
	}

	@Override
	public String toString(TranslatableString translatableString) {
		if (translatableString == null) {
			return "";
		}
		String translated = translatableString.getTranslation(languageProvide);
		return translated;
	}

	@Override
	public TranslatableString fromString(String value) {
		throw new MethodNotSupportedException(TranslatableStringConverter.class.getCanonicalName() + ".fromString()");
	}

}
