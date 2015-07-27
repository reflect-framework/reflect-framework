package nth.introspect.generic.converterfactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.generic.exception.TypeNotSupportedException;

public abstract class NumberConverterFactory<T,U> {

	public T createNumberConverter(Class<? extends Number> type_, U metadata) {

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
		} 
		throw new TypeNotSupportedException(type_, this.getClass());
	}
	
	public abstract T createAtomicIntegerConverter(U metadata);

	public abstract T createAtomicLongConverter(U metadata);
	
	public abstract T createBigDecimalConverter(U metadata);

	public abstract T createBigIntegerConverter(U metadata);

	public abstract T createByteConverter(U metadata);

	public abstract T createDoubleConverter(U metadata);

	public abstract T createFloatCoverter(U metadata);

	public abstract T createIntegerConverter(U metadata);

	public abstract T createLongConverter(U metadata);

	public abstract T createShortConverter(U metadata);

}
