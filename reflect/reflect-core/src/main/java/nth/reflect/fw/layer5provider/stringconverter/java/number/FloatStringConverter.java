package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class FloatStringConverter extends NumberStringConverter<Float> {

	public FloatStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	protected Float getValue(Number number) {
		return number.floatValue();
	}

}
