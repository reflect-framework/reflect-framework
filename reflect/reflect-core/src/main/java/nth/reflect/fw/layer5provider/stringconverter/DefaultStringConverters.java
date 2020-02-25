package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.layer5provider.stringconverter.abstractconverter.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.NumberStringConverterFactory;

public class DefaultStringConverters {

	private static StringConverterFactory[] allStringConverterFactories= {new NumberStringConverterFactory()};

	public static StringConverterFactory[] getAll() {
		return allStringConverterFactories;
	}

}
