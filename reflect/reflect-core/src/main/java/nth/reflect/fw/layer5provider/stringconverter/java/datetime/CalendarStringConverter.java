package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class CalendarStringConverter extends StringConverter<Calendar> {

	private final SimpleDateFormat format;

	public CalendarStringConverter(DependencyInjectionContainer container, String formatPattern) {
		super(container, formatPattern);
		format = createFormat();
	}

	private SimpleDateFormat createFormat() {
		if (formatPattern.isPresent()) {
			return new SimpleDateFormat(formatPattern.get());
		} else {
			return new SimpleDateFormat();
		}
	}

	@Override
	public String toString(Calendar value) {
		if (value == null) {
			return "";
		}
		format.setCalendar(value);
		String formatedValue = format.format(value.getTime());
		return formatedValue;
	}

	@Override
	public Calendar fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			Date date = format.parse(value);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;

		} catch (ParseException e) {
			throw new StringConverterException(this, value, e);
		}
	}

}
