package nth.introspect.layer5provider.reflection.behavior.format;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import nth.introspect.generic.converterfactory.ConverterFactory;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.format.impl.BooleanFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.CalendarFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.CharacterFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.DomainObjectFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.EnumFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.FileFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.NoFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.NumericFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.StringFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.UriFormat;
import nth.introspect.layer5provider.reflection.behavior.format.impl.UrlFormat;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
/**
 * /**
 * Gets the pattern from the {@link Format} annotation and gets the {@link Format} for a {@link PropertyInfo}
 * 
 * @author nilsth
 *
 */
public class FormatFactory extends ConverterFactory<Format, PropertyInfo> {

	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;
	private final String formatPattern;
	
	public FormatFactory(ReflectionProvider reflectionProvider,
			LanguageProvider languageProvider, Method getterMethod) {
		this.reflectionProvider = reflectionProvider;
		this.languageProvider = languageProvider;
		this.formatPattern=createFormatPattern(getterMethod);
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
	public Format getFormat(PropertyInfo propertyInfo) {
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
		return new DomainObjectFormat(reflectionProvider, propertyType);
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
		return new EnumFormat(languageProvider);
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
	
	private String createFormatPattern(Method getterMethod) {
		nth.introspect.layer5provider.reflection.behavior.format.Format format = getterMethod.getAnnotation(nth.introspect.layer5provider.reflection.behavior.format.Format.class);
		if (format == null) {
			return null;
		} else {
			return format.pattern();
		}
	}

	public String getFormatPattern() {
		return formatPattern;
	}
}
