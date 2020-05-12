package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.net.URI;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class UriStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == URI.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		UriStringConverter stringConverter = new UriStringConverter();
		return stringConverter;
	}

}
