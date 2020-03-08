package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.layer5provider.stringconverter.converter.BigDecimalStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.BigIntegerStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.BooleanStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.ByteStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.DoubleStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.FloatStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.IntegerStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.LongStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.ShortStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactory;

public class DefaultStringConverters {

	private static StringConverterFactory[] allStringConverterFactories = { new ByteStringConverterFactory(),
			new ShortStringConverterFactory(), new DoubleStringConverterFactory(), new FloatStringConverterFactory(),
			new IntegerStringConverterFactory(), new LongStringConverterFactory(),
			new BigDecimalStringConverterFactory(), new BigIntegerStringConverterFactory(),
			new BooleanStringConverterFactory() };

	public static StringConverterFactory[] getAll() {
		return allStringConverterFactories;
	}

}
