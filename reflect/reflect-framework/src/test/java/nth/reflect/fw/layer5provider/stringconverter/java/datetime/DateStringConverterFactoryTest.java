package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import nth.reflect.fw.layer5provider.stringconverter.StringConverterFactoryTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class DateStringConverterFactoryTest extends StringConverterFactoryTest {

	@Override
	protected StringConverterFactory getStringConverterFactory() {
		return new DateStringConverterFactory();
	}

}
