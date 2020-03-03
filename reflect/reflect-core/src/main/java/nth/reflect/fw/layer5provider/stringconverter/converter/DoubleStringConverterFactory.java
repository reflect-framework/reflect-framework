package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class DoubleStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == double.class || type == Double.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		DoubleStringConverter stringConverter = new DoubleStringConverter(info.getContainer(), info.getFormatPattern());
		return stringConverter;
	}

}
