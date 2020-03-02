package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class ShortStringConverterFactory implements StringConverterFactory {//

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == short.class || type == Short.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		ShortStringConverter stringConverter = new ShortStringConverter(info.getContainer(), info.getFormatPattern());
		return stringConverter;
	}

}
