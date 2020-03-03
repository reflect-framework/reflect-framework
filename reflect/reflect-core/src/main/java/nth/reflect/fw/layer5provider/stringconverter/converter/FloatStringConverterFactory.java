package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

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
