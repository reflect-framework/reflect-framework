package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.util.Locale;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class BooleanStringConverter implements StringConverter<Boolean> {

	private static final TranslatableString TRUE = new TranslatableString(
			BooleanStringConverter.class.getCanonicalName() + ".true", "True");
	private static final TranslatableString FALSE = new TranslatableString(
			BooleanStringConverter.class.getCanonicalName() + ".false", "False");

	private final LanguageProvider languageProvider;

	public BooleanStringConverter(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public String toString(Boolean value) {
		if (value == null) {
			return "";
		}
		if (value) {
			return languageProvider.getText(TRUE);
		} else {
			return languageProvider.getText(FALSE);
		}
	}

	@Override
	public Boolean fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		String trueText;
		String falseText;
		if (languageProvider.getDefaultLocale() != Locale.ENGLISH) {
			trueText = languageProvider.getText(TRUE);
			falseText = languageProvider.getText(FALSE);
			if (value.equalsIgnoreCase(trueText)) {
				return true;
			}
			if (value.equalsIgnoreCase(falseText)) {
				return false;
			}
		}
		trueText = Boolean.toString(true);
		falseText = Boolean.toString(false);
		if (value.equalsIgnoreCase(trueText)) {
			return true;
		}
		if (value.equalsIgnoreCase(falseText)) {
			return false;
		}

		throw new StringConverterException(this, value);
	}

}
