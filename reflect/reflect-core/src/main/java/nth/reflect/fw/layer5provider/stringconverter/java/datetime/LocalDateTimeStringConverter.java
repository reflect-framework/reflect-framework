package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class LocalDateTimeStringConverter extends StringConverter<LocalDateTime> {

	private final DateTimeFormatter dateTimeFormatter;

	public LocalDateTimeStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
		dateTimeFormatter = createDateTimeFormatter();
	}

	private DateTimeFormatter createDateTimeFormatter() {
		// TODO multi language
		if (formatPattern.isPresent()) {
			return DateTimeFormatter.ofPattern(formatPattern.get());
		} else {
			return DateTimeFormatter.ISO_DATE;
		}
	}

	@Override
	public String toString(LocalDateTime value) {
		if (value == null) {
			return "";
		}
		String formatedValue = dateTimeFormatter.format(value);
		return formatedValue;
	}

	@Override
	public LocalDateTime fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			TemporalAccessor temporal = dateTimeFormatter.parse(value);
			LocalDateTime localDateTime = LocalDateTime.from(temporal);
			return localDateTime;

		} catch (Exception e) {
			throw new StringConverterException(this, value, e);
		}
	}

}
