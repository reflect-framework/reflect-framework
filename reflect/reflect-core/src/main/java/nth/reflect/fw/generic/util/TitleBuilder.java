package nth.reflect.fw.generic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.NumericFormat;

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
 * </p>
 * <p>
 * The {@link TitleBuilder} is a singleton so that we do not have to create a
 * {@link TitleBuilder} every time an domain objects needs to create an
 * identification string in the {@link #toString()} method.
 * </p>
 * TODO example
 * 
 * @author nilsth
 * 
 */

public class TitleBuilder {

	private static final String COMMA_SPACE_SEPARATOR = ", ";
	public static final String DEFAULT_SEPARATOR = COMMA_SPACE_SEPARATOR;
	private final ThreadLocal<String> separator;
	private final ThreadLocal<StringBuilder> title;
	private final LanguageProvider languageProvider;
	private static final TitleBuilder titleBuilderInstance = new TitleBuilder();

	/**
	 * Private constructor: Use {@link #getInstance()}<br>
	 * FIXME: We create a new {@link DefaultLanguageProvider} here because the
	 * {@link NumericFormat} needs it to be able to throw a error message in the
	 * correct language. We do not want to pass a {@link LanguageProvider} to the
	 * {@link TitleBuilder} everytime it is needed.
	 * 
	 * @param seperator
	 */

	private TitleBuilder() {
		this.title = new ThreadLocal<StringBuilder>();
		this.separator = new ThreadLocal<String>();
		this.languageProvider = new DefaultLanguageProvider();
	}

	public static synchronized TitleBuilder getInstance() {
		return getInstance(DEFAULT_SEPARATOR);
	}

	/**
	 * @param seperator to separate the values
	 * @return a re-initialized {@link TitleBuilder}. This is a singleton so that we
	 *         do not have to create a {@link TitleBuilder} every time an domain
	 *         objects needs to be displayed (e.g. in a {@link #toString()} method)
	 */
	public static synchronized TitleBuilder getInstance(String seperator) {
		titleBuilderInstance.clear();
		titleBuilderInstance.setSeperator(seperator);
		return titleBuilderInstance;
	}

	// TODO: create title of object using reflection (using
	// @PropertyActionMethod, @Hidden notation and ignoring properties of type
	// collection). This requires the ReflectionProvider. This is no problem if
	// the TitleBuilder is injected into the object (improves peformance because
	// titlebuilder does not have to be instantiated every toString call)
	// public TitleBuilder(final Object object) {
	//
	// }

	/**
	 * Creates a new StringBuilder if this method is called for the first time in
	 * the current thread. Otherwise it will reuse the {@link StringBuilder} by
	 * clearing it (setting its length to 0)
	 */
	private void clear() {
		if (title.get() == null) {
			title.set(new StringBuilder());
		} else {
			title.get().setLength(0);
		}
	}

	private void setSeperator(String seperator) {
		this.separator.set(seperator);
	}

	public TitleBuilder append(final String text) {
		append(separator.get(), text);
		return this;
	}

	public TitleBuilder append(final Object object) {
		append(separator.get(), object);
		return this;
	}

	public TitleBuilder append(final String separator, final String text) {
		if (!isEmpty(text)) {
			appendSeparatorIfNeeded(separator);
			title.get().append(text);
		}
		return this;
	}

	public TitleBuilder append(final String separator, final Object object) {
		String title = titleFor(object);
		append(separator, title);
		return this;
	}

	public TitleBuilder append(final Date date, final String formatPattern) {
		append(separator.get(), date, formatPattern);
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

	public TitleBuilder append(final Number number, final String formatPattern) {
		append(separator.get(), number, formatPattern);
		return this;
	}

	public TitleBuilder append(final String separator, final Number number, final String formatPattern) {
		if (number != null) {
			NumericFormat numberFormat = new NumericFormat(languageProvider, number.getClass(), formatPattern);
			String text = numberFormat.format(number);
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder append(final Enum<?> enumeration) {
		append(separator.get(), enumeration);
		return this;
	}

	public TitleBuilder append(final String separator, final Enum<?> enumeration) {
		if (enumeration != null) {
			String text = StringUtil.eliphantCaseToNormal(enumeration.toString());
			append(separator, text);
		}
		return this;
	}

	public TitleBuilder contact(String text) {
		if (!isEmpty(text)) {
			title.get().append(text);
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
			NumericFormat numberFormat = new NumericFormat(languageProvider, number.getClass(), formatPattern);
			String text = numberFormat.format(number);
			contact(text);
		}
		return this;
	}

	public TitleBuilder contact(Enum<?> enumeration) {
		if (enumeration != null) {
			// TODO multi language
			String text = StringUtil.eliphantCaseToNormal(enumeration.toString());
			contact(text);
		}
		return this;
	}

	@Override
	public String toString() {
		return title.get().toString();
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
		if (title.get().length() > 0) {
			title.get().append(separator);
		}
	}

}
