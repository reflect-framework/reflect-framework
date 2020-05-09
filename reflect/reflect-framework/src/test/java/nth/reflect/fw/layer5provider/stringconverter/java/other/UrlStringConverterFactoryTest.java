package nth.reflect.fw.layer5provider.stringconverter.java.other;

import nth.reflect.fw.layer5provider.stringconverter.StringConverterFactoryTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class UrlStringConverterFactoryTest extends StringConverterFactoryTest {

	@Override
	protected StringConverterFactory getStringConverterFactory() {
		return new UrlStringConverterFactory();
	}

}
