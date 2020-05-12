package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigDecimal;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class BigDecimalStringConverterFactory implements StringConverterFactory {

	private final LanguageProvider languageProvider;

	public BigDecimalStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return BigDecimal.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		BigDecimalStringConverter stringConverter = new BigDecimalStringConverter(languageProvider, formatPattern);
		return stringConverter;
	}

}
