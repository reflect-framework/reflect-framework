package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.math.BigDecimal;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;

public class BigDecimalStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(DependencyInjectionContainer container, String formatPattern) {
		return new BigDecimalStringConverter(container, formatPattern);
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
