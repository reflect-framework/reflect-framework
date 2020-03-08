package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigInteger;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigIntegerStringConverter;

public class BigIntegerStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(DependencyInjectionContainer container, String formatPattern) {
		return new BigIntegerStringConverter(container, formatPattern);
	}

	@Override
	protected Number getMinValue() {
		return new BigInteger(Integer.toString(Integer.MIN_VALUE));
	}

	@Override
	protected Number getMaxValue() {
		return new BigInteger(Integer.toString(Integer.MAX_VALUE));
	}

	@Override
	public void testFromString_givenMaxNumberPluseOne_mustThrowNumberExceedsMaxException() {
		// Skip test
	}

	@Override
	public void testFromString_givenMinNumberMinuseOne_mustThrowNumberExceedsMinException() {
		// Skip test
	}

}
