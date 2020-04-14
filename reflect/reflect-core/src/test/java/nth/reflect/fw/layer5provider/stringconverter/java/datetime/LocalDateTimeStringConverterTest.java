package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class LocalDateTimeStringConverterTest extends StringConverterTest {

	@Test
	public void testToString_givenNullWithNullFormat_mustReturnEmptyString() {
		LocalDateTimeStringConverter localLocalDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME));
		String result = localLocalDateStringConverter.toString(null);
		assertThat(result).isEqualTo(EMPTY);
	}

	@Test
	public void testToString_givenLocalDateTimeWithNullFormat_mustReturnDateTimeString() {
		LocalDateTimeStringConverter localLocalDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME));
		String result = localLocalDateStringConverter.toString(DateTimeTestUtil.LOCAL_DATE_TIME);
		DateTimeFormatter format = DateTimeFormatter.ISO_DATE;
		String expected = format.format(DateTimeTestUtil.LOCAL_DATE);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenLocalDateTimeWithDateFormat_mustReturnDateString() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME, DateTimeTestUtil.DATE_FORMAT_PATTERN));
		String result = localDateStringConverter.toString(DateTimeTestUtil.LOCAL_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenLocalDateWithTimeFormat_mustReturnString() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME, DateTimeTestUtil.TIME_FORMAT_PATTERN));
		String result = localDateStringConverter.toString(DateTimeTestUtil.LOCAL_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.TIME_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenLocalDateWithDateTimeFormat_mustReturnString() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME, DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN));
		String result = localDateStringConverter.toString(DateTimeTestUtil.LOCAL_DATE_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_TIME_FORMAT_RESULT);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME));
		LocalDateTime result = localDateStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptyString_mustReturnNull() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME));
		LocalDateTime result = localDateStringConverter.fromString(EMPTY);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenBogusValue_mustThrowException() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME));
		assertThrows(StringConverterException.class, () -> {
			localDateStringConverter.fromString(BOGUS);
		});
	}

	@Test
	public void testFromString_givenSpaceDate_mustThrowException() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME, DateTimeTestUtil.DATE_FORMAT_PATTERN));
		assertThrows(StringConverterException.class, () -> {
			localDateStringConverter.fromString(SPACE + DateTimeTestUtil.DATE_FORMAT_RESULT);
		});
	}

	@Test
	public void testFromString_givenTimeSpace_mustThrowException() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME, DateTimeTestUtil.TIME_FORMAT_PATTERN));
		assertThrows(StringConverterException.class, () -> {
			localDateStringConverter.fromString(DateTimeTestUtil.TIME_FORMAT_RESULT + SPACE);
		});
	}

	@Test
	public void testFromString_givenSpaceDateTimeSpace_mustReturnLocalDateTime() {
		LocalDateTimeStringConverter localDateStringConverter = new LocalDateTimeStringConverter(
				createInfo(FullFeatureDomainObject.GET_MY_LOCAL_DATE_TIME, DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN));
		LocalDateTime result = localDateStringConverter
				.fromString(SPACE + DateTimeTestUtil.DATE_TIME_FORMAT_RESULT + SPACE);
		assertThat(result).isEqualTo(DateTimeTestUtil.LOCAL_DATE_TIME);
	}
}
