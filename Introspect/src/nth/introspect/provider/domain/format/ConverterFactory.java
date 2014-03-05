package nth.introspect.provider.domain.format;

import java.math.BigDecimal;
import java.math.BigInteger;
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
public abstract class ConverterFactory<T, U> {

	public T createConverter(Class<?> type_, U metadata) {
		//boolean
		if (Boolean.class.isAssignableFrom(type_)) {
			return createBooleanConverter(metadata);
		} else
			
		// numbers

		if (AtomicInteger.class.isAssignableFrom(type_)) {
			return createAtomicIntegerConverter(metadata);
		} else if (AtomicLong.class.isAssignableFrom(type_)) {
			return createAtomicLongConverter(metadata);
		} else if (BigDecimal.class.isAssignableFrom(type_)) {
			return createBigDecimalConverter(metadata);
		} else if (BigInteger.class.isAssignableFrom(type_)) {
			return createBigIntegerConverter(metadata);
		} else if (Byte.class.isAssignableFrom(type_)) {
			return createByteConverter(metadata);
		} else if (Double.class.isAssignableFrom(type_)) {
			return createDoubleConverter(metadata);
		} else if (Float.class.isAssignableFrom(type_)) {
			return createFloatCoverter(metadata);
		} else if (Integer.class.isAssignableFrom(type_)) {
			return createIntegerConverter(metadata);
		} else if (Long.class.isAssignableFrom(type_)) {
			return createLongConverter(metadata);
		} else if (Short.class.isAssignableFrom(type_)) {
			return createShortConverter(metadata);
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
		if (Uri.class.isAssignableFrom(type_)) {
			return createUriConverter(metadata);
		} else
		// domainObjects
		if (TypeUtil.isDomainType(type_)) {
			return createDomainConverter(metadata);
		}
		// Not supported
		throw new TypeNotSupportedException(type_, this.getClass());
	}

	
	public abstract T createAtomicIntegerConverter(U metadata);

	public abstract T createAtomicLongConverter(U metadata);
	
	public abstract T createBigDecimalConverter(U metadata);

	public abstract T createBigIntegerConverter(U metadata);

	public abstract T createBooleanConverter(U metadata);

	public abstract T createByteConverter(U metadata);

	public abstract T createCalendarConverter(U metadata);

	public abstract T createCharConverter(U metadata);

	public abstract T createDateConverter(U metadata);

	public abstract T createDomainConverter(U metadata);

	public abstract T createDoubleConverter(U metadata);

	public abstract T createEnumConverter(U metadata);

	public abstract T createFloatCoverter(U metadata);

	public abstract T createIntegerConverter(U metadata);

	public abstract T createLongConverter(U metadata);

	public abstract T createShortConverter(U metadata);

	public abstract T createStringConverter(U metadata);

	public abstract T createUriConverter(U metadata);
}