package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class IntegerStringConverter extends NumberStringConverter<Integer> {

	public IntegerStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	protected Integer getValue(Number number) {
		if (number.longValue() > Integer.MAX_VALUE) {
			throw new NumberExceedsMaxException(this, number, Integer.MAX_VALUE);
		}
		if (number.longValue() < Integer.MIN_VALUE) {
			throw new NumberExceedsMinException(this, number, Integer.MIN_VALUE);
		}
		return number.intValue();
	}

}
