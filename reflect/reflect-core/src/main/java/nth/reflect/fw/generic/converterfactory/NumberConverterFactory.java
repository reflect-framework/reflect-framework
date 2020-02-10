package nth.reflect.fw.generic.converterfactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.reflect.fw.layer5provider.language.LanguageProvider;

public abstract class NumberConverterFactory<T> {

	public Optional<T> createNumberConverter(LanguageProvider languageProvider, Class<? extends Number> type_) {

		if (AtomicInteger.class.isAssignableFrom(type_)) {
			return Optional.of(createAtomicIntegerConverter());
		} else if (AtomicLong.class.isAssignableFrom(type_)) {
			return Optional.of(createAtomicLongConverter());
		} else if (BigDecimal.class.isAssignableFrom(type_)) {
			return Optional.of(createBigDecimalConverter());
		} else if (BigInteger.class.isAssignableFrom(type_)) {
			return Optional.of(createBigIntegerConverter());
		} else if (Byte.class.isAssignableFrom(type_)) {
			return Optional.of(createByteConverter());
		} else if (Double.class.isAssignableFrom(type_)) {
			return Optional.of(createDoubleConverter());
		} else if (Float.class.isAssignableFrom(type_)) {
			return Optional.of(createFloatCoverter());
		} else if (Integer.class.isAssignableFrom(type_)) {
			return Optional.of(createIntegerConverter());
		} else if (Long.class.isAssignableFrom(type_)) {
			return Optional.of(createLongConverter());
		} else if (Short.class.isAssignableFrom(type_)) {
			return Optional.of(createShortConverter());
		}
		return Optional.empty();
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
