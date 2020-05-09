package nth.reflect.fw.layer5provider.language.translatable;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class TranslatedString extends TranslatableString {

	public final LanguageProvider languageProvider;

	public TranslatedString(LanguageProvider languageProvider, String key, String defaultEnglish, Object... parameters) {
		super(key, defaultEnglish, parameters);
		this.languageProvider = languageProvider;
	}

	public String getTranslation() {
		return getTranslation(languageProvider);
	}

	@Override
	public String toString() {
		return getTranslation();
	}
}
