package nth.reflect.fw.layer5provider.language.translatable;

import java.lang.reflect.Field;
import java.util.Properties;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class TranslatableString {

	private final String key;
	private final String englishText;
	private final Object[] parameters;

	/**
	 * A {@link TranslatableString} can be used when the {@link ReflectApplication}
	 * needs to support multiple languages. It will translate the englishText using
	 * the {@link LanguageProvider} and then replace the place holders with the
	 * parameter values (if any). {@link TranslatableString} can best be implemented
	 * by creating a {@link Field} of type {@link TranslatableString} without
	 * parameters, so a translation tool can automatically find all
	 * {@link TranslatableString}s in an application and create and or update
	 * {@link LanguageProvider} {@link Properties} files.
	 * 
	 * @param key         the key that is used in property files to look up a text
	 *                    in a language other than English
	 * @param englishText the default text (always in English) which will be use as
	 *                    lookup key to find the translated text or as default value
	 *                    when no translations can be found. the englishText may
	 *                    contain place holders for parameters (e.g. numbers or
	 *                    dates). See {@link String#format(String, Object...)}.
	 * @param parameters  optional parameters that will replace the place holders in
	 *                    the englishText
	 */
	public TranslatableString(String key, String englishText, Object... parameters) {
		this.key = key;
		this.englishText = englishText;
		this.parameters = parameters;
	}

	public TranslatableString withParameters(Object... parameters) {
		return new TranslatableString(key, englishText, parameters);
	}

	public String translate(LanguageProvider languageProvider) {
		String translatedText = languageProvider.getText(key, englishText);
		Object[] translatedParameters = getTranslatedParameters(languageProvider);
		String translatedTextWithParameters = String.format(translatedText, translatedParameters);
		return translatedTextWithParameters;
	}

	private Object[] getTranslatedParameters(LanguageProvider languageProvider) {
		Object translatedParameters[] = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			Object parameter = parameters[i];
			if (parameter instanceof TranslatableString) {
				TranslatableString translatableString = (TranslatableString) parameter;
				String translatedString = translatableString.translate(languageProvider);
				translatedParameters[i] = translatedString;
			} else {
				translatedParameters[i] = parameter;
			}
		}
		return translatedParameters;
	}

	public String translateToEnglish() {
		Object[] englishParameters = getEnglishParameters();
		String translatedTextWithParameters = String.format(englishText, englishParameters);
		return translatedTextWithParameters;
	}

	private Object[] getEnglishParameters() {
		Object translatedParameters[] = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			Object parameter = parameters[i];
			if (parameter instanceof TranslatableString) {
				TranslatableString translatableString = (TranslatableString) parameter;
				String englishString = translatableString.translateToEnglish();
				translatedParameters[i] = englishString;
			} else {
				translatedParameters[i] = parameter;
			}
		}
		return translatedParameters;
	}

	@Override
	public String toString() {
		return translateToEnglish();
	}

	public String getKey() {
		return key;
	}

	public TranslatableString append(String appendix) {
		return new TranslatableString("", "%s%s", this, appendix);
	}

}
