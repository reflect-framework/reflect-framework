package nth.reflect.fw.generic.translatablestring;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class TranslatableString {

	private final String englishText;
	private final Object[] parameters;

	/**
	 * A {@link TranslatableString} can be used when the
	 * {@link ReflectApplication} needs to support multiple languages. It will
	 * translate the englishText using the {@link LanguageProvider} and then
	 * replace the place holders with the parameter values (if any)
	 * 
	 * @param englishText
	 *            the default text (always in English) which will be use as
	 *            lookup key to find the translated text or as default value
	 *            when no translations can be found. the englishText may contain
	 *            place holders for parameters (e.g. numbers or dates). See
	 *            {@link String#format(String, Object...)}.
	 * @param parameters
	 *            optional parameters that will replace the place holders in the
	 *            englishText
	 */
	public TranslatableString(String englishText, Object... parameters) {
		this.englishText = englishText;
		this.parameters = parameters;
	}

	public String translate(LanguageProvider languageProvider) {
		String translatedText = languageProvider.getText(englishText);
		String translatedTextWithParameters = String.format(translatedText, parameters);
		return translatedTextWithParameters;
	}

	public String translateToEnglish() {
		String translatedTextWithParameters = String.format(englishText, parameters);
		return translatedTextWithParameters;
	}

}
