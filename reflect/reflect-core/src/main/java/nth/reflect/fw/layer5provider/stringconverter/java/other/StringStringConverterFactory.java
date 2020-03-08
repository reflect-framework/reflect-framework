package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class StringStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == String.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		StringStringConverter stringConverter = new StringStringConverter(info.getContainer(), info.getFormatPattern());
		return stringConverter;
	}

}
