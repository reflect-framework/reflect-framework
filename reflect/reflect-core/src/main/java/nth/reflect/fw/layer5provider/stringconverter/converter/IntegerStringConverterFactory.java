package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class IntegerStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == int.class || type == Integer.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		IntegerStringConverter stringConverter = new IntegerStringConverter(info.getContainer(),
				info.getFormatPattern());
		return stringConverter;
	}

}
