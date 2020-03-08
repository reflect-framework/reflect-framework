package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

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
