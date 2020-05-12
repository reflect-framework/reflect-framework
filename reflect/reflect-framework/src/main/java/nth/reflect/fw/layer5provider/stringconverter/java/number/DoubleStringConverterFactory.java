package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class DoubleStringConverterFactory implements StringConverterFactory {

	private final LanguageProvider languageProvider;

	public DoubleStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == double.class || type == Double.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		DoubleStringConverter stringConverter = new DoubleStringConverter(languageProvider, formatPattern);
		return stringConverter;
	}

}
