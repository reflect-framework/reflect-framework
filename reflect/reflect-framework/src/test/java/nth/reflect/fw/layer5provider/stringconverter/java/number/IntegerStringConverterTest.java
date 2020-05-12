package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class IntegerStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		return new IntegerStringConverter(getLanguageProvider(), formatPattern);
	}

	@Override
	protected Number getMinValue() {
		return Integer.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Integer.MAX_VALUE;
	}

}
