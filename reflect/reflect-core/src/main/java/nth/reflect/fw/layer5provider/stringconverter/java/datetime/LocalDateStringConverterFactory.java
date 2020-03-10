package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalDate;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class LocalDateStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return type == LocalDate.class;
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		LocalDateStringConverter stringConverter = new LocalDateStringConverter(info.getContainer(),
				info.getFormatPattern());
		return stringConverter;
	}

}
