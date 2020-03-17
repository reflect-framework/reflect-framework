package nth.reflect.fw.generic.exception;

import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

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
		return message.translateToEnglish();
	}

	@Override
	public String getLocalizedMessage() {
		return message.translate(languageProvider);
	}
}
