package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;

public class FloatStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(DependencyInjectionContainer container, String formatPattern) {
		return new FloatStringConverter(container, formatPattern);
	}

	@Override
	protected Number getMinValue() {
		return Float.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Float.MAX_VALUE;
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
