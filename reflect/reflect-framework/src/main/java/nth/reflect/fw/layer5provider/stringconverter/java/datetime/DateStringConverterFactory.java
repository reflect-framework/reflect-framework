package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.util.Date;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class DateStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return Date.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		DateStringConverter stringConverter = new DateStringConverter(formatPattern);
		return stringConverter;
	}

}
