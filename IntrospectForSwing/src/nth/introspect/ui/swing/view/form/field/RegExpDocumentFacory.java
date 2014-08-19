package nth.introspect.ui.swing.view.form.field;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.text.Document;

import nth.introspect.util.converterfactory.ConverterFactory;

public class RegExpDocumentFacory { 	//XXX extend ConverterFactory<T, U> ??? 


	private static final String SINGLE_CHAR_REG_EXP = ".";
	//on char only
	private static final String BYTE_REG_EXP = "^[+-]?\\d{0,3}$";
	// ^: Match must start at beginning of string
	// [+-]: The hyphen-minus character, used to denote a negative integer
	// ?: May or may not occur
	// /d: Any character whose ASCII code (or Unicode code point) is between '0' and '9'
	// {0,3}: No more than 3 times
	// $: Match must end at end of string
	private static final String SHORT_REG_EXP = "^[+-]?\\d{0,5}$";
	// ^: Match must start at beginning of string
	// [+-]: The hyphen-minus character, used to denote a negative integer
	// ?: May or may not occur
	// /d: Any character whose ASCII code (or Unicode code point) is between '0' and '9'
	// {0,5}: No more than 5 times
	// $: Match must end at end of string
	private static final String INTEGER_REG_EXP = "^[+-]?\\d{0,10}$";
	// ^: Match must start at beginning of string
	// [+-]: The hyphen-minus character, used to denote a negative integer
	// ?: May or may not occur
	// /d: Any character whose ASCII code (or Unicode code point) is between '0' and '9'
	// {0,10}: No more than ten times
	// $: Match must end at end of string
	private static final String BIG_INTEGER_EXP = "\\d*";
	// /d: Any character whose ASCII code (or Unicode code point) is between '0' and '9'
	// *: 0 or more times
	private static final String LONG_REG_EXP = "^[+-]?\\d{0,19}$";
	// ^: Match must start at beginning of string
	// [+-]: The hyphen-minus character, used to denote a negative integer
	// ?: May or may not occur
	// /d: Any character whose ASCII code (or Unicode code point) is between '0' and '9'
	// {0,19}: No more than 19 times
	// $: Match must end at end of string
	private static final String BIG_DECIMAL_REG_EXP = "[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?";
	// [+-]? # optional sign
	// (?: # required significant: either...
	// \d+ # a number
	// (?:\.\d*)? # optionally followed by a dot, optionally followed by more digits
	// | # or...
	// \.\d+ # just a dot, followed by digits (in this case required)
	// ) # end of significant
	// (?: # optional exponent
	// [eE] # required exponent indicator
	// [+-]? # optional sign
	// \d+ # required digits
	// )? # end of exponent
	private static final String FLOAT_REG_EXP = "^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$";
	
	public static Document create(Class<?> valueClass) {
		if (Character.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(SINGLE_CHAR_REG_EXP);
		} else if (AtomicInteger.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(INTEGER_REG_EXP);
		} else if (AtomicLong.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(LONG_REG_EXP);
		} else if (BigDecimal.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(BIG_DECIMAL_REG_EXP);
		} else if (BigInteger.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(BIG_INTEGER_EXP);
		} else if (Byte.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(BYTE_REG_EXP);
		} else if (Double.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(FLOAT_REG_EXP);
		} else if (Float.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(FLOAT_REG_EXP);
		} else if (Integer.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(INTEGER_REG_EXP);
		} else if (Long.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(LONG_REG_EXP);
		} else if (Short.class.isAssignableFrom(valueClass)) {
			return new RegExpDocument(SHORT_REG_EXP);
		} else {
			//TODO simple types should also be supported!
			throw new IllegalArgumentException("Property type:" + valueClass.getCanonicalName() + " is not supported.");
		}
	}
}