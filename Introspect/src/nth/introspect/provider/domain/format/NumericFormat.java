package nth.introspect.provider.domain.format;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import nth.introspect.util.exception.TypeNotSupportedException;

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
			if (Integer.class.isAssignableFrom(numberType)) {
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
