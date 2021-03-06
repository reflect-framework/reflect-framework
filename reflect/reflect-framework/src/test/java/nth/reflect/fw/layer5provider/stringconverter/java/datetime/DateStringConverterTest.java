package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class DateStringConverterTest extends StringConverterTest {

	@Test
	public void testToString_givenNullAndNullFormat_mustReturnEmptyString() {
		DateStringConverter dateStringConverter = new DateStringConverter(NO_FORMAT);
		String result = dateStringConverter.toString(null);
		assertThat(result).isEqualTo(EMPTY);
	}

	@Test
	public void testToString_givenDateWithNullFormat_mustReturnDateWith() {
		DateStringConverter dateStringConverter = new DateStringConverter(NO_FORMAT);
		String result = dateStringConverter.toString(DateTimeTestUtil.DATE_WITH_DATE_TIME);
		SimpleDateFormat format = new SimpleDateFormat();
		String expected = format.format(DateTimeTestUtil.DATE_WITH_DATE_TIME.getTime());
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenDateWithDateFormat_mustReturnDateString() {
		DateStringConverter dateStringConverter = new DateStringConverter(DateTimeTestUtil.DATE_FORMAT_PATTERN);
		String result = dateStringConverter.toString(DateTimeTestUtil.DATE_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenDateWithTimeFormat_mustReturnTimeString() {
		DateStringConverter dateStringConverter = new DateStringConverter(DateTimeTestUtil.TIME_FORMAT_PATTERN);
		String result = dateStringConverter.toString(DateTimeTestUtil.DATE_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.TIME_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenDateWithDateTimeFormat_mustReturnDateTimeString() {
		DateStringConverter dateStringConverter = new DateStringConverter(DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN);
		String result = dateStringConverter.toString(DateTimeTestUtil.DATE_WITH_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_TIME_FORMAT_RESULT);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		DateStringConverter dateStringConverter = new DateStringConverter(NO_FORMAT);
		Date result = dateStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptyString_mustReturnNull() {
		DateStringConverter dateStringConverter = new DateStringConverter(NO_FORMAT);
		Date result = dateStringConverter.fromString(EMPTY);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenBogusValue_mustThrowException() {
		DateStringConverter dateStringConverter = new DateStringConverter(NO_FORMAT);
		assertThrows(StringConverterException.class, () -> {
			dateStringConverter.fromString(BOGUS);
		});
	}

	@Test
	public void testFromString_givenSpaceDate_mustReturnDateWithDate() {
		DateStringConverter dateStringConverter = new DateStringConverter(DateTimeTestUtil.DATE_FORMAT_PATTERN);
		Date result = dateStringConverter.fromString(SPACE + DateTimeTestUtil.DATE_FORMAT_RESULT);
		assertThat(result).isEqualToIgnoringMillis(DateTimeTestUtil.DATE_WITH_DATE);
	}

	@Test
	public void testFromString_givenTimeSpace_mustReturnDateWithTime() {
		DateStringConverter dateStringConverter = new DateStringConverter(DateTimeTestUtil.TIME_FORMAT_PATTERN);
		Date result = dateStringConverter.fromString(DateTimeTestUtil.TIME_FORMAT_RESULT + SPACE);
		assertThat(result).isEqualToIgnoringMillis(DateTimeTestUtil.DATE_WITH_TIME);
	}

	@Test
	public void testFromString_givenSpaceDateTimeSpace_mustReturnDateWithDateTime() {
		DateStringConverter dateStringConverter = new DateStringConverter(DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN);
		Date result = dateStringConverter.fromString(SPACE + DateTimeTestUtil.DATE_TIME_FORMAT_RESULT + SPACE);
		assertThat(result).isEqualToIgnoringMillis(DateTimeTestUtil.DATE_WITH_DATE_TIME);
	}

}
