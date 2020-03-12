package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class LocalDateStringConverterTest extends StringConverterTest {

	@Test
	public void testToString_givenNullWithNullFormat_mustReturnEmptyString() {
		LocalDateStringConverter localLocalDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE));
		String result = localLocalDateStringConverter.toString(null);
		assertThat(result).isEqualTo(EMPTY);
	}

	@Test
	public void testToString_givenLocalDateWithNullFormat_mustReturnDateString() {
		LocalDateStringConverter localLocalDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE));
		String result = localLocalDateStringConverter.toString(DateTimeTestUtil.LOCAL_DATE);
		DateTimeFormatter format = DateTimeFormatter.ISO_DATE;
		String expected = format.format(DateTimeTestUtil.LOCAL_DATE);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenLocalDateWithDateFormat_mustReturnDateString() {
		LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE, DateTimeTestUtil.DATE_FORMAT_PATTERN));
		String result = localDateStringConverter.toString(DateTimeTestUtil.LOCAL_DATE);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenLocalDateWithTimeFormat_mustThrowException() {
		LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE, DateTimeTestUtil.TIME_FORMAT_PATTERN));
		assertThrows(StringConverterException.class, () -> {
			localDateStringConverter.toString(DateTimeTestUtil.LOCAL_DATE);
		});
	}

	@Test
	public void testToString_givenLocalDateWithDateFormat_mustReturnString() {
		LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE, DateTimeTestUtil.DATE_FORMAT_PATTERN));
		String result = localDateStringConverter.toString(DateTimeTestUtil.LOCAL_DATE);
		assertThat(result).isEqualTo(DateTimeTestUtil.DATE_FORMAT_RESULT);
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE));
		LocalDate result = localDateStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptyString_mustReturnNull() {
		LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE));
		LocalDate result = localDateStringConverter.fromString(EMPTY);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenBogusValue_mustThrowException() {
		LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE));
		assertThrows(StringConverterException.class, () -> {
			localDateStringConverter.fromString(BOGUS);
		});
	}

	@Test
	public void testFromString_givenTimeSpace_mustThrowException() {
		LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE, DateTimeTestUtil.TIME_FORMAT_PATTERN));
		assertThrows(StringConverterException.class, () -> {
			localDateStringConverter.fromString(DateTimeTestUtil.TIME_FORMAT_RESULT + SPACE);
		});
	}

	@Test
	public void testFromString_givenSpaceDateSpace_mustReturnLocalDate() {
		LocalDateStringConverter localDateStringConverter = new LocalDateStringConverter(
				createInfo(DomainObject.GET_MY_LOCAL_DATE, DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN));
		LocalDate result = localDateStringConverter
				.fromString(SPACE + DateTimeTestUtil.DATE_TIME_FORMAT_RESULT + SPACE);
		assertThat(result).isEqualTo(DateTimeTestUtil.LOCAL_DATE);
	}
}
