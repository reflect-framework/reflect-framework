package nth.reflect.fw.layer5provider.stringconverter.java.number;

import java.math.BigDecimal;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class BigDecimalStringConverter extends NumberStringConverter<BigDecimal> {

	public BigDecimalStringConverter(StringConverterFactoryInfo info) {
		super(info);
	}

	@Override
	protected BigDecimal getValue(Number number) {
		return new BigDecimal(number.toString());
	}

}
