package nth.reflect.fw.generic.util;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.stringconverter.domain.EnumStringConverter;

/**
 * <p>
 * Often a {@link DomainObject} can be identified by a single property. The
 * toString() method can than simply return this property value to return the
 * title.
 * </p>
 * <p>
 * In other cases a DomainObject is identified by multiple property values. In
 * this case it is recommended to override the {@link #toString()} method and
 * using the {@link TitleBuilder} to create an identification string.
 * </p>
 * <p>
 * The {@link TitleBuilder} can be compared with a StringBuilder, but has some
 * additional functionality such as:
 * <ul>
 * <li>The “append” methods will append a separator and a given value. You can
 * use the default separator (a comma and a space) or use an “append” method
 * where you pass the separator as a first parameter followed by the value.</li>
 * <li>The “contact” methods will append a given value without a separator</li>
 * <li>Both “append” and “contact” methods will ignore null values or reference
 * objects that have not overridden the toString method</li>
 * <li>Both “append” and “contact” methods have methods where you can specify
 * format values (e.g. date, time and numbers)</li>
 * <li>Enumeration values will be translated to a readable format</li>
 * </ul>
 * TODO example
 * 
 * @author nilsth
 * 
 */

public class TitleBuilder {

	private static final String COMMA_SPACE_SEPARATOR = ", ";
	public static final String DEFAULT_SEPARATOR = COMMA_SPACE_SEPARATOR;
	private String separator;
	private final StringBuilder title;

	/**
	 * FIXME: We create a new {@link DefaultLanguageProvider} here because the
	 * {@link NumericFormat} needs it to be able to throw a error message in the
	 * correct language. We do not want to pass a {@link LanguageProvider} to the
	 * {@link TitleBuilder} every time it is needed.
	 */

	public TitleBuilder() {
		this.title = new StringBuilder();
		this.separator = DEFAULT_SEPARATOR;
	}

	public TitleBuilder setSeperator(String seperator) {
		this.separator = seperator;
		return this;
	}

	public TitleBuilder append(final String text) {
		append(separator, text);
		return this;
	}

	public TitleBuilder append(final Object object) {
		append(separator, object);
		return this;
	}

	public TitleBuilder append(final String separator, final String text) {
		if (!isEmpty(text)) {
			appendSeparatorIfNeeded(separator);
			title.append(text);
		}
		return this;
	}

	public TitleBuilder append(final String separator, final Object object) {
		String title = titleFor(object);
		append(separator, title);
		return this;
	}

	public TitleBuilder append(final Date date, final String formatPattern) {
		append(separator, date, formatPattern);
		return this;
	}

	public TitleBuilder append(final String separator, final Date date, final String formatPattern) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
			String text = dateFormat.format(date);
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder append(Number number, String formatPattern) {
		if (number != null && formatPattern != null && !formatPattern.trim().isEmpty()) {
			Format format = new DecimalFormat(formatPattern);
			append(number, format);
		}
		return this;
	}

	public TitleBuilder append(final Number number, final Format format) {
		append(separator, number, format);
		return this;
	}

	public TitleBuilder append(final String separator, final Number number, final String formatPattern) {
		if (number != null) {
			Format format = new DecimalFormat(formatPattern);
			append(separator, number, format);
		}
		return this;
	}

	public TitleBuilder append(final String separator, final Number number, final Format format) {
		if (number != null) {
			String text = format.format(number);
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder append(final Enum<?> enumeration) {
		append(separator, enumeration);
		return this;
	}

	public TitleBuilder append(final Enum<?> enumeration, LanguageProvider languageProvider) {
		append(separator, enumeration, languageProvider);
		return this;
	}

	public TitleBuilder append(final String separator, final Enum<?> enumeration) {
		if (enumeration != null) {
			String text = StringUtil.eliphantCaseToNormal(enumeration.toString());
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder append(final String separator, final Enum<?> enumeration, LanguageProvider languageProvider) {
		if (enumeration != null) {
			String text = EnumStringConverter.enumToString(languageProvider, enumeration);
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder contact(String text) {
		if (!isEmpty(text)) {
			title.append(text);
		}
		return this;
	}

	public TitleBuilder contact(Object object) {
		String title = titleFor(object);
		contact(title);
		return this;
	}

	public TitleBuilder contact(Date date, String formatPattern) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
			String text = dateFormat.format(date);
			contact(text);
		}
		return this;
	}

	public TitleBuilder contact(Number number, String formatPattern) {
		if (number != null && formatPattern != null && !formatPattern.trim().isEmpty()) {
			Format format = new DecimalFormat(formatPattern);
			contact(number, format);
		}
		return this;
	}

	public TitleBuilder contact(Number number, Format format) {
		if (number != null) {
			String text = format.format(number);
			contact(text);
		}
		return this;
	}

	public TitleBuilder contact(Enum<?> enumeration) {
		if (enumeration != null) {
			String text = StringUtil.eliphantCaseToNormal(enumeration.toString());
			contact(text);
		}
		return this;
	}

	public TitleBuilder contact(Enum<?> enumeration, LanguageProvider languageProvider) {
		if (enumeration != null) {
			String text = EnumStringConverter.enumToString(languageProvider, enumeration);
			contact(text);
		}
		return this;
	}

	@Override
	public String toString() {
		return title.toString();
	}

	private String titleFor(Object object) {
		return (object == null) ? null : object.toString();
	}

	private boolean isEmpty(String text) {
		return text == null || text.length() == 0;
	}

	/**
	 * Only adds a separator to the title if it is not empty
	 * 
	 * @param separator
	 */
	private void appendSeparatorIfNeeded(String separator) {
		if (title.length() > 0) {
			title.append(separator);
		}
	}

}
