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

	@Test
	public void testToString_givenNullAndNullFormat_mustReturnEmptyString() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, null);
		String result = calendarStringConverter.toString(null);
		assertThat(result).isEqualTo(EMPTY_STRING);
	}

	@Test
	public void testToString_givenCalendarAndNullFormat_mustReturnCalendar() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null, null);
		String result = calendarStringConverter.toString(DateTimeTestUtil.CALENDAR_WITH_DATE_TIME);
		SimpleDateFormat format = new SimpleDateFormat();
		String expected = format.format(DateTimeTestUtil.CALENDAR_WITH_DATE_TIME.getTime());
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenCalendarAndDateFormat_mustReturnDateString() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null,
				DateTimeTestUtil.DATE_FORMAT_PATTERN);
		String result = calendarStringConverter.toString(DateTimeTestUtil.CALENDAR_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenCalendarAndTimeFormat_mustReturnTimeString() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null,
				DateTimeTestUtil.TIME_FORMAT_PATTERN);
		String result = calendarStringConverter.toString(DateTimeTestUtil.CALENDAR_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.TIME_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenCalendarAndDateTimeFormat_mustReturnDateTimeString() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null,
				DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN);
		String result = calendarStringConverter.toString(DateTimeTestUtil.CALENDAR_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_TIME_FORMAT_RESULT);
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
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null,
				DateTimeTestUtil.DATE_FORMAT_PATTERN);
		Calendar result = calendarStringConverter.fromString(SPACE + DateTimeTestUtil.DATE_FORMAT_RESULT);
		assertThat(result.getTimeInMillis() / 1000)
				.isEqualTo(DateTimeTestUtil.CALENDAR_WITH_DATE.getTimeInMillis() / 1000);
	}

	@Test
	public void testFromString_givenTimeSpace_mustReturnCalendarTime() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null,
				DateTimeTestUtil.TIME_FORMAT_PATTERN);
		Calendar result = calendarStringConverter.fromString(DateTimeTestUtil.TIME_FORMAT_RESULT + SPACE);
		assertThat(result.getTimeInMillis() / 1000)
				.isEqualTo(DateTimeTestUtil.CALENDAR_WITH_TIME.getTimeInMillis() / 1000);
	}

	@Test
	public void testFromString_givenSpaceDateTimeSpace_mustReturnCalendarDateTime() {
		CalendarStringConverter calendarStringConverter = new CalendarStringConverter(null,
				DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN);
		Calendar result = calendarStringConverter.fromString(SPACE + DateTimeTestUtil.DATE_TIME_FORMAT_RESULT + SPACE);
		assertThat(result.getTimeInMillis() / 1000)
				.isEqualTo(DateTimeTestUtil.CALENDAR_WITH_DATE_TIME.getTimeInMillis() / 1000);
	}

}
