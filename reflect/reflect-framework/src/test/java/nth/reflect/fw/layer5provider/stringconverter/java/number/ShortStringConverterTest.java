package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class ShortStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		return new ShortStringConverter(getLanguageProvider(), formatPattern);
	}

	@Override
	protected Number getMinValue() {
		return Short.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Short.MAX_VALUE;
	}

}
