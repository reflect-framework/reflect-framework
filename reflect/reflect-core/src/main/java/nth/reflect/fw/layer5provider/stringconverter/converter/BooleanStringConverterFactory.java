package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class BooleanStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == boolean.class || type == Boolean.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		BooleanStringConverter stringConverter = new BooleanStringConverter(info.getContainer(),
				info.getFormatPattern());
		return stringConverter;
	}

}
