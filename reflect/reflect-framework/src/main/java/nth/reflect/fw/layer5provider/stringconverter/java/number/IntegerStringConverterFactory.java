package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class IntegerStringConverterFactory implements StringConverterFactory {

	private final LanguageProvider languageProvider;

	public IntegerStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == int.class || type == Integer.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		IntegerStringConverter stringConverter = new IntegerStringConverter(languageProvider, formatPattern);
		return stringConverter;
	}

}
