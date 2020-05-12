package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class EnumStringConverter implements StringConverter<Enum<?>> {

	private final LanguageProvider languageProvider;

	public EnumStringConverter(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
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
