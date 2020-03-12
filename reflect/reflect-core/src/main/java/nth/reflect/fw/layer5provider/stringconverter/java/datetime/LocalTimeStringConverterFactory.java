package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalTime;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class LocalTimeStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == LocalTime.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		LocalTimeStringConverter stringConverter = new LocalTimeStringConverter(info);
		return stringConverter;
	}

}
