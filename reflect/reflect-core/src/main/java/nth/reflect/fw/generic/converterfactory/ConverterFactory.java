package nth.reflect.fw.generic.converterfactory;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collection;

import nth.reflect.fw.generic.exception.TypeNotSupportedException;
import nth.reflect.fw.generic.util.JavaTypeConverter;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * Abstract factory class to help create a converter of type <T> (I.E. a
 * formatter, document or XML converter) for all different types
 * 
 * Fixme: implement Contractor
 * 
 * @author nilsth
 * 
 */
public abstract class ConverterFactory<T> extends NumberConverterFactory<T> {

	@SuppressWarnings("unchecked")
	public T createConverter(LanguageProvider languageProvider, TypeInfo typeInfo) {
		Class<?> type = JavaTypeConverter.getComplexType(typeInfo.getType());
		// boolean
		if (Boolean.class.isAssignableFrom(type)) {
			return createBooleanConverter();
		} else

		// numbers

		if (Number.class.isAssignableFrom(type)) {
			return createNumberConverter(languageProvider, (Class<? extends Number>) type);
		} else

		// text
		if (Character.class.isAssignableFrom(type)) {
			return createCharConverter();
		} else if (String.class.isAssignableFrom(type)) {
			return createStringConverter();
		} else

		// enumeration
		if (type.isEnum()) {
			return createEnumConverter();
		} else
		// date and time
		if (java.util.Date.class.isAssignableFrom(type)) {
			return createDateConverter();
		} else if (Calendar.class.isAssignableFrom(type)) {
			return createCalendarConverter();
		} else if (LocalDateTime.class.isAssignableFrom(type)) {
			return createLocalDateTimeConverter();
		} else if (LocalDate.class.isAssignableFrom(type)) {
			return createLocalDateConverter();
		} else if (LocalTime.class.isAssignableFrom(type)) {
			return createLocalTimeConverter();
		}
		// URI
		if (URI.class.isAssignableFrom(type)) {
			return createUriConverter();
		} else if (URL.class.isAssignableFrom(type)) {
			return createUrlConverter();
		} else if (File.class.isAssignableFrom(type)) {
			return createFileConverter();
		}
		// TODO URL!!!!

		// domainObjects
		if (typeInfo.isDomainClass()) {
			return createDomainConverter();
		} else
		// collections
		if (Collection.class.isAssignableFrom(type)) {
			return createCollectionConverter();
		}

		// Not supported
		throw new TypeNotSupportedException(languageProvider, type, this.getClass());
	}

	public abstract T createLocalTimeConverter();

	public abstract T createLocalDateConverter();

	public abstract T createLocalDateTimeConverter();

	public abstract T createFileConverter();

	public abstract T createUrlConverter();

	public abstract T createBooleanConverter();

	public abstract T createCalendarConverter();

	public abstract T createCharConverter();

	public abstract T createCollectionConverter();

	public abstract T createDateConverter();

	public abstract T createDomainConverter();

	public abstract T createEnumConverter();

	public abstract T createStringConverter();

	public abstract T createUriConverter();
}