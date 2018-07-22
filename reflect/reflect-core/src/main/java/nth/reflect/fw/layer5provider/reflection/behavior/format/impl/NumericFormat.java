package nth.reflect.fw.layer5provider.reflection.behavior.format.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.reflect.fw.generic.exception.TypeNotSupportedException;

/**
 * Adapter for {@link DecimalFormat} to return the correct {@link #numberType}
 * 
 * @author nilsth
 * 
 */
public class NumericFormat extends Format {

	private static final long serialVersionUID = 5081082083122386923L;
	private final DecimalFormat decimalFormat;
	private Class<? extends Number> numberType;

	public NumericFormat(Class<? extends Number> numberType) {
		this(numberType, null);
	}

	public NumericFormat(Class<? extends Number> numberType, String format) {
		this.numberType = numberType;
		if (format == null || format.trim().length() == 0) {
			decimalFormat = new DecimalFormat();
		} else {
			decimalFormat = new DecimalFormat(format);
		}
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		return decimalFormat.format(obj, toAppendTo, pos);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		Number number = decimalFormat.parse(source, pos);
		if (number == null) {
			return null;
		} else {
			//FIXME implement NumberConverterFactory<Class<? extends Number>, Number> 
			if (AtomicInteger.class.isAssignableFrom(numberType)) {
				return new AtomicInteger(number.intValue());
			} else if (AtomicLong.class.isAssignableFrom(numberType)) {
				return new AtomicLong(number.longValue());
			} else if (BigDecimal.class.isAssignableFrom(numberType)) {
				return new BigDecimal(number.toString());
			} else if (BigInteger.class.isAssignableFrom(numberType)) {
				return BigInteger.valueOf(number.longValue());
			} else if (Integer.class.isAssignableFrom(numberType)) {
				return number.intValue();
			} else if (Long.class.isAssignableFrom(numberType)) {
				return number.longValue();
			} else if (Float.class.isAssignableFrom(numberType)) {
				return number.floatValue();
			} else if (Double.class.isAssignableFrom(numberType)) {
				return number.doubleValue();
			} else if (Byte.class.isAssignableFrom(numberType)) {
				return number.byteValue();
			} else if (Short.class.isAssignableFrom(numberType)) {
				return number.shortValue();
			}
			throw new TypeNotSupportedException(numberType, this.getClass());
		}
	}

}
