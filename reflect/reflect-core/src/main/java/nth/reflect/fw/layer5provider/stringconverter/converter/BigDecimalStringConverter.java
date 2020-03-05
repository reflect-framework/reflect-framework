package nth.reflect.fw.layer5provider.stringconverter.converter;

import java.math.BigDecimal;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class BigDecimalStringConverter extends NumberStringConverter<BigDecimal> {

	public BigDecimalStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
	}

	@Override
	protected BigDecimal getValue(Number number) {
		return new BigDecimal(number.toString());
	}

}
