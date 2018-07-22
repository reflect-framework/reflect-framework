package nth.reflect.fw.generic.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.reflect.fw.layer5provider.language.LanguageProvider;


public class StringConverter {

	
	
	@SuppressWarnings("rawtypes")
	public static  String from(Object value, LanguageProvider languageProvider) {
		if (value == null) {
			return null;
		}
		Class<? extends Object> valueClass = value.getClass();
		if (Character.class.isAssignableFrom(valueClass)) {
			return fromChar((Character) value);
		} else if (AtomicInteger.class.isAssignableFrom(valueClass)) {
			return fromAtomicInteger((AtomicInteger) value);
		} else if (AtomicLong.class.isAssignableFrom(valueClass)) {
			return fromAtomicLong((AtomicLong) value);
		} else if (BigDecimal.class.isAssignableFrom(valueClass)) {
			return fromBigDecimal((BigDecimal)value);
		} else if (BigInteger.class.isAssignableFrom(valueClass)) {
			return fromBigInteger((BigInteger)value);
		} else if (Byte.class.isAssignableFrom(valueClass)) {
			return fromByte((Byte)value);
		} else if (Double.class.isAssignableFrom(valueClass)) {
			return fromDouble((Double)value);
		} else if (Float.class.isAssignableFrom(valueClass)) {
			return fromFloat((Float)value);
		} else if (Integer.class.isAssignableFrom(valueClass)) {
			return fromInteger((Integer)value);
		} else if (Long.class.isAssignableFrom(valueClass)) {
			return fromLong((Long)value);
		} else if (Short.class.isAssignableFrom(valueClass)) {
			return fromShort((Short)value);
		} else if (value.getClass().isEnum()) {
			return fromEnum((Enum)value, languageProvider);
		} else {
			throw new RuntimeException("String conversion from type:" + valueClass.getCanonicalName() + " is not suported");
		}
	}
	

	private static String fromEnum(Object value, LanguageProvider languageProvider) {
		String key = languageProvider.getKey(value);
		String defaultValue = languageProvider.getDefaultValue(key);
		return  languageProvider.getText(key, defaultValue);
	}


	//TODO enum and domainobject
	
	public static  Object to(String text,Class<?> destinationClass) {
		if (text == null) {
			return null;
		}
		if (Character.class.isAssignableFrom(destinationClass)) {
			return toChar( text);
		} else if (AtomicInteger.class.isAssignableFrom(destinationClass)) {
			return toAtomicInteger( text);
		} else if (AtomicLong.class.isAssignableFrom(destinationClass)) {
			return toAtomicLong( text);
		} else if (BigDecimal.class.isAssignableFrom(destinationClass)) {
			return toBigDecimal(text);
		} else if (BigInteger.class.isAssignableFrom(destinationClass)) {
			return toBigInteger(text);
		} else if (Byte.class.isAssignableFrom(destinationClass)) {
			return toByte(text);
		} else if (Double.class.isAssignableFrom(destinationClass)) {
			return toDouble(text);
		} else if (Float.class.isAssignableFrom(destinationClass)) {
			return toFloat(text);
		} else if (Integer.class.isAssignableFrom(destinationClass)) {
			return toInteger(text);
		} else if (Long.class.isAssignableFrom(destinationClass)) {
			return toLong(text);
		} else if (Short.class.isAssignableFrom(destinationClass)) {
			return toShort(text);
		} else {
			throw new RuntimeException("String conversion to type:" + destinationClass.getCanonicalName() + " is not suported");
		}

	}

	public static short toShort(String text) {
		return Short.parseShort(text);
	}

	public static float toFloat(String text) {
		return Float.parseFloat(text);
	}

	public static double toDouble(String text) {
		return Double.parseDouble(text);
	}

	public static byte toByte(String text) {
		return Byte.parseByte(text);
	}

	public static BigInteger toBigInteger(String text) {
		return new BigInteger(text);
	}

	public static BigDecimal toBigDecimal(String text) {
		return new BigDecimal(text);
	}

	public static AtomicLong toAtomicLong(String text) {
		return new AtomicLong(toLong(text));
	}

	public static long toLong(String text) {
		return Long.parseLong(text);
	}

	public static int toInteger(String text) {
		return Integer.parseInt(text);
	}

	
	public static AtomicInteger toAtomicInteger(String text) {
		return new AtomicInteger(toInteger(text));
	}


	public static char toChar(String text) {
		return (text==null||text.length()<1)?null:text.charAt(0);
	}

	public static String fromShort(Short value) {
		return Short.toString(value);
	}


	public static String fromLong(Long value) {
		return Long.toString(value);
	}


	public static String fromInteger(Integer value) {
		return Integer.toString(value);
	}


	public static String fromFloat(Float value) {
		return Float.toString(value);
	}


	public static String fromDouble(Double value) {
		return Double.toString(value);
	}


	public static String fromByte(Byte value) {
		return Byte.toString(value);
	}


	public static String fromBigInteger(BigInteger value) {
		return value.toString();// TODO validate
	}


	public static String fromBigDecimal(BigDecimal value) {
		return value.toString();// TODO validate
	}


	public static String fromAtomicLong(AtomicLong value) {
		return value.toString();// TODO validate
	}

	public static String fromAtomicInteger(AtomicInteger value) {
		return value.toString();// TODO validate
	}

	public static String fromChar(Character value) {
		return String.valueOf((char)value);

	}
}
