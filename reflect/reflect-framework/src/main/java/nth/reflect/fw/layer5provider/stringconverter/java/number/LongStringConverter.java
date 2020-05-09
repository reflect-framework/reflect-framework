package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class LongStringConverter extends NumberStringConverter<Long> {

	public LongStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	protected Long getValue(Number number) {
		return number.longValue();
	}

}
