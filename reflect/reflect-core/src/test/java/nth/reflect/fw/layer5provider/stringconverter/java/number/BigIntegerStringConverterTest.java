package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigInteger;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class BigIntegerStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		StringConverterFactoryInfo info = createInfo(AllFeatureDomainObject.GET_MY_BIG_INTEGER, formatPattern);
		return new BigIntegerStringConverter(info);
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
