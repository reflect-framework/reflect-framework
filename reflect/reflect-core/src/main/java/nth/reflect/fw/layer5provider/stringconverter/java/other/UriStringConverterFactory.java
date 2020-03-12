package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.net.URI;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class UriStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == URI.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		UriStringConverter stringConverter = new UriStringConverter(info);
		return stringConverter;
	}

}
