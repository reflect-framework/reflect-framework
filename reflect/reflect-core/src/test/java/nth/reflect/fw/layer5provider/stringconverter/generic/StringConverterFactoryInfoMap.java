package nth.reflect.fw.layer5provider.stringconverter.generic;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.CalendarStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.DateStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.java.datetime.LocalDateTimeStringConverter;
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
import nth.reflect.fw.layer5provider.stringconverter.java.other.StringStringConverter;

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
	private final DependencyInjectionContainer container;
	private final ReflectApplication application;

	public StringConverterFactoryInfoMap(DependencyInjectionContainer container) {
		this.container = container;
		this.application = container.get(ReflectApplication.class);
		addJavaNumberTypes();
		addJavaDateTimeTypes();
		addJavaOtherTypes();

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
	}

	private void addJavaOtherTypes() {
		put(DomainObject.GET_MY_TEXT, StringStringConverter.class);
		put(DomainObject.IS_MY_BOOLEAN, BooleanStringConverter.class);
		put(DomainObject.IS_MY_PRIMITIVE_BOOLEAN, BooleanStringConverter.class);
		put(DomainObject.GET_MY_CHARACTER, CharacterStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_CHAR, CharacterStringConverter.class);
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
		StringConverterFactoryInfo info = createInfo(domainObjectGetterMethod);
		put(info, expectedStringConverterType);
	}

	private StringConverterFactoryInfo createInfo(String domainObjectGetterMethod) {
		Method method = findMethod(domainObjectGetterMethod);
		ReturnTypeInfo typeInfo = new ReturnTypeInfo(application, method);
		StringConverterFactoryInfo stringConverterFactoryInfo = new StringConverterFactoryInfo(typeInfo, container,
				null);
		return stringConverterFactoryInfo;
	}

	private Method findMethod(String domainObjectGetterMethod) {
		Method[] allMethods = DomainObject.class.getDeclaredMethods();
		for (Method method : allMethods) {
			if (method.getName().equals(domainObjectGetterMethod)) {
				return method;
			}
		}
		throw new RuntimeException(
				"Could not find method " + domainObjectGetterMethod + " in " + DomainObject.class.getCanonicalName());
	}

}
