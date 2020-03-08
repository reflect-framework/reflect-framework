package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigInteger;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

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
