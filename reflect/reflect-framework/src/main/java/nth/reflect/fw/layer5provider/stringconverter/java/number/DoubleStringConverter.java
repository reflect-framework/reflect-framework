package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class DoubleStringConverter extends NumberStringConverter<Double> {

	public DoubleStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	protected Double getValue(Number number) {
		return number.doubleValue();
	}

}
