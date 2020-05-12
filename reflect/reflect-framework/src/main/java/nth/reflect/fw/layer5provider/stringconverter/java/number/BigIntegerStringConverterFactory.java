package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigInteger;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class BigIntegerStringConverterFactory implements StringConverterFactory {

	private final LanguageProvider languageProvider;

	public BigIntegerStringConverterFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return BigInteger.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		BigIntegerStringConverter stringConverter = new BigIntegerStringConverter(languageProvider, formatPattern);
		return stringConverter;
	}

}
