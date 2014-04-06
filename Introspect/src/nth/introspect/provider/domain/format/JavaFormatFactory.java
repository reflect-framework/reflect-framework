package nth.introspect.provider.domain.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.util.converterfactory.ConverterFactory;
import nth.introspect.util.exception.MethodNotSupportedException;

public class JavaFormatFactory extends ConverterFactory<Format, String> {

	/**
	 * Creates a formatter for a java type (Sting, Integer, boolean, etc)
	 * 
	 * @param propertyInfo
	 *            of property to be converted
	 * @return a format that will format a property to a string or parse from a
	 *         string to property
	 */
	public Format create(Class<?> type) {
		return createConverter(type, null);	
	}


	@Override
	public Format createCharConverter(String format) {
		return new CharacterFormat();
	}

	@Override
	public Format createStringConverter(String format) {
		return new NoFormat();
	}

	@Override
	public Format createDomainConverter(String format) {
		throw new MethodNotSupportedException();
	}

	@Override
	public Format createUriConverter(String format) {
		return new UriFormat();
	}

	@Override
	public Format createCalendarConverter(String format) {
			return new CalendarFormat();
	}

	@Override
	public Format createDateConverter(String format) {
			return new SimpleDateFormat();
	}

	@Override
	public Format createEnumConverter(String format) {
		return new EnumFormat();
	}

	@Override
	public Format createShortConverter(String format) {
		return new NumericFormat(Short.class, format);
	}

	@Override
	public Format createLongConverter(String format) {
		return new NumericFormat(Long.class, format);
	}

	@Override
	public Format createIntegerConverter(String format) {
		return new NumericFormat(Integer.class, format);
	}

	@Override
	public Format createFloatCoverter(String format) {
		return new NumericFormat(Float.class, format);
	}

	@Override
	public Format createDoubleConverter(String format) {
		return new NumericFormat(Double.class, format);
	}

	@Override
	public Format createByteConverter(String format) {
		return new NumericFormat(Byte.class, format);
	}

	@Override
	public Format createBigIntegerConverter(String format) {
		return new NumericFormat(BigInteger.class, format);

	}

	@Override
	public Format createBigDecimalConverter(String format) {
		return new NumericFormat(BigDecimal.class, format);
	}

	@Override
	public Format createAtomicLongConverter(String format) {
		return new NumericFormat(AtomicLong.class, format);
	}

	@Override
	public Format createAtomicIntegerConverter(String format) {
		return new NumericFormat(AtomicInteger.class, format);
	}

	@Override
	public Format createBooleanConverter(String format) {
		return new BooleanFormat();
	}

	@Override
	public Format createCollectionConverter(String format) {
		return new NoFormat();
	}


	@Override
	public Format createFileConverter(String metadata) {
		return new FileFormat();
	}


	@Override
	public Format createUrlConverter(String metadata) {
		return new UrlFormat();
	}

}
