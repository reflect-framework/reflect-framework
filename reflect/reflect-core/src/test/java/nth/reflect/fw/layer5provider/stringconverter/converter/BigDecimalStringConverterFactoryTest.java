package nth.reflect.fw.layer5provider.stringconverter.converter;

import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;

public class BigDecimalStringConverterFactoryTest extends StringConverterFactoryTest {

	@Override
	protected StringConverterFactory getStringConverterFactory() {
		return new BigDecimalStringConverterFactory();
	}

}
