package nth.reflect.fw.layer5provider.language.translatable;

import java.lang.reflect.Field;

import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguagePropertyFile;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

/**
 * A {@link TranslatableException} will show the translated error message using
 * the {@link LanguageProvider} and then replace the place holders (e.g. %s)
 * with the parameter values (if any). {@link TranslatableException}s need to
 * define a {@link TranslatableString}. {@link TranslatableString}s are best
 * created as a static {@link Field}, so that the {@link ReflectUtilMavenPlugin}
 * can automatically find all {@link TranslatableString}s in an application and
 * and update {@link LanguagePropertyFile}s.
 */
public class TranslatableException extends RuntimeException {

	private static final long serialVersionUID = 6799738456356756550L;
	private LanguageProvider languageProvider;
	private final TranslatableString message;

	public TranslatableException(TranslatableString message) {
		super();
		this.languageProvider = new DefaultLanguageProvider();
		this.message = message;
	}

	public TranslatableException(TranslatableString message, Throwable cause) {
		super(cause);
		this.languageProvider = new DefaultLanguageProvider();
		this.message = message;
	}

	public void setLanguageProvider(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public String getMessage() {
		return message.getDefaultEnglish();
	}

	@Override
	public String getLocalizedMessage() {
		return message.getTranslation(languageProvider);
	}
}
