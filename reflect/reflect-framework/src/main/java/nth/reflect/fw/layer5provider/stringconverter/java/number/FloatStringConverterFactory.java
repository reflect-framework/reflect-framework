package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class FloatStringConverterFactory implements StringConverterFactory {
	private final LanguageProvider languageProvider;

	public FloatStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == float.class || type == Float.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		FloatStringConverter stringConverter = new FloatStringConverter(languageProvider, formatPattern);
		return stringConverter;
	}

}
