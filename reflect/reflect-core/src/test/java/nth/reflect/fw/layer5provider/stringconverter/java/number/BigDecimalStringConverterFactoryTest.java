package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryTest;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigDecimalStringConverterFactory;

public class BigDecimalStringConverterFactoryTest extends StringConverterFactoryTest {

	@Override
	protected StringConverterFactory getStringConverterFactory() {
		return new BigDecimalStringConverterFactory();
	}

}
