package nth.reflect.fw.layer5provider.reflection.behavior.format.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.TypeNotSupportedException;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;

/**
 * Adapter for {@link DecimalFormat} to return the correct {@link #numberType}
 * @deprecated to be replaced with {@link StringConverterProvider}
 * 
 * @author nilsth
 * 
 */
public class NumericFormat extends Format {

	private static final long serialVersionUID = 5081082083122386923L;
	private final DecimalFormat decimalFormat;
	private Class<? extends Number> numberType;
	private final LanguageProvider languageProvider;

	public NumericFormat(LanguageProvider languageProvider, Class<? extends Number> numberType) {
		this(languageProvider, numberType, null);
	}

	public NumericFormat(LanguageProvider languageProvider, Class<? extends Number> numberType, String format) {
		this.languageProvider = languageProvider;
		this.numberType = numberType;
		if (format == null || format.trim().length() == 0) {
			decimalFormat = new DecimalFormat();
		} else {
			Locale locale=languageProvider.getDefaultLocale();
			DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
			decimalFormat = new DecimalFormat(format, symbols);
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
