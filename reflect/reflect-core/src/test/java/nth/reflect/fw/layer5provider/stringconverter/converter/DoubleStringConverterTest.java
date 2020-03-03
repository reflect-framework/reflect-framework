package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;

public class DoubleStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(DependencyInjectionContainer container, String formatPattern) {
		return new DoubleStringConverter(container, formatPattern);
	}

	@Override
	protected Number getMinValue() {
		return Double.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Double.MAX_VALUE;
	}

}
