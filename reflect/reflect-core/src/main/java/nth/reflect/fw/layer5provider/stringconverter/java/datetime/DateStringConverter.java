package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class DateStringConverter extends StringConverter<Date> {

	private final SimpleDateFormat format;

	public DateStringConverter(StringConverterFactoryInfo info) {
		super(info);
		format = createFormat();
	}

	private SimpleDateFormat createFormat() {
		// TODO multi language
		if (formatPattern.isPresent()) {
			return new SimpleDateFormat(formatPattern.get());
		} else {
			return new SimpleDateFormat();
		}
	}

	@Override
	public String toString(Date value) {
		if (value == null) {
			return "";
		}
		String formatedValue = format.format(value);
		return formatedValue;
	}

	@Override
	public Date fromString(String value) {
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}

		try {
			Date date = format.parse(value);
			return date;

		} catch (ParseException e) {
			throw new StringConverterException(this, value, e);
		}
	}

}
