package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class DoubleStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		StringConverterFactoryInfo info = createInfo(DomainObject.GET_MY_DOUBLE, formatPattern);
		return new DoubleStringConverter(info);
	}

	@Override
	protected Number getMinValue() {
		return Double.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Double.MAX_VALUE;
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
