package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class CalendarStringConverterTest {

	private static final String EMPTY_STRING = "";
	private static final String SPACE = " ";
	private static final String BOGUS_STRING = "Bogus";

	public final static Integer YEAR = new Integer(2020);
	public final static Integer MONTH = new Integer(9);
	public final static Integer DAY_OF_MONTH = new Integer(8);
	public final static Integer HOUR = new Integer(7);
	public final static Integer MINUTE = new Integer(6);
	public final static Integer SECOND = new Integer(5);

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

	public final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
	public final static String DATE_FORMAT_RESULT = "2020-09-08";

	public final static String TIME_FORMAT_PATTERN = "HH:mm:ss";
	public final static String TIME_FORMAT_RESULT = "07:06:05";

	public final static String DATE_TIME_FORMAT_PATTERN = DATE_FORMAT_PATTERN + SPACE + TIME_FORMAT_PATTERN;
	public final static String DATE_TIME_FORMAT_RESULT = DATE_FORMAT_RESULT + SPACE + TIME_FORMAT_RESULT;

	@Test
	public void testToString_givenNullAndNullFormat_mustReturnEmptyString() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, null);
		String result = calendarStringConverter.toString(null);
		assertThat(result).isEqualTo(EMPTY_STRING);
	}

	@Test
	public void testToString_givenCalendarAndNullFormat_mustReturnCalendar() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, null);
		String result = calendarStringConverter.toString(CALENDAR_WITH_DATE_TIME);
		SimpleDateFormat format = new SimpleDateFormat();
		String expected = format.format(CALENDAR_WITH_DATE_TIME.getTime());
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenCalendarAndDateFormat_mustReturnDateString() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, DATE_FORMAT_PATTERN);
		String result = calendarStringConverter.toString(CALENDAR_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DATE_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenCalendarAndTimeFormat_mustReturnTimeString() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, TIME_FORMAT_PATTERN);
		String result = calendarStringConverter.toString(CALENDAR_WITH_DATE_TIME);
		assertThat(result).isEqualTo(TIME_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenCalendarAndDateTimeFormat_mustReturnDateTimeString() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, DATE_TIME_FORMAT_PATTERN);
		String result = calendarStringConverter.toString(CALENDAR_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DATE_TIME_FORMAT_RESULT);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, null);
		Calendar result = calendarStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptyString_mustReturnNull() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, null);
		Calendar result = calendarStringConverter.fromString(EMPTY_STRING);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenBogusValue_mustThrowException() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, null);
		assertThrows(StringConverterException.class, () -> {
			calendarStringConverter.fromString(BOGUS_STRING);
		});
	}

	@Test
	public void testFromString_givenSpaceDate_mustReturnCalendarDate() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, DATE_FORMAT_PATTERN);
		Calendar result = calendarStringConverter.fromString(SPACE + DATE_FORMAT_RESULT);
		assertThat(result.getTimeInMillis() / 1000).isEqualTo(CALENDAR_WITH_DATE.getTimeInMillis() / 1000);
	}

	@Test
	public void testFromString_givenTimeSpace_mustReturnCalendarTime() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, TIME_FORMAT_PATTERN);
		Calendar result = calendarStringConverter.fromString(TIME_FORMAT_RESULT + SPACE);
		assertThat(result.getTimeInMillis() / 1000).isEqualTo(CALENDAR_WITH_TIME.getTimeInMillis() / 1000);
	}

	@Test
	public void testFromString_givenSpaceDateTimeSpace_mustReturnCalendarDateTime() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, DATE_TIME_FORMAT_PATTERN);
		Calendar result = calendarStringConverter.fromString(SPACE + DATE_TIME_FORMAT_RESULT + SPACE);
		assertThat(result.getTimeInMillis() / 1000).isEqualTo(CALENDAR_WITH_DATE_TIME.getTimeInMillis() / 1000);
	}

}
