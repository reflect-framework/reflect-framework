package nth.reflect.fw.layer5provider.stringconverter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.domain.DomainObjectStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.domain.EnumStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.domain.TranslatableStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
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
 * This {@link Map} contains {@link TypeInfo} and expected
 * {@link StringConverter} (created from
 * {@link FullFeatureDomainObjectProperty}s of the
 * {@link AllFeatureDomainObject} class) and their corresponding
 * {@link StringConverter} type. {@link StringConverterFactoryInfoMap} is used for
 * JUnit testing
 * 
 * @author nilsth
 *
 */
public class StringConverterFactoryInfoMap extends HashMap<TypeInfo, Class<? extends StringConverter>> {

	private static final long serialVersionUID = 799702481550723556L;
	private ReflectApplication application;

	public StringConverterFactoryInfoMap(DependencyInjectionContainer container) {
		application = container.get(ReflectApplication.class);
		addJavaNumberTypes();
		addJavaDateTimeTypes();
		addJavaOtherTypes();
		addDomainTypes();
	}

	private void addDomainTypes() {
		put(AllFeatureDomainObject.GET_MY_ENUM, EnumStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DOMAIN_OBJECT, DomainObjectStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_TRANSLATABLE_STRING, TranslatableStringConverter.class);
	}

	private void addJavaDateTimeTypes() {
		put(AllFeatureDomainObject.GET_MY_CALENDAR, CalendarStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_CALENDAR_WITH_DATE_ANNOTATION, CalendarStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_CALENDAR_WITH_DATE_FORMAT, CalendarStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_CALENDAR_WITH_DATE_TIME_ANNOTATION, CalendarStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_CALENDAR_WITH_DATE_TIME_FORMAT, CalendarStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_CALENDAR_WITH_TIME_ANNOTATION, CalendarStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_CALENDAR_WITH_TIME_FORMAT, CalendarStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DATE, DateStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DATE_WITH_DATE_ANNOTATION, DateStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DATE_WITH_DATE_FORMAT, DateStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DATE_WITH_DATE_TIME_ANNOTATION, DateStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DATE_WITH_DATE_TIME_FORMAT, DateStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DATE_WITH_TIME_ANNOTATION, DateStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DATE_WITH_TIME_FORMAT, DateStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_LOCAL_DATE_TIME, LocalDateTimeStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_LOCAL_DATE, LocalDateStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_LOCAL_TIME, LocalTimeStringConverter.class);
	}

	private void addJavaOtherTypes() {
		put(AllFeatureDomainObject.GET_MY_TEXT, StringStringConverter.class);
		put(AllFeatureDomainObject.IS_MY_BOOLEAN, BooleanStringConverter.class);
		put(AllFeatureDomainObject.IS_MY_PRIMITIVE_BOOLEAN, BooleanStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_CHARACTER, CharacterStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_PRIMITIVE_CHAR, CharacterStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_URI, UriStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_URL, UrlStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_FILE, FileStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_PATH, PathStringConverter.class);
	}

	private void addJavaNumberTypes() {
		put(AllFeatureDomainObject.GET_MY_BYTE, ByteStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_PRIMITIVE_BYTE, ByteStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_SHORT, ShortStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_PRIMITIVE_SHORT, ShortStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_INTEGER, IntegerStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_PRIMITIVE_INT, IntegerStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_LONG, LongStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_PRIMITIVE_LONG, LongStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_DOUBLE, DoubleStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_PRIMITIVE_DOUBLE, DoubleStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_FLOAT, FloatStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_PRIMITIVE_FLOAT, FloatStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_BIG_INTEGER, BigIntegerStringConverter.class);
		put(AllFeatureDomainObject.GET_MY_BIG_DECIMAL, BigDecimalStringConverter.class);
	}

	private void put(String domainObjectGetterMethod, Class<? extends StringConverter> expectedStringConverterType) {
		Method method = findMethod(domainObjectGetterMethod);
		ReturnTypeInfo typeInfo = new ReturnTypeInfo(application, method);
		put(typeInfo, expectedStringConverterType);
	}

	private static Method findMethod(String domainObjectGetterMethod) {
		Method[] allMethods = AllFeatureDomainObject.class.getDeclaredMethods();
		for (Method method : allMethods) {
			if (method.getName().equals(domainObjectGetterMethod)) {
				return method;
			}
		}
		throw new MethodNotFoundException(domainObjectGetterMethod);
	}

}
