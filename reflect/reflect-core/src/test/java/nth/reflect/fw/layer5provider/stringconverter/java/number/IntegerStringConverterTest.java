package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.IntegerStringConverter;

public class IntegerStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(DependencyInjectionContainer container, String formatPattern) {
		return new IntegerStringConverter(container, formatPattern);
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
