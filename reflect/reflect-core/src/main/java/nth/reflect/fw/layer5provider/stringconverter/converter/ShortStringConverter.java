package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.NumberExceedsMaxException;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.NumberExceedsMinException;

public class ShortStringConverter extends NumberStringConverter<Short>  {

	public ShortStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	protected Short getValue(Number number) {
		if (number.longValue()>Short.MAX_VALUE) {
			throw new NumberExceedsMaxException(this, number, Short.MAX_VALUE);
		};
		if (number.longValue()<Short.MIN_VALUE) {
			throw new NumberExceedsMinException(this, number, Short.MIN_VALUE);
		};
		return number.shortValue();
	}

}
