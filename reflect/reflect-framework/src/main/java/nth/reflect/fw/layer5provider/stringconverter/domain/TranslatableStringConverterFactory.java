package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class TranslatableStringConverterFactory implements StringConverterFactory {

	private final LanguageProvider languageProvider;

	public TranslatableStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return TranslatableString.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		TranslatableStringConverter stringConverter = new TranslatableStringConverter(languageProvider);
		return stringConverter;
	}

}
