package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.math.BigDecimal;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class BigDecimalStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return BigDecimal.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		BigDecimalStringConverter stringConverter = new BigDecimalStringConverter(info.getContainer(),
				info.getFormatPattern());
		return stringConverter;
	}

}
