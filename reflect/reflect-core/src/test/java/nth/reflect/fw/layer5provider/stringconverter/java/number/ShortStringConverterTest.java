package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ShortStringConverter;

public class ShortStringConverterTest extends NumberStringConverterTest {


	@Override
	protected StringConverter createStringConverter(DependencyInjectionContainer container, String formatPattern) {
		return new ShortStringConverter(container, formatPattern);
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
