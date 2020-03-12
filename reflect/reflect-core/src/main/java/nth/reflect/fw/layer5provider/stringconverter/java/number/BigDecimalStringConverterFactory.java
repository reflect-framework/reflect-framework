package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigDecimal;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class BigDecimalStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return BigDecimal.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		BigDecimalStringConverter stringConverter = new BigDecimalStringConverter(info);
		return stringConverter;
	}

}
