package nth.introspect.provider.domain.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.util.converterfactory.ConverterFactory;

public class PropertyInfoFormatFactory extends ConverterFactory<Format, PropertyInfo> {

	private DomainInfoProvider domainInfoProvider;

	public PropertyInfoFormatFactory(DomainInfoProvider domainInfoProvider) {
		this.domainInfoProvider = domainInfoProvider;
	}
	
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

	@Override
	public Format createCharConverter(PropertyInfo propertyInfo) {
		return new CharacterFormat();
	}

	@Override
	public Format createStringConverter(PropertyInfo propertyInfo) {
		return new StringFormat();
	}

	@Override
	public Format createDomainConverter( PropertyInfo propertyInfo) {
		Class<?> propertyType = propertyInfo.getPropertyType().getType();
		return new DomainObjectFormat(domainInfoProvider, propertyType);
	}

	@Override
	public Format createUriConverter(PropertyInfo propertyInfo) {
		return new UriFormat();
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

	@Override
	public Format createFileConverter(PropertyInfo metadata) {
		return new FileFormat();
	}

	@Override
	public Format createUrlConverter(PropertyInfo metadata) {
		return new UrlFormat();
	}

}
