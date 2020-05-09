package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DateTimeTestUtil {
	private static final String SPACE = " ";

	public final static Integer YEAR = new Integer(2020);
	public final static Integer MONTH = new Integer(9);
	public final static Integer DAY_OF_MONTH = new Integer(8);
	public final static Integer HOUR = new Integer(7);
	public final static Integer MINUTE = new Integer(6);
	public final static Integer SECOND = new Integer(5);

	// Calendar
	public final static Calendar CALENDAR_WITH_DATE_TIME = Calendar.getInstance();
	static {
		CALENDAR_WITH_DATE_TIME.set(YEAR, MONTH - 1, DAY_OF_MONTH, HOUR, MINUTE, SECOND);
	}

	public final static Calendar CALENDAR_WITH_DATE = Calendar.getInstance();
	static {
		CALENDAR_WITH_DATE.set(YEAR, MONTH - 1, DAY_OF_MONTH, 0, 0, 0);
	}

	public final static Calendar CALENDAR_WITH_TIME = Calendar.getInstance();
	static {
		CALENDAR_WITH_TIME.set(1970, 0, 1, HOUR, MINUTE, SECOND);
	}

	// Date
	public static final Date DATE_WITH_DATE_TIME = CALENDAR_WITH_DATE_TIME.getTime();
	public static final Date DATE_WITH_DATE = CALENDAR_WITH_DATE.getTime();
	public static final Date DATE_WITH_TIME = CALENDAR_WITH_TIME.getTime();

	// LocalDateTime
	public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH, DAY_OF_MONTH, HOUR, MINUTE,
			SECOND);
	public static final LocalDate LOCAL_DATE = LocalDate.of(YEAR, MONTH, DAY_OF_MONTH);
	public static final LocalTime LOCAL_TIME = LocalTime.of(HOUR, MINUTE, SECOND);

	// patterns and results
	public final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	public final static String DATE_FORMAT_RESULT = "2020-09-08";

	public final static String TIME_FORMAT_PATTERN = "HH:mm:ss";
	public final static String TIME_FORMAT_RESULT = "07:06:05";

	public final static String DATE_TIME_FORMAT_PATTERN = DATE_FORMAT_PATTERN + SPACE + TIME_FORMAT_PATTERN;
	public final static String DATE_TIME_FORMAT_RESULT = DATE_FORMAT_RESULT + SPACE + TIME_FORMAT_RESULT;

}
