package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.layer5provider.stringconverter.converter.ByteStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;

public class DefaultStringConverters {

	private static StringConverterFactory[] allStringConverterFactories= {new ByteStringConverterFactory()};

	public static StringConverterFactory[] getAll() {
		return allStringConverterFactories;
	}

}
