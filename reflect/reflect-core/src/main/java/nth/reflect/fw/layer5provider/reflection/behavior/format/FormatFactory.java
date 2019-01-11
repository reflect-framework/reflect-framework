package nth.reflect.fw.layer5provider.reflection.behavior.format;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javafx.util.StringConverter;
import nth.reflect.fw.generic.converterfactory.ConverterFactory;
import nth.reflect.fw.generic.util.JavaTypeConverter;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.BooleanFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.CalendarFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.CharacterFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.DomainObjectFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.EnumFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.FileFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.LocalDateFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.LocalDateTimeFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.LocalTimeFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.NoFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.NumericFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.StringFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.UriFormat;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.UrlFormat;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
/**
 * /**
 * Gets the pattern from the {@link Format} annotation and gets the {@link Format} for a {@link PropertyInfo}
 * 
 * See also {@link StringConverter} for inspiration and alternative for {@link Format}
 * 
 * @author nilsth
 *
 */
public class FormatFactory extends ConverterFactory<Format> {

	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;
	private final String formatPattern;
	private final Class<?> propertyType;
	
	public FormatFactory(ReflectionProvider reflectionProvider,
			LanguageProvider languageProvider, Method getterMethod) {
		this.reflectionProvider = reflectionProvider;
		this.languageProvider = languageProvider;
		this.propertyType=JavaTypeConverter.getComplexType(getterMethod.getReturnType());
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
		return createConverter(languageProvider, propertyType);	
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
		return new NumericFormat(languageProvider,Short.class, formatPattern);
	}

	@Override
	public Format createLongConverter() {
		return new NumericFormat(languageProvider,Long.class, formatPattern);
	}

	@Override
	public Format createIntegerConverter() {
		return new NumericFormat(languageProvider,Integer.class, formatPattern);
	}

	@Override
	public Format createFloatCoverter() {
		return new NumericFormat(languageProvider,Float.class, formatPattern);
	}

	@Override
	public Format createDoubleConverter() {
		return new NumericFormat(languageProvider,Double.class, formatPattern);
	}

	@Override
	public Format createByteConverter() {
		return new NumericFormat(languageProvider,Byte.class, formatPattern);
	}

	@Override
	public Format createBigIntegerConverter() {
		return new NumericFormat(languageProvider,BigInteger.class, formatPattern);

	}

	@Override
	public Format createBigDecimalConverter() {
		return new NumericFormat(languageProvider,BigDecimal.class, formatPattern);
	}

	@Override
	public Format createAtomicLongConverter() {
		return new NumericFormat(languageProvider,AtomicLong.class, formatPattern);
	}

	@Override
	public Format createAtomicIntegerConverter() {
		return new NumericFormat(languageProvider,AtomicInteger.class, formatPattern);
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
		return new LocalTimeFormat(formatPattern);
	}

	@Override
	public Format createLocalDateConverter() {
		return new LocalDateFormat(formatPattern);
	}

	@Override
	public Format createLocalDateTimeConverter() {
		return new LocalDateTimeFormat(formatPattern);

	}

	private String createFormatPattern(Method getterMethod) {
		nth.reflect.fw.layer5provider.reflection.behavior.format.Format format = getterMethod.getAnnotation(nth.reflect.fw.layer5provider.reflection.behavior.format.Format.class);
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
