package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class IntegerStringConverter extends NumberStringConverter<Integer> {

	public IntegerStringConverter(StringConverterFactoryInfo info) {
		super(info);
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
