package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigDecimal;

import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class BigDecimalStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		StringConverterFactoryInfo info = createInfo(FullFeatureDomainObject.GET_MY_BIG_DECIMAL, formatPattern);
		return new BigDecimalStringConverter(info);
	}

	@Override
	protected Number getMinValue() {
		return new BigDecimal("-12345.678");
	}

	@Override
	protected Number getMaxValue() {
		return new BigDecimal("123456.78");
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
