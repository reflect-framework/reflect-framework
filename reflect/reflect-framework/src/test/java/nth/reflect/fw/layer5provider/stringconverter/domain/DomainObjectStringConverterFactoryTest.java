package nth.reflect.fw.layer5provider.stringconverter.domain;

import nth.reflect.fw.layer5provider.stringconverter.StringConverterFactoryTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

public class DomainObjectStringConverterFactoryTest extends StringConverterFactoryTest {

	@Override
	protected StringConverterFactory getStringConverterFactory() {
		return new DomainObjectStringConverterFactory();
	}

}
