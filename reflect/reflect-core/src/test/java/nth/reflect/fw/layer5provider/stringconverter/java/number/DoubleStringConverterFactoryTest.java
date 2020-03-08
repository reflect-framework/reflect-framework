package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryTest;
import nth.reflect.fw.layer5provider.stringconverter.java.number.DoubleStringConverterFactory;

public class DoubleStringConverterFactoryTest extends StringConverterFactoryTest {

	@Override
	protected StringConverterFactory getStringConverterFactory() {
		return new DoubleStringConverterFactory();
	}

}
