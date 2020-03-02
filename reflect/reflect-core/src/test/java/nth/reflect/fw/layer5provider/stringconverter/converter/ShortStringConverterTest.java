package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;

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
