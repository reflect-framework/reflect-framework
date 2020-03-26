package nth.reflect.fw.layer5provider.language.translatable;

import java.lang.reflect.Field;
import java.util.Properties;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class TranslatableString {

	protected final String key;
	private final String defaultEnglish;
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
	 * @param key                the key that is used in property files to look up a
	 *                           text in a language other than English
	 * @param defaultEnglish English text which will be used as default when no
	 *                           translations can be found, or when creating
	 *                           language property files. This text is directly
	 *                           derived from the code base. Text may contain place
	 *                           holders for parameters (e.g. numbers or dates). See
	 *                           {@link String#format(String, Object...)}.
	 * @param parameters         optional parameters that will replace the place
	 *                           holders in the englishText
	 */
	public TranslatableString(String key, String defaultEnglish, Object... parameters) {
		this.key = key;
		this.defaultEnglish = defaultEnglish;
		this.parameters = parameters;
	}

	public TranslatableString withParameters(Object... parameters) {
		return new TranslatableString(key, defaultEnglish, parameters);
	}

	public String getTranslation(LanguageProvider languageProvider) {
		String translatedText = languageProvider.getText(key, defaultEnglish);
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
				String translatedString = translatableString.getTranslation(languageProvider);
				translatedParameters[i] = translatedString;
			} else {
				translatedParameters[i] = parameter;
			}
		}
		return translatedParameters;
	}

	public String getDefaultEnglish() {
		Object[] englishParameters = getDefaultEnglishParameters();
		String translatedTextWithParameters = String.format(defaultEnglish, englishParameters);
		return translatedTextWithParameters;
	}

	private Object[] getDefaultEnglishParameters() {
		Object translatedParameters[] = new Object[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			Object parameter = parameters[i];
			if (parameter instanceof TranslatableString) {
				TranslatableString translatableString = (TranslatableString) parameter;
				String englishString = translatableString.getDefaultEnglish();
				translatedParameters[i] = englishString;
			} else {
				translatedParameters[i] = parameter;
			}
		}
		return translatedParameters;
	}

	@Override
	public String toString() {
		return getDefaultEnglish();
	}

	public String getKey() {
		return key;
	}

	public TranslatableString append(String appendix) {
		return new TranslatableString("", "%s%s", this, appendix);
	}

}
