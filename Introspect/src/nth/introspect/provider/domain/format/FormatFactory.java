package nth.introspect.provider.domain.format;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.util.TypeUtil;

public class FormatFactory {
	public static Format create(PropertyInfo propertyInfo) {
		Class<?> returnType = propertyInfo.getPropertyType().getType();
		String formatPattern = propertyInfo.getFormatPattern();
		return create(returnType, formatPattern);
	}

	public static Format create(Class<?> type, String formatPattern) {
		boolean hasFormatPatern = formatPattern != null && formatPattern.trim().length() > 0;

		if (CharSequence.class.isAssignableFrom(type)) {
			return new NoFormat();
		} else if (Enum.class.isAssignableFrom(type)) {
			return new EnumFormat();
		} else if (TypeUtil.isNumber(type)) {
			if (hasFormatPatern) {
				return new DecimalFormat(formatPattern);
			} else {
				if (TypeUtil.isShort(type)) {
					return new ShortFormat();					
				} else {
					return new DecimalFormat();
				}
			}
		} else if (TypeUtil.isChar(type)) {
			return new CharacterFormat();
		} else if (Date.class.isAssignableFrom(type)) {//TODO Calendar
			if (hasFormatPatern) {
				return new SimpleDateFormat(formatPattern);
			} else {
				LanguageProvider languageProvider = Introspect.getLanguageProvider();
				Locale locale = languageProvider.getDefaultLocale();
				return DateFormat.getDateInstance(DateFormat.SHORT, locale);
			}
		} else if (!type.getCanonicalName().startsWith("java")) {
			// non java class so assuming we have a domain object
			return new DomainObjectFormat(type);
		} else {
			// unknown type, use default formatting (toString)
			return new NoFormat();
		}
	}

}
