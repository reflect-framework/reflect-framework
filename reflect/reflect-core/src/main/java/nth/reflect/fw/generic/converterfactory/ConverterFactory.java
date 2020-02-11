package nth.reflect.fw.generic.converterfactory;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

import nth.reflect.fw.generic.util.PrimitiveType;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

/**
 * Abstract factory class to help create a converter of type &lt;T&gt; (I.E. a
 * formatter, document or XML converter) for all different types
 * 
 * Fixme: implement Contractor
 * 
 * @author nilsth
 * 
 */
public abstract class ConverterFactory<T> extends NumberConverterFactory<T> {

	@SuppressWarnings("unchecked")
	public Optional<T> createConverter(LanguageProvider languageProvider, TypeInfo typeInfo) {
		Class<?> type = PrimitiveType.primitiveToWrapper(typeInfo.getType());
		// boolean
		if (Boolean.class.isAssignableFrom(type)) {
			return Optional.of(createBooleanConverter());
		} else

		// numbers
		if (Number.class.isAssignableFrom(type)) {
			return createNumberConverter(languageProvider, (Class<? extends Number>) type);
		} else

		// text
		if (Character.class.isAssignableFrom(type)) {
			return Optional.of(createCharConverter());
		} else if (String.class.isAssignableFrom(type)) {
			return Optional.of(createStringConverter());
		} else

		// enumeration
		if (type.isEnum()) {
			return Optional.of(createEnumConverter());
		} else
		// date and time
		if (java.util.Date.class.isAssignableFrom(type)) {
			return Optional.of(createDateConverter());
		} else if (Calendar.class.isAssignableFrom(type)) {
			return Optional.of(createCalendarConverter());
		} else if (LocalDateTime.class.isAssignableFrom(type)) {
			return Optional.of(createLocalDateTimeConverter());
		} else if (LocalDate.class.isAssignableFrom(type)) {
			return Optional.of(createLocalDateConverter());
		} else if (LocalTime.class.isAssignableFrom(type)) {
			return Optional.of(createLocalTimeConverter());
		}
		// URI
		if (URI.class.isAssignableFrom(type)) {
			return Optional.of(createUriConverter());
		} else if (URL.class.isAssignableFrom(type)) {
			return Optional.of(createUrlConverter());
		} else if (File.class.isAssignableFrom(type)) {
			return Optional.of(createFileConverter());
		}
		// TODO URL!!!!

		// domainObjects
		if (typeInfo.isDomainClass()) {
			return Optional.of(createDomainConverter());
		} else
		// collections
		if (Collection.class.isAssignableFrom(type)) {
			return Optional.of(createCollectionConverter());
		}

		return Optional.empty();
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