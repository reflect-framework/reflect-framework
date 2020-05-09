package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class FloatStringConverter extends NumberStringConverter<Float> {

	public FloatStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	protected Float getValue(Number number) {
		return number.floatValue();
	}

}
