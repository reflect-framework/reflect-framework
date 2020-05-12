package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalDateTime;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class LocalDateTimeStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == LocalDateTime.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		LocalDateTimeStringConverter stringConverter = new LocalDateTimeStringConverter(formatPattern);
		return stringConverter;
	}

}
