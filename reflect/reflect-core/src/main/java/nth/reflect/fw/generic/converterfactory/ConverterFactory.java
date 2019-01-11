package nth.reflect.fw.generic.converterfactory;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

import nth.reflect.fw.generic.exception.TypeNotSupportedException;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeCategory;

/**
 * Abstract factory class to help create a converter of type <T> (I.E. a
 * formatter, document or XML converter) for all different types
 * 
 * @author nilsth
 * 
 */
public abstract class ConverterFactory<T> extends
		NumberConverterFactory<T> {
	
	@SuppressWarnings("unchecked")
	public T createConverter(LanguageProvider languageProvider, Class<?> type_) {
		// boolean
		if (Boolean.class.isAssignableFrom(type_)) {
			return createBooleanConverter();
		} else

		// numbers

		if (Number.class.isAssignableFrom(type_)) {
			return createNumberConverter(languageProvider, (Class<? extends Number>) type_);
		} else

		// text
		if (Character.class.isAssignableFrom(type_)) {
			return createCharConverter();
		} else if (String.class.isAssignableFrom(type_)) {
			return createStringConverter();
		} else

		// enumeration
		if (type_.isEnum()) {
			return createEnumConverter();
		} else
		// date and time
		if (java.util.Date.class.isAssignableFrom(type_)) {
			return createDateConverter();
		} else if (Calendar.class.isAssignableFrom(type_)) {
			return createCalendarConverter();
		} else if (LocalDateTime.class.isAssignableFrom(type_)) {
			return createLocalDateTimeConverter();
		} else if (LocalDate.class.isAssignableFrom(type_)) {
			return createLocalDateConverter();
		}  else if (LocalTime.class.isAssignableFrom(type_)) {
			return createLocalTimeConverter();
		}
		// URI
		if (URI.class.isAssignableFrom(type_)) {
			return createUriConverter();
		}  else if (URL.class.isAssignableFrom(type_)) {
			return createUrlConverter();
		}		else if (File.class.isAssignableFrom(type_)) {
			return createFileConverter();
		}
		//TODO URL!!!!	
			
		// domainObjects
		if (TypeCategory.isDomainType(type_)) {
			return createDomainConverter();
		} else
		// collections
		if (TypeCategory.isColection(type_)) {
			return createCollectionConverter();
		}

		// Not supported
		throw new TypeNotSupportedException(languageProvider, type_, this.getClass());
	}

	public abstract T  createLocalTimeConverter();

	public abstract T  createLocalDateConverter();

	public abstract T  createLocalDateTimeConverter();

	public abstract T  createFileConverter();

	public abstract T createUrlConverter() ;

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