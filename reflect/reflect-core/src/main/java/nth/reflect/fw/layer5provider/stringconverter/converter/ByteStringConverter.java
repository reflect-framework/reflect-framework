package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.NumberExceedsMaxException;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.NumberExceedsMinException;

public class ByteStringConverter extends NumberStringConverter<Byte>  {

	public ByteStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	protected Byte getValue(Number number) {
		if (number.longValue()>Byte.MAX_VALUE) {
			throw new NumberExceedsMaxException(this, number, Byte.MAX_VALUE);
		};
		if (number.longValue()<Byte.MIN_VALUE) {
			throw new NumberExceedsMinException(this, number, Byte.MIN_VALUE);
		};
		return number.byteValue();
	}

}
