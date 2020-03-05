package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.math.BigInteger;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class BigIntegerStringConverter extends NumberStringConverter<BigInteger> {

	public BigIntegerStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	protected BigInteger getValue(Number number) {
		return new BigInteger(number.toString());
	}

}
