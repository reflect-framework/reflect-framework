package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class ByteStringConverterFactory implements StringConverterFactory {

	private final LanguageProvider languageProvider;

	public ByteStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == byte.class || type == Byte.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		ByteStringConverter stringConverter = new ByteStringConverter(languageProvider, formatPattern);
		return stringConverter;
	}

}
