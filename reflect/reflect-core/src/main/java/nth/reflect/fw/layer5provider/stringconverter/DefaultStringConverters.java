package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigDecimalStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigIntegerStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ByteStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.DoubleStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.FloatStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.IntegerStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.LongStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ShortStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.BooleanStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.CharacterStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.StringStringConverterFactory;

public class DefaultStringConverters {

	private static StringConverterFactory[] allStringConverterFactories = { new StringStringConverterFactory(),
			new BooleanStringConverterFactory(), new CharacterStringConverterFactory(),
			new ByteStringConverterFactory(), new ShortStringConverterFactory(), new DoubleStringConverterFactory(),
			new FloatStringConverterFactory(), new IntegerStringConverterFactory(), new LongStringConverterFactory(),
			new BigDecimalStringConverterFactory(), new BigIntegerStringConverterFactory(), };

	public static StringConverterFactory[] getAll() {
		return allStringConverterFactories;
	}

}
