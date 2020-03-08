package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class FloatStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == float.class || type == Float.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		FloatStringConverter stringConverter = new FloatStringConverter(info.getContainer(), info.getFormatPattern());
		return stringConverter;
	}

}
