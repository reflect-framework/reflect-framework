package nth.reflect.fw.generic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.NumericFormat;

/**
 * <p>
 * Often a {@link DomainObject} can be identified by a single property. The
 * toString() method can than simply return this property value to return the
 * title.
 * </p>
 * <p>
 * In other cases a DomainObject is identified by multiple property values. In
 * this case it is recommended to use the TitleBuilder. The TitleBuilder can be
 * compared with a StringBuilder, but has some additional functionality such as:
 * <ul>
 * <li>The “append” methods will append a separator and a given value. You can
 * use the default separator (a comma and a space) or use an “append” method where you pass
 * the separator as a first parameter followed by the value.</li>
 * <li>The “contact” methods will append a given value without a separator</li>
 * <li>Both “append” and “contact” methods will ignore null values or reference
 * objects that do not have a toString implementation</li>
 * <li>Both “append” and “contact” methods have methods where you can specify
 * format values (e.g. date, time and numbers)</li>
 * <li>Enumeration values will be translated to a readable format</li>
 * </ul>
 * </p>
 * 
 * TODO example
 * 
 * @author nilsth
 * 
 */
public class TitleBuilder {

	private static final String COMMA_SPACE_SEPARATOR = ", ";
	public static final String DEFAULT_SEPARATOR = COMMA_SPACE_SEPARATOR;
	private final String defaultSeparator;
	private final StringBuilder titleBuilder;

	public TitleBuilder() {
		this(DEFAULT_SEPARATOR);
	}

	public TitleBuilder(String defaultSeparator) {
		this.defaultSeparator = defaultSeparator;
		this.titleBuilder = new StringBuilder();
	}

	//TODO: create title of object using reflection (using @Order, @Hidden notation and ignoring properties of type collection). This requires the ReflectionProvider. This is no problem if the TitleBuilder is injected into the object (improves peformance because titlebuilder does not have to be instantiated every toString call)
//	public TitleBuilder(final Object object) {
//		
//	}

	public TitleBuilder append(final String text) {
		append(defaultSeparator, text);
		return this;
	}

	public TitleBuilder append(final Object object) {
		append(defaultSeparator, object);
		return this;
	}

	public TitleBuilder append(final String separator, final String text) {
		if (!isEmpty(text)) {
			appendSeparatorIfNeeded(separator);
			titleBuilder.append(text);
		}
		return this;
	}

	public TitleBuilder append(final String separator, final Object object) {
		String title = titleFor(object);
		append(separator, title);
		return this;
	}

	public TitleBuilder append(final Date date, final String formatPattern) {
		append(defaultSeparator, date, formatPattern);
		return this;
	}

	public TitleBuilder append(final String separator, final Date date,
			final String formatPattern) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
			String text = dateFormat.format(date);
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder append(final Number number, final String formatPattern) {
		append(defaultSeparator, number, formatPattern);
		return this;
	}

	public TitleBuilder append(final String separator, final Number number,
			final String formatPattern) {
		if (number != null) {
			NumericFormat numberFormat = new NumericFormat(number.getClass(),
					formatPattern);
			String text = numberFormat.format(number);
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder append(final Enum<?> enumeration) {
		append(defaultSeparator, enumeration);
		return this;
	}

	public TitleBuilder append(final String separator, final Enum<?> enumeration) {
		if (enumeration != null) {
			String text = StringUtil.eliphantCaseToNormal(enumeration
					.toString());
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder contact(String text) {
		if (!isEmpty(text)) {
			titleBuilder.append(text);
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
		if (number != null) {
			NumericFormat numberFormat = new NumericFormat(number.getClass(),
					formatPattern);
			String text = numberFormat.format(number);
			contact(text);
		}
		return this;
	}

	public TitleBuilder contact(Enum<?> enumeration) {
		if (enumeration != null) {
			String text = StringUtil.eliphantCaseToNormal(enumeration
					.toString());
			contact(text);
		}
		return this;
	}

	@Override
	public String toString() {
		return titleBuilder.toString();
	}

	private String titleFor(Object object) {
		return (object == null) ? null : object.toString();
	}

	private boolean isEmpty(String text) {
		return text == null || text.length() == 0;
	}

	/**
	 * Only adds a separator to the titleBuilder if it is not empty
	 * 
	 * @param separator
	 */
	private void appendSeparatorIfNeeded(String separator) {
		if (titleBuilder.length() > 0) {
			titleBuilder.append(separator);
		}
	}
	

}
