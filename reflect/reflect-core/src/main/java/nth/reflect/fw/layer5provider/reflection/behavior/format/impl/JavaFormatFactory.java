package nth.reflect.fw.layer5provider.reflection.behavior.format.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.reflect.fw.generic.converterfactory.ConverterFactory;
import nth.reflect.fw.generic.exception.MethodNotSupportedException;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatFactory;

/**
 * @deprecated use {@link FormatFactory} instead
 * @author nilsth
 *
 */
public class JavaFormatFactory extends ConverterFactory<Format> {

	private final LanguageProvider languageProvider;

	public JavaFormatFactory(LanguageProvider languageProvider) {
		this.languageProvider = languageProvider;
	}


	/**
	 * Creates a formatter for a java type (Sting, Integer, boolean, etc)
	 * 
	 * @param propertyInfo
	 *            of property to be converted
	 * @return a format that will format a property to a string or parse from a
	 *         string to property
	 */
	public Format create(Class<?> type) {
		return createConverter(type);	
	}


	@Override
	public Format createCharConverter() {
		return new CharacterFormat();
	}

	@Override
	public Format createStringConverter() {
		return new NoFormat();
	}

	@Override
	public Format createDomainConverter() {
		throw new MethodNotSupportedException();
	}

	@Override
	public Format createUriConverter() {
		return new UriFormat();
	}

	@Override
	public Format createCalendarConverter() {
			return new CalendarFormat();
	}

	@Override
	public Format createDateConverter() {
			return new SimpleDateFormat();
	}

	@Override
	public Format createEnumConverter() {
		return new EnumFormat(languageProvider);
	}

	@Override
	public Format createShortConverter() {
		return new NumericFormat(Short.class, null);
	}

	@Override
	public Format createLongConverter() {
		return new NumericFormat(Long.class, null);
	}

	@Override
	public Format createIntegerConverter() {
		return new NumericFormat(Integer.class, null);
	}

	@Override
	public Format createFloatCoverter() {
		return new NumericFormat(Float.class, null);
	}

	@Override
	public Format createDoubleConverter() {
		return new NumericFormat(Double.class, null);
	}

	@Override
	public Format createByteConverter() {
		return new NumericFormat(Byte.class, null);
	}

	@Override
	public Format createBigIntegerConverter() {
		return new NumericFormat(BigInteger.class, null);

	}

	@Override
	public Format createBigDecimalConverter() {
		return new NumericFormat(BigDecimal.class, null);
	}

	@Override
	public Format createAtomicLongConverter() {
		return new NumericFormat(AtomicLong.class, null);
	}

	@Override
	public Format createAtomicIntegerConverter() {
		return new NumericFormat(AtomicInteger.class, null);
	}

	@Override
	public Format createBooleanConverter() {
		return new BooleanFormat();
	}

	@Override
	public Format createCollectionConverter() {
		return new NoFormat();
	}


	@Override
	public Format createFileConverter() {
		return new FileFormat();
	}


	@Override
	public Format createUrlConverter() {
		return new UrlFormat();
	}


	@Override
	public Format createLocalTimeConverter() {
		return new LocalTimeFormat();
	}


	@Override
	public Format createLocalDateConverter() {
		return new LocalDateFormat();
	}


	@Override
	public Format createLocalDateTimeConverter() {
		return new LocalTimeFormat();
	}

}