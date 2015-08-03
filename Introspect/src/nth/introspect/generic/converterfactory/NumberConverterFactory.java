package nth.introspect.generic.converterfactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.generic.exception.TypeNotSupportedException;

public abstract class NumberConverterFactory<T> {

	public T createNumberConverter(Class<? extends Number> type_) {

		if (AtomicInteger.class.isAssignableFrom(type_)) {
			return createAtomicIntegerConverter();
		} else if (AtomicLong.class.isAssignableFrom(type_)) {
			return createAtomicLongConverter();
		} else if (BigDecimal.class.isAssignableFrom(type_)) {
			return createBigDecimalConverter();
		} else if (BigInteger.class.isAssignableFrom(type_)) {
			return createBigIntegerConverter();
		} else if (Byte.class.isAssignableFrom(type_)) {
			return createByteConverter();
		} else if (Double.class.isAssignableFrom(type_)) {
			return createDoubleConverter();
		} else if (Float.class.isAssignableFrom(type_)) {
			return createFloatCoverter();
		} else if (Integer.class.isAssignableFrom(type_)) {
			return createIntegerConverter();
		} else if (Long.class.isAssignableFrom(type_)) {
			return createLongConverter();
		} else if (Short.class.isAssignableFrom(type_)) {
			return createShortConverter();
		} 
		throw new TypeNotSupportedException(type_, this.getClass());
	}
	
	public abstract T createAtomicIntegerConverter();

	public abstract T createAtomicLongConverter();
	
	public abstract T createBigDecimalConverter();

	public abstract T createBigIntegerConverter();

	public abstract T createByteConverter();

	public abstract T createDoubleConverter();

	public abstract T createFloatCoverter();

	public abstract T createIntegerConverter();

	public abstract T createLongConverter();

	public abstract T createShortConverter();

}
