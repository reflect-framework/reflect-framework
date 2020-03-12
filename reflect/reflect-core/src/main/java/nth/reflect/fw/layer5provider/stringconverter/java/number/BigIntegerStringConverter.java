package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigInteger;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class BigIntegerStringConverter extends NumberStringConverter<BigInteger> {

	public BigIntegerStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	protected BigInteger getValue(Number number) {
		return new BigInteger(number.toString());
	}

}
