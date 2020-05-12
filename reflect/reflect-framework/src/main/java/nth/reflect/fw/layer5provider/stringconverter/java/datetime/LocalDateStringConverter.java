package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class LocalDateStringConverter implements StringConverter<LocalDate> {

	private final DateTimeFormatter dateTimeFormatter;

	public LocalDateStringConverter(String formatPattern) {
		dateTimeFormatter = createDateTimeFormatter(formatPattern);
	}

	private DateTimeFormatter createDateTimeFormatter(String formatPattern) {
		// TODO multiple language support
		if (StringUtil.isNullOrEmpty(formatPattern)) {
			return DateTimeFormatter.ISO_DATE;
		} else {
			return DateTimeFormatter.ofPattern(formatPattern);
		}
	}

	@Override
	public String toString(LocalDate value) {
		if (value == null) {
			return "";
		}

		try {
			String formatedValue = dateTimeFormatter.format(value);
			return formatedValue;

		} catch (Exception e) {
			throw new StringConverterException(this, value, e);
		}

	}

	@Override
	public LocalDate fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			TemporalAccessor temporal = dateTimeFormatter.parse(value);
			LocalDate localDate = LocalDate.from(temporal);
			return localDate;

		} catch (Exception e) {
			throw new StringConverterException(this, value, e);
		}
	}

}
