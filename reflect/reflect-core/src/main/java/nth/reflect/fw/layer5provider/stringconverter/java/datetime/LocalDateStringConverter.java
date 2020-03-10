package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class LocalDateStringConverter extends StringConverter<LocalDate> {

	private final DateTimeFormatter dateTimeFormatter;

	public LocalDateStringConverter(DependencyInjectionContainer container, String formatPattern) {
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
