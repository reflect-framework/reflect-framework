package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class DateStringConverterTest {

	private static final String EMPTY_STRING = "";
	private static final String SPACE = " ";
	private static final String BOGUS_STRING = "Bogus";

	@Test
	public void testToString_givenNullAndNullFormat_mustReturnEmptyString() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, null);
		String result = dateStringConverter.toString(null);
		assertThat(result).isEqualTo(EMPTY_STRING);
	}

	@Test
	public void testToString_givenDateWithNullFormat_mustReturnDateWith() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, null);
		String result = dateStringConverter.toString(DateTimeTestUtil.DATE_WITH_DATE_TIME);
		SimpleDateFormat format = new SimpleDateFormat();
		String expected = format.format(DateTimeTestUtil.DATE_WITH_DATE_TIME.getTime());
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenDateWithDateFormat_mustReturnDateString() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, DateTimeTestUtil.DATE_FORMAT_PATTERN);
		String result = dateStringConverter.toString(DateTimeTestUtil.DATE_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenDateWithTimeFormat_mustReturnTimeString() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, DateTimeTestUtil.TIME_FORMAT_PATTERN);
		String result = dateStringConverter.toString(DateTimeTestUtil.DATE_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.TIME_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenDateWithDateTimeFormat_mustReturnDateTimeString() {
		DateStringConverter dateStringConverter = new DateStringConverter(null,
				DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN);
		String result = dateStringConverter.toString(DateTimeTestUtil.DATE_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_TIME_FORMAT_RESULT);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, null);
		Date result = dateStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptyString_mustReturnNull() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, null);
		Date result = dateStringConverter.fromString(EMPTY_STRING);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenBogusValue_mustThrowException() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, null);
		assertThrows(StringConverterException.class, () -> {
			dateStringConverter.fromString(BOGUS_STRING);
		});
	}

	@Test
	public void testFromString_givenSpaceDate_mustReturnDateWithDate() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, DateTimeTestUtil.DATE_FORMAT_PATTERN);
		Date result = dateStringConverter.fromString(SPACE + DateTimeTestUtil.DATE_FORMAT_RESULT);
		assertThat(result).isEqualToIgnoringMillis(DateTimeTestUtil.DATE_WITH_DATE);
	}

	@Test
	public void testFromString_givenTimeSpace_mustReturnDateWithTime() {
		DateStringConverter dateStringConverter = new DateStringConverter(null, DateTimeTestUtil.TIME_FORMAT_PATTERN);
		Date result = dateStringConverter.fromString(DateTimeTestUtil.TIME_FORMAT_RESULT + SPACE);
		assertThat(result).isEqualToIgnoringMillis(DateTimeTestUtil.DATE_WITH_TIME);
	}

	@Test
	public void testFromString_givenSpaceDateTimeSpace_mustReturnDateWithDateTime() {
		DateStringConverter dateStringConverter = new DateStringConverter(null,
				DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN);
		Date result = dateStringConverter.fromString(SPACE + DateTimeTestUtil.DATE_TIME_FORMAT_RESULT + SPACE);
		assertThat(result).isEqualToIgnoringMillis(DateTimeTestUtil.DATE_WITH_DATE_TIME);
	}

}
