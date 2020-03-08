package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.util.Date;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class DateStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return Date.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		DateStringConverter stringConverter = new DateStringConverter(info.getContainer(), info.getFormatPattern());
		return stringConverter;
	}

}
