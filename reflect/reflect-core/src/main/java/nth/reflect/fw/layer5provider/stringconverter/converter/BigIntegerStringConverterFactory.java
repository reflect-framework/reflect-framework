package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.math.BigInteger;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

public class BigIntegerStringConverterFactory implements StringConverterFactory {

	@Override
	public boolean canCreate(StringConverterFactoryInfo info) {
		Class<?> type = info.getTypeInfo().getType();
		return BigInteger.class.isAssignableFrom(type);
	}

	@Override
	public StringConverter create(StringConverterFactoryInfo info) {
		BigIntegerStringConverter stringConverter = new BigIntegerStringConverter(info.getContainer(),
				info.getFormatPattern());
		return stringConverter;
	}

}
