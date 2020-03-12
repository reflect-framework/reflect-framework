package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class ByteStringConverter extends NumberStringConverter<Byte> {

	public ByteStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	protected Byte getValue(Number number) {
		if (number.longValue() > Byte.MAX_VALUE) {
			throw new NumberExceedsMaxException(this, number, Byte.MAX_VALUE);
		}
		if (number.longValue() < Byte.MIN_VALUE) {
			throw new NumberExceedsMinException(this, number, Byte.MIN_VALUE);
		}
		return number.byteValue();
	}

}
