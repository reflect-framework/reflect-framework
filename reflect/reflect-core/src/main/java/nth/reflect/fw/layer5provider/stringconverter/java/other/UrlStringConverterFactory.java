package nth.reflect.fw.layer5provider.stringconverter.java.other;

import java.net.URL;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class UrlStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == URL.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		UrlStringConverter stringConverter = new UrlStringConverter(info.getContainer(), info.getFormatPattern());
		return stringConverter;
	}

}
