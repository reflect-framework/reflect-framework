package nth.introspect.provider.domain.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.util.converterfactory.ConverterFactory;

public class FormatFactory extends ConverterFactory<Format, PropertyInfo> {

	/**
	 * This method should only called by {@link PropertyInfo} so that the format
	 * cashed!!!
	 * 
	 * @param propertyInfo
	 *            of property to be converted
	 * @return a format that will format a property to a string or parse from a
	 *         string to property
	 */
	public Format create(PropertyInfo propertyInfo) {
		Class<?> propertyType = propertyInfo.getPropertyType().getType();
		return createConverter(propertyType, propertyInfo);	
	}

	// public static Format create(Class<?> type, String formatPattern) {
	// boolean hasFormatPatern = formatPattern != null &&
	// formatPattern.trim().length() > 0;
	//
	// if (CharSequence.class.isAssignableFrom(type)) {
	// return new NoFormat();
	// } else if (Enum.class.isAssignableFrom(type)) {
	// return new EnumFormat();
	// } else if (TypeUtil.isNumber(type)) {
	// if (hasFormatPatern) {
	// return new DecimalFormat(formatPattern);
	// } else {
	// if (TypeUtil.isShort(type)) {
	// return new ShortFormat();
	// } else if (type.isAssignableFrom(Integer.class)) {
	// return new IntegerFormat();
	// } else {
	// return new DecimalFormat();
	// }
	// }
	// } else if (TypeUtil.isChar(type)) {
	// return new CharacterFormat();
	// } else if (Date.class.isAssignableFrom(type)) {//TODO Calendar
	// if (hasFormatPatern) {
	// return new SimpleDateFormat(formatPattern);
	// } else {
	// LanguageProvider languageProvider = Introspect.getLanguageProvider();
	// Locale locale = languageProvider.getDefaultLocale();
	// return DateFormat.getDateInstance(DateFormat.SHORT, locale);
	// }
	// } else if (!type.getCanonicalName().startsWith("java")) {
	// // non java class so assuming we have a domain object
	// return new DomainObjectFormat(type);
	// } else {
	// // unknown type, use default formatting (toString)
	// return new NoFormat();
	// }
	// }

	@Override
	public Format createCharConverter(PropertyInfo propertyInfo) {
		return new CharacterFormat();
	}

	@Override
	public Format createStringConverter(PropertyInfo propertyInfo) {
		return new NoFormat();
	}

	@Override
	public Format createDomainConverter(PropertyInfo propertyInfo) {
		Class<?> propertyType = propertyInfo.getPropertyType().getType();
		return new DomainObjectFormat(propertyType);
	}

	@Override
	public Format createUriConverter(PropertyInfo propertyInfo) {
		return new NoFormat();
	}

	@Override
	public Format createCalendarConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		if (formatPattern == null || formatPattern.trim().length() == 0) {
			return new CalendarFormat();
		} else {
			return new CalendarFormat(formatPattern);
		}
	}

	@Override
	public Format createDateConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		if (formatPattern == null || formatPattern.trim().length() == 0) {
			return new SimpleDateFormat();
		} else {
			return new SimpleDateFormat(formatPattern);
		}
	}

	@Override
	public Format createEnumConverter(PropertyInfo propertyInfo) {
		return new EnumFormat();
	}

	@Override
	public Format createShortConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(Short.class, formatPattern);
	}

	@Override
	public Format createLongConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(Long.class, formatPattern);
	}

	@Override
	public Format createIntegerConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(Integer.class, formatPattern);
	}

	@Override
	public Format createFloatCoverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(Float.class, formatPattern);
	}

	@Override
	public Format createDoubleConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(Double.class, formatPattern);
	}

	@Override
	public Format createByteConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(Byte.class, formatPattern);
	}

	@Override
	public Format createBigIntegerConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(BigInteger.class, formatPattern);

	}

	@Override
	public Format createBigDecimalConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(BigDecimal.class, formatPattern);
	}

	@Override
	public Format createAtomicLongConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(AtomicLong.class, formatPattern);
	}

	@Override
	public Format createAtomicIntegerConverter(PropertyInfo propertyInfo) {
		String formatPattern = propertyInfo.getFormatPattern();
		return new NumericFormat(AtomicInteger.class, formatPattern);
	}

	@Override
	public Format createBooleanConverter(PropertyInfo metadata) {
		return new BooleanFormat();
	}

	@Override
	public Format createCollectionConverter(PropertyInfo metadata) {
		return new NoFormat();
	}

}
