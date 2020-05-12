package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class BooleanStringConverterFactory implements StringConverterFactory {

	private LanguageProvider languageProvider;

	public BooleanStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == boolean.class || type == Boolean.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		BooleanStringConverter stringConverter = new BooleanStringConverter(languageProvider);
		return stringConverter;
	}

}
