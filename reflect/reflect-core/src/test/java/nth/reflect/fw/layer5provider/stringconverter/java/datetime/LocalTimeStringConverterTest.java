package nth.reflect.fw.layer5provider.stringconverter.java.datetime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterException;

public class LocalTimeStringConverterTest extends StringConverterTest {

	@Test
	public void testToString_givenNullWithNullFormat_mustReturnEmptyString() {
		LocalTimeStringConverter localLocalTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME));
		String result = localLocalTimeStringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenLocalTimeWithNullFormat_mustReturnTimeString() {
		LocalTimeStringConverter localLocalTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME));
		String result = localLocalTimeStringConverter.toString(DateTimeTestUtil.LOCAL_TIME);
		DateTimeFormatter format = DateTimeFormatter.ISO_TIME;
		String expected = format.format(DateTimeTestUtil.LOCAL_TIME);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenLocalTimeWithTimeFormat_mustReturnTimeString() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME, DateTimeTestUtil.TIME_FORMAT_PATTERN));
		String result = localTimeStringConverter.toString(DateTimeTestUtil.LOCAL_TIME);
		assertThat(result).isEqualTo(DateTimeTestUtil.TIME_FORMAT_RESULT);
	}

	@Test
	public void testToString_givenLocalTimeWithDateFormat_mustThrowException() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME, DateTimeTestUtil.DATE_FORMAT_PATTERN));
		assertThrows(StringConverterException.class, () -> {
			localTimeStringConverter.toString(DateTimeTestUtil.LOCAL_TIME);
		});
	}

	@Test
	public void testFromString_givenNull_mustReturnNull() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME));
		LocalTime result = localTimeStringConverter.fromString(null);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenEmptyString_mustReturnNull() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME));
		LocalTime result = localTimeStringConverter.fromString(EMPTY);
		assertThat(result).isNull();
	}

	@Test
	public void testFromString_givenBogusValue_mustThrowException() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME));
		assertThrows(StringConverterException.class, () -> {
			localTimeStringConverter.fromString(BOGUS);
		});
	}

	@Test
	public void testFromString_givenDateSpace_mustThrowException() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME, DateTimeTestUtil.TIME_FORMAT_PATTERN));
		assertThrows(StringConverterException.class, () -> {
			localTimeStringConverter.fromString(DateTimeTestUtil.DATE_FORMAT_RESULT + SPACE);
		});
	}

	@Test
	public void testFromString_givenSpaceDateSpace_mustReturnLocalDate() {
		LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter(
				createInfo(AllFeatureDomainObject.GET_MY_LOCAL_TIME, DateTimeTestUtil.DATE_TIME_FORMAT_PATTERN));
		LocalTime result = localTimeStringConverter
				.fromString(SPACE + DateTimeTestUtil.DATE_TIME_FORMAT_RESULT + SPACE);
		assertThat(result).isEqualTo(DateTimeTestUtil.LOCAL_TIME);
	}

}
