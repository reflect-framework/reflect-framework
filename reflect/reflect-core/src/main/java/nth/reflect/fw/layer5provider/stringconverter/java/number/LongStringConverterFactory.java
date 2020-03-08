package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

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
