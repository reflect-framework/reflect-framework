package nth.reflect.fw.layer5provider.stringconverter;

import java.util.ArrayList;

import nth.reflect.fw.layer5provider.stringconverter.domain.DomainObjectStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.domain.DomainObjectStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.domain.EnumStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.domain.EnumStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.domain.TranslatableStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.CalendarStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.CalendarStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.DateStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.DateStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateTimeStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateTimeStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalTimeStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalTimeStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigDecimalStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigDecimalStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigIntegerStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ByteStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ByteStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.DoubleStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.DoubleStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.FloatStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.FloatStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.IntegerStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.IntegerStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.LongStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.LongStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ShortStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ShortStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.BooleanStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.BooleanStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.CharacterStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.CharacterStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.FileStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.FileStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.PathStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.PathStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.StringStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.StringStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.UriStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.UriStringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.java.other.UrlStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.UrlStringConverterFactory;

/**
 * The {@link StringConverterProvider} provides the following
 * {@link StringConverter}s per default:<br>
 * <br>
 * Java Numbers:
 * <ul>
 * <li>{@link ByteStringConverter}</li>
 * <li>{@link ShortStringConverter}</li>
 * <li>{@link DoubleStringConverter}</li>
 * <li>{@link FloatStringConverter}</li>
 * <li>{@link IntegerStringConverter}</li>
 * <li>{@link LongStringConverter}</li>
 * <li>{@link BigDecimalStringConverter}</li>
 * <li>{@link BigIntegerString}</li>
 * </ul>
 * Java Date & Time:
 * <ul>
 * <li>{@link CalendarStringConverter}</li>
 * <li>{@link DateStringConverter}</li>
 * <li>{@link LocalDateTimeStringConverter}</li>
 * <li>{@link LocalDateStringConverter}</li>
 * <li>{@link LocalTimeStringConverter}</li>
 * </ul>
 * * Other Java Types:
 * <ul>
 * <li>{@link StringStringConverter}</li>
 * <li>{@link BooleanStringConverter}</li>
 * <li>{@link CharacterStringConverter}</li>
 * <li>{@link UriStringConverter}</li>
 * <li>{@link UrlStringConverter}</li>
 * <li>{@link FileStringConverter}</li>
 * <li>{@link PathStringConverter}</li>
 * </ul>
 * Domain types:
 * <ul>
 * <li>{@link EnumStringConverter}</li>
 * <li>{@link DomainObjectStringConverter}</li>
 * <li>{@link TranslatedStringConverter}</li>
 * </ul>
 * 
 * 
 * @author nilsth
 *
 */
public class StringConverterFactories extends ArrayList<Class<? extends StringConverterFactory>> {

	private static final long serialVersionUID = 6818666850966910988L;

	public StringConverterFactories() {
		addJavaTypeFactories();
		addJavaNumberFactories();
		addJavaDateTimeFactories();
		addDomainTypeFactories();
	}

	private void addJavaTypeFactories() {
		add(StringStringConverterFactory.class);
		add(BooleanStringConverterFactory.class);
		add(CharacterStringConverterFactory.class);
		add(UriStringConverterFactory.class);
		add(UrlStringConverterFactory.class);
		add(FileStringConverterFactory.class);
		add(PathStringConverterFactory.class);
	}

	private void addJavaNumberFactories() {
		add(ByteStringConverterFactory.class);
		add(ShortStringConverterFactory.class);
		add(DoubleStringConverterFactory.class);
		add(FloatStringConverterFactory.class);
		add(IntegerStringConverterFactory.class);
		add(LongStringConverterFactory.class);
		add(BigDecimalStringConverterFactory.class);
		add(BigIntegerStringConverterFactory.class);
	}

	private void addJavaDateTimeFactories() {
		add(CalendarStringConverterFactory.class);
		add(DateStringConverterFactory.class);
		add(LocalDateTimeStringConverterFactory.class);
		add(LocalDateStringConverterFactory.class);
		add(LocalTimeStringConverterFactory.class);
	}

	private void addDomainTypeFactories() {
		add(EnumStringConverterFactory.class);
		add(DomainObjectStringConverterFactory.class);
		add(TranslatableStringConverterFactory.class);
	}

}
