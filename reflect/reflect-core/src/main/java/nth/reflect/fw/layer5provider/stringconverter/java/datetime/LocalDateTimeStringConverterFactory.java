package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalDateTime;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class LocalDateTimeStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == LocalDateTime.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		LocalDateTimeStringConverter stringConverter = new LocalDateTimeStringConverter(info);
		return stringConverter;
	}

}
