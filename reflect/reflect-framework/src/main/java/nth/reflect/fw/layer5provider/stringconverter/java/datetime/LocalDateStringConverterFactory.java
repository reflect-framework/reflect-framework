package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalDate;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class LocalDateStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(TypeInfo typeInfo) {
		Class<?> type = typeInfo.getType();
		return type == LocalDate.class;
	}

	@Override
	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		LocalDateStringConverter stringConverter = new LocalDateStringConverter(formatPattern);
		return stringConverter;
	}

}
