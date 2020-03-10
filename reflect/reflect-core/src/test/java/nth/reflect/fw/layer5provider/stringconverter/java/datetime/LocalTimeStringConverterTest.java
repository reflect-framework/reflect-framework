package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class LocalTimeStringConverterTest {

	private static final String EMPTY_STRING = "";
	private static final String SPACE = " ";
	private static final String BOGUS_STRING = "Bogus";

	@Test
	public void testToString_givenNullWithNullFormat_mustReturnEmptyString() {
		LocalTimeStringConverter localLocalTimeStringConverter = new LocalTimeStringConverter(null, null);
		String result = localLocalTimeStringConverter.toString(null);
		assertThat(result).isEqualTo(EMPTY_STRING);
	}

	@Test
	public void testToString_givenLocalTimeWithNullFormat_mustReturnTimeString() {
		LocalTimeStringConverter localLocalTimeStringConverter = new LocalTimeStringConverter(null, null);
		String result = localLocalTimeStringConverter.toString(DateTimeTestUtil.LOCAL_TIME);
		DateTimeFormatter format = DateTimeFormatter.ISO_TIME;
		String expected = format.format(DateTimeTestUtil.LOCAL_TIME);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenLocalTimeWithTimeFormat_mustReturnTimeString() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(null,
				DateTimeTestUtil.TIME_FORMAT_PATTERN);
		String result = localTimeStringConverter.toString(DateTimeTestUtil.LOCAL_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.TIME_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenLocalTimeWithDateFormat_mustThrowException() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(null,
				DateTimeTestUtil.DATE_FORMAT_PATTERN);
		assertThrows(StringConverterException.class, () -> {
			localTimeStringConverter.toString(DateTimeTestUtil.LOCAL_TIME);
		});
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(null, null);
		LocalTime result = localTimeStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptyString_mustReturnNull() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(null, null);
		LocalTime result = localTimeStringConverter.fromString(EMPTY_STRING);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenBogusValue_mustThrowException() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(null, null);
		assertThrows(StringConverterException.class, () -> {
			localTimeStringConverter.fromString(BOGUS_STRING);
		});
	}

	@Test
	public void testFromString_givenDateSpace_mustThrowException() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(null,
				DateTimeTestUtil.TIME_FORMAT_PATTERN);
		assertThrows(StringConverterException.class, () -> {
			localTimeStringConverter.fromString(DateTimeTestUtil.DATE_FORMAT_RESULT + SPACE);
		});
	}

	@Test
	public void testFromString_givenSpaceDateSpace_mustReturnLocalDate() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(null,
				DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN);
		LocalTime result = localTimeStringConverter
				.fromString(SPACE + DateTimeTestUtil.DATE_TIME_FORMAT_RESULT + SPACE);
		assertThat(result).isEqualTo(DateTimeTestUtil.LOCAL_TIME);
	}

}
