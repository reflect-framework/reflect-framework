package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class EnumStringConverterFactory implements StringConverterFactory {

	private final LanguageProvider languageProvider;

	public EnumStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		boolean isEnum = typeInfo.isEnum();
		return isEnum;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String format) {
		EnumStringConverter stringConverter = new EnumStringConverter(languageProvider);
		return stringConverter;
	}

}
