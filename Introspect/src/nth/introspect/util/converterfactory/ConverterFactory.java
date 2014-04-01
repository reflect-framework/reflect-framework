package nth.introspect.util.converterfactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.util.TypeUtil;
import nth.introspect.util.exception.TypeNotSupportedException;

import com.sun.jndi.toolkit.url.Uri;

/**
 * Abstract factory class to help create a converter of type <T> (I.E. a
 * formatter, document or XML converter) for all different types
 * 
 * @author nilsth
 * 
 */
public abstract class ConverterFactory<T, U> extends
		NumberConverterFactory<T, U> {

	public T createConverter(Class<?> type_, U metadata) {
		// boolean
		if (Boolean.class.isAssignableFrom(type_)) {
			return createBooleanConverter(metadata);
		} else

		// numbers

		if (Number.class.isAssignableFrom(type_)) {
			return createNumberConverter((Class<? extends Number>) type_,
					metadata);
		} else

		// text
		if (Character.class.isAssignableFrom(type_)) {
			return createCharConverter(metadata);
		} else if (String.class.isAssignableFrom(type_)) {
			return createStringConverter(metadata);
		} else

		// enumeration
		if (type_.isEnum()) {
			return createEnumConverter(metadata);
		} else
		// date and time
		if (java.util.Date.class.isAssignableFrom(type_)) {
			return createDateConverter(metadata);
		} else if (Calendar.class.isAssignableFrom(type_)) {
			return createCalendarConverter(metadata);
		} else
		// URI
		if (URI.class.isAssignableFrom(type_)) {
			return createUriConverter(metadata);
		} else
		// domainObjects
		if (TypeUtil.isDomainType(type_)) {
			return createDomainConverter(metadata);
		} else
		// collections
		if (TypeUtil.isColection(type_)) {
			return createCollectionConverter(metadata);
		}

		// Not supported
		throw new TypeNotSupportedException(type_, this.getClass());
	}

	public abstract T createBooleanConverter(U metadata);

	public abstract T createCalendarConverter(U metadata);

	public abstract T createCharConverter(U metadata);

	public abstract T createCollectionConverter(U metadata);

	public abstract T createDateConverter(U metadata);

	public abstract T createDomainConverter(U metadata);

	public abstract T createEnumConverter(U metadata);

	public abstract T createStringConverter(U metadata);

	public abstract T createUriConverter(U metadata);
}