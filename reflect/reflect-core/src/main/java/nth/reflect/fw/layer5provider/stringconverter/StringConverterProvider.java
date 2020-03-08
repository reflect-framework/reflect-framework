package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.generic.contractor.DelegatingContractor;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class StringConverterProvider extends DelegatingContractor<StringConverter, StringConverterFactoryInfo>
		implements Provider {

	public StringConverterProvider(StringConverterFactory... stringConverters) {
		super(stringConverters);
	}

}
