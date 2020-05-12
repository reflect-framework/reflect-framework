package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class LongStringConverterTest extends NumberStringConverterTest {
	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		return new LongStringConverter(getLanguageProvider(), formatPattern);
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
