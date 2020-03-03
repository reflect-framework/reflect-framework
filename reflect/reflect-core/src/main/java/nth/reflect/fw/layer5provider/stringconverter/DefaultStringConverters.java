package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.layer5provider.stringconverter.converter.ByteStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.DoubleStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.ShortStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;

public class DefaultStringConverters {

	private static StringConverterFactory[] allStringConverterFactories = { new ByteStringConverterFactory(),
			new ShortStringConverterFactory(), new DoubleStringConverterFactory() };

	public static StringConverterFactory[] getAll() {
		return allStringConverterFactories;
	}

}
