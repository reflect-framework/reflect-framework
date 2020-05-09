package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class LongStringConverterTest extends NumberStringConverterTest {
	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		StringConverterFactoryInfo info = createInfo(AllFeatureDomainObject.GET_MY_LONG, formatPattern);
		return new LongStringConverter(info);
	}

	@Override
	protected Number getMinValue() {
		return Long.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Long.MAX_VALUE;
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
