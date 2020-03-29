package nth.reflect.fw.layer5provider.stringconverter;

import java.util.HashMap;
import java.util.Map;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.stringconverter.domain.DomainObjectStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.domain.EnumStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.domain.TranslatableStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.CalendarStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.DateStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateTimeStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalTimeStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigDecimalStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.BigIntegerStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ByteStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.DoubleStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.FloatStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.IntegerStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.LongStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.number.ShortStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.BooleanStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.CharacterStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.FileStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.PathStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.StringStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.UriStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.other.UrlStringConverter;

/**
 * This {@link Map} contains {@link StringConverterFactoryInfo}s (created from
 * {@link DomainObjectProperty}s of the {@link DomainObject} class) and their
 * corresponding {@link StringConverter} type.
 * {@link StringConverterFactoryInfoMap} is used for JUnit testing
 * 
 * @author nilsth
 *
 */
public class StringConverterFactoryInfoMap
		extends HashMap<StringConverterFactoryInfo, Class<? extends StringConverter>> {

	private static final long serialVersionUID = 799702481550723556L;
	private DependencyInjectionContainer container;

	public StringConverterFactoryInfoMap(DependencyInjectionContainer container) {
		this.container = container;
		addJavaNumberTypes();
		addJavaDateTimeTypes();
		addJavaOtherTypes();
		addDomainTypes();
	}

	private void addDomainTypes() {
		put(DomainObject.GET_MY_ENUM, EnumStringConverter.class);
		put(DomainObject.GET_MY_DOMAIN_OBJECT, DomainObjectStringConverter.class);
		put(DomainObject.GET_MY_TRANSLATABLE_STRING, TranslatableStringConverter.class);
	}

	private void addJavaDateTimeTypes() {
		put(DomainObject.GET_MY_CALENDAR, CalendarStringConverter.class);
		put(DomainObject.GET_MY_CALENDAR_WITH_DATE_ANNOTATION, CalendarStringConverter.class);
		put(DomainObject.GET_MY_CALENDAR_WITH_DATE_FORMAT, CalendarStringConverter.class);
		put(DomainObject.GET_MY_CALENDAR_WITH_DATE_TIME_ANNOTATION, CalendarStringConverter.class);
		put(DomainObject.GET_MY_CALENDAR_WITH_DATE_TIME_FORMAT, CalendarStringConverter.class);
		put(DomainObject.GET_MY_CALENDAR_WITH_TIME_ANNOTATION, CalendarStringConverter.class);
		put(DomainObject.GET_MY_CALENDAR_WITH_TIME_FORMAT, CalendarStringConverter.class);
		put(DomainObject.GET_MY_DATE, DateStringConverter.class);
		put(DomainObject.GET_MY_DATE_WITH_DATE_ANNOTATION, DateStringConverter.class);
		put(DomainObject.GET_MY_DATE_WITH_DATE_FORMAT, DateStringConverter.class);
		put(DomainObject.GET_MY_DATE_WITH_DATE_TIME_ANNOTATION, DateStringConverter.class);
		put(DomainObject.GET_MY_DATE_WITH_DATE_TIME_FORMAT, DateStringConverter.class);
		put(DomainObject.GET_MY_DATE_WITH_TIME_ANNOTATION, DateStringConverter.class);
		put(DomainObject.GET_MY_DATE_WITH_TIME_FORMAT, DateStringConverter.class);
		put(DomainObject.GET_MY_LOCAL_DATE_TIME, LocalDateTimeStringConverter.class);
		put(DomainObject.GET_MY_LOCAL_DATE, LocalDateStringConverter.class);
		put(DomainObject.GET_MY_LOCAL_TIME, LocalTimeStringConverter.class);
	}

	private void addJavaOtherTypes() {
		put(DomainObject.GET_MY_TEXT, StringStringConverter.class);
		put(DomainObject.IS_MY_BOOLEAN, BooleanStringConverter.class);
		put(DomainObject.IS_MY_PRIMITIVE_BOOLEAN, BooleanStringConverter.class);
		put(DomainObject.GET_MY_CHARACTER, CharacterStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_CHAR, CharacterStringConverter.class);
		put(DomainObject.GET_MY_URI, UriStringConverter.class);
		put(DomainObject.GET_MY_URL, UrlStringConverter.class);
		put(DomainObject.GET_MY_FILE, FileStringConverter.class);
		put(DomainObject.GET_MY_PATH, PathStringConverter.class);
	}

	private void addJavaNumberTypes() {
		put(DomainObject.GET_MY_BYTE, ByteStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_BYTE, ByteStringConverter.class);
		put(DomainObject.GET_MY_SHORT, ShortStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_SHORT, ShortStringConverter.class);
		put(DomainObject.GET_MY_INTEGER, IntegerStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_INT, IntegerStringConverter.class);
		put(DomainObject.GET_MY_LONG, LongStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_LONG, LongStringConverter.class);
		put(DomainObject.GET_MY_DOUBLE, DoubleStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_DOUBLE, DoubleStringConverter.class);
		put(DomainObject.GET_MY_FLOAT, FloatStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_FLOAT, FloatStringConverter.class);
		put(DomainObject.GET_MY_BIG_INTEGER, BigIntegerStringConverter.class);
		put(DomainObject.GET_MY_BIG_DECIMAL, BigDecimalStringConverter.class);
	}

	private void put(String domainObjectGetterMethod, Class<? extends StringConverter> expectedStringConverterType) {
		StringConverterFactoryInfo info = InfoFactory.create(container, domainObjectGetterMethod);
		put(info, expectedStringConverterType);
	}

}
