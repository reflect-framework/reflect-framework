package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class EnumStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type.isEnum();
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		EnumStringConverter stringConverter = new EnumStringConverter(info.getContainer(), info.getFormatPattern());
		return stringConverter;
	}

}
