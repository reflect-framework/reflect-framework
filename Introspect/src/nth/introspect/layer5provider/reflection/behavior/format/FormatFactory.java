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
public class FormatFactory extends ConverterFactory<Format> {

	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;
	private final String formatPattern;
	private final Method getterMethod;
	private final Class<?> propertyType;
	
	public FormatFactory(ReflectionProvider reflectionProvider,
			LanguageProvider languageProvider, Method getterMethod) {
		this.reflectionProvider = reflectionProvider;
		this.languageProvider = languageProvider;
		this.getterMethod = getterMethod;
		this.propertyType=getterMethod.getReturnType();
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
	public Format getFormat() {
		Class<?> propertyType = getterMethod.getReturnType();
		return createConverter(propertyType);	
	}

	@Override
	public Format createCharConverter() {
		return new CharacterFormat();
	}

	@Override
	public Format createStringConverter() {
		return new StringFormat();
	}

	@Override
	public Format createDomainConverter() {
		return new DomainObjectFormat(reflectionProvider, propertyType);
	}

	@Override
	public Format createUriConverter() {
		return new UriFormat();
	}

	@Override
	public Format createCalendarConverter() {
		if (formatPattern == null || formatPattern.trim().length() == 0) {
			return new CalendarFormat();
		} else {
			return new CalendarFormat(formatPattern);
		}
	}

	@Override
	public Format createDateConverter() {
		if (formatPattern == null || formatPattern.trim().length() == 0) {
			return new SimpleDateFormat();
		} else {
			return new SimpleDateFormat(formatPattern);
		}
	}

	@Override
	public Format createEnumConverter() {
		return new EnumFormat(languageProvider);
	}

	@Override
	public Format createShortConverter() {
		return new NumericFormat(Short.class, formatPattern);
	}

	@Override
	public Format createLongConverter() {
		return new NumericFormat(Long.class, formatPattern);
	}

	@Override
	public Format createIntegerConverter() {
		return new NumericFormat(Integer.class, formatPattern);
	}

	@Override
	public Format createFloatCoverter() {
		return new NumericFormat(Float.class, formatPattern);
	}

	@Override
	public Format createDoubleConverter() {
		return new NumericFormat(Double.class, formatPattern);
	}

	@Override
	public Format createByteConverter() {
		return new NumericFormat(Byte.class, formatPattern);
	}

	@Override
	public Format createBigIntegerConverter() {
		return new NumericFormat(BigInteger.class, formatPattern);

	}

	@Override
	public Format createBigDecimalConverter() {
		return new NumericFormat(BigDecimal.class, formatPattern);
	}

	@Override
	public Format createAtomicLongConverter() {
		return new NumericFormat(AtomicLong.class, formatPattern);
	}

	@Override
	public Format createAtomicIntegerConverter() {
		return new NumericFormat(AtomicInteger.class, formatPattern);
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
