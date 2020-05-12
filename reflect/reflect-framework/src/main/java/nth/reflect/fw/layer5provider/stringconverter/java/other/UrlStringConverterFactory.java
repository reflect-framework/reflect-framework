package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.net.URL;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class UrlStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == URL.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		UrlStringConverter stringConverter = new UrlStringConverter();
		return stringConverter;
	}

}
