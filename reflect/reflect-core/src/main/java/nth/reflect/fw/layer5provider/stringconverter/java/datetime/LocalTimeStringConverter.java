package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class LocalTimeStringConverter extends StringConverter<LocalTime> {

	private final DateTimeFormatter dateTimeFormatter;

	public LocalTimeStringConverter(StringConverterFactoryInfo info) {
		super(info);
		dateTimeFormatter = createDateTimeFormatter();
	}

	private DateTimeFormatter createDateTimeFormatter() {
		// TODO multi language
		if (formatPattern.isPresent()) {
			return DateTimeFormatter.ofPattern(formatPattern.get());
		} else {
			return DateTimeFormatter.ISO_TIME;
		}
	}

	@Override
	public String toString(LocalTime value) {
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
	public LocalTime fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			TemporalAccessor temporal = dateTimeFormatter.parse(value);
			LocalTime localTime = LocalTime.from(temporal);
			return localTime;

		} catch (Exception e) {
			throw new StringConverterException(this, value, e);
		}
	}

}
