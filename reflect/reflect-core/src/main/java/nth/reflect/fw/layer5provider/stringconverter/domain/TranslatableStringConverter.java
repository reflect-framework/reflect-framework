package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class TranslatableStringConverter extends StringConverter<TranslatableString> {

	private final LanguageProvider languageProvide;

	public TranslatableStringConverter(StringConverterFactoryInfo info) {
		super(info);
		languageProvide = container.get(LanguageProvider.class);
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
