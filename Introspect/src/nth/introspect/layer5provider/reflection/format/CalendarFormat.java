package nth.introspect.layer5provider.reflection.format;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Adapter for {@link SimpleDateFormat} to work with {@link Calendar}-type
 * 
 * @author nilsth
 * 
 */
public class CalendarFormat extends Format {

	private static final long serialVersionUID = 4788700826780335200L;
	private final SimpleDateFormat simpleDateFormat;

	public CalendarFormat() {
		this(null);
	}

	public CalendarFormat(String formatPattern) {
		if (formatPattern == null || formatPattern.trim().length() == 0) {
			simpleDateFormat = new SimpleDateFormat();
		} else {
			simpleDateFormat = new SimpleDateFormat(formatPattern);
		}
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		Calendar calendar = (Calendar) obj;
		simpleDateFormat.setCalendar(calendar);
		String formatedValue = simpleDateFormat.format(calendar.getTime());
		toAppendTo.append(formatedValue);
		return toAppendTo;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		Date date = simpleDateFormat.parse(source, pos);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

}
