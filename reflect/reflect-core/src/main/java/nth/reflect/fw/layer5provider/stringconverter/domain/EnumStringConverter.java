package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.generic.exception.MethodNotSupportedException;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class EnumStringConverter extends StringConverter<Enum<?>> {

	private LanguageProvider languageProvider;

	public EnumStringConverter(StringConverterFactoryInfo info) {
		super(info);
		languageProvider = container.get(LanguageProvider.class);
	}

	@Override
	public String toString(Enum<?> value) {
		if (value == null) {
			return "";
		}
		String text = enumToString(languageProvider, value);
		return text;
	}

	public static String enumToString(LanguageProvider languageProvider, Enum<?> value) {
		String key = languageProvider.getKey(value);
		String defaultValue = StringUtil.eliphantCaseToNormal(value.toString());
		String text = languageProvider.getText(key, defaultValue);
		return text;
	}

	@Override
	public Enum<?> fromString(String value) {
		throw new MethodNotSupportedException(EnumStringConverter.class.getCanonicalName() + ".fromString()");
	}

}
