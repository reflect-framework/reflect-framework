package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class LongStringConverter extends NumberStringConverter<Long> {

	public LongStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	protected Long getValue(Number number) {
		return number.longValue();
	}

}
