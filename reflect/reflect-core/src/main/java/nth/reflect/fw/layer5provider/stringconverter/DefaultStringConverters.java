package nth.reflect.fw.layer5provider.stringconverter;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.CalendarStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.DateStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateTimeStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalTimeStringConverterFactory;
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

	private static StringConverterFactory[] allStringConverterFactories = {
			// Java Types (other than numbers or date & time)
			new StringStringConverterFactory(), new BooleanStringConverterFactory(),
			new CharacterStringConverterFactory(),
			// Java Numbers
			new ByteStringConverterFactory(), new ShortStringConverterFactory(), new DoubleStringConverterFactory(),
			new FloatStringConverterFactory(), new IntegerStringConverterFactory(), new LongStringConverterFactory(),
			new BigDecimalStringConverterFactory(), new BigIntegerStringConverterFactory(),
			// Java Date & Time
			new CalendarStringConverterFactory(), new DateStringConverterFactory(),
			new LocalDateTimeStringConverterFactory(), new LocalDateStringConverterFactory(),
			new LocalTimeStringConverterFactory() };

	public static StringConverterFactory[] getAll() {
		return allStringConverterFactories;
	}

}
