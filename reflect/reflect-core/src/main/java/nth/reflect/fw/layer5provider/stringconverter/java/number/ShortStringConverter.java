package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class ShortStringConverter extends NumberStringConverter<Short> {

	public ShortStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	protected Short getValue(Number number) {
		if (number.longValue() > Short.MAX_VALUE) {
			throw new NumberExceedsMaxException(this, number, Short.MAX_VALUE);
		}
		if (number.longValue() < Short.MIN_VALUE) {
			throw new NumberExceedsMinException(this, number, Short.MIN_VALUE);
		}
		return number.shortValue();
	}

}
