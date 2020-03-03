package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class DoubleStringConverter extends NumberStringConverter<Double> {

	public DoubleStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	protected Double getValue(Number number) {
		return number.doubleValue();
	}

}
