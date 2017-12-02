package nth.introspect.layer5provider.reflection.behavior.format.impl;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Adapter for {@link DateTimeFormatter} to work with {@link LocalTime}-type
 * 
 * @author nilsth
 * 
 */
public class LocalTimeFormat extends Format {

	private static final long serialVersionUID = 4788700826780335200L;
	private final DateTimeFormatter dateTimeFormatter;

	public LocalTimeFormat() {
		this(null);
	}

	public LocalTimeFormat(String formatPattern) {
		if (formatPattern == null || formatPattern.trim().length() == 0) {
			dateTimeFormatter=DateTimeFormatter.ISO_TIME;
		} else {
			dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
		}
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		TemporalAccessor temporal=(TemporalAccessor) obj;
		String formatedValue=dateTimeFormatter.format(temporal);
		toAppendTo.append(formatedValue);
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		TemporalAccessor temporal = dateTimeFormatter.parse(source);
		return LocalTime.from(temporal);
	}

}
