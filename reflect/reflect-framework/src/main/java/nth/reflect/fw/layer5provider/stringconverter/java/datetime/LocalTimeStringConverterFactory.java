package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalTime;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class LocalTimeStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == LocalTime.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		LocalTimeStringConverter stringConverter = new LocalTimeStringConverter(formatPattern);
		return stringConverter;
	}

}
