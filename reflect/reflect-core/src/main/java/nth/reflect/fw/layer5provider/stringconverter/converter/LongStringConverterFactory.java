package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class LongStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == long.class || type == Long.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		LongStringConverter stringConverter = new LongStringConverter(info.getContainer(), info.getFormatPattern());
		return stringConverter;
	}

}
