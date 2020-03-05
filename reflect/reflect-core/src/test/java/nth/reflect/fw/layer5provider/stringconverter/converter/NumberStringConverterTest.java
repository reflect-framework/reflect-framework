package nth.reflect.fw.layer5provider.stringconverter.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.NumberExceedsMaxException;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.NumberExceedsMinException;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterException;

public abstract class NumberStringConverterTest {

	private static final String SPACE = " ";
	private static final String EURO = "Euro";
	private static final String ZERO = "0";
	private DependencyInjectionContainer container;
	private Format expectedDefaultFormat;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().createContainer();
		expectedDefaultFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
	}

	protected abstract StringConverter createStringConverter(DependencyInjectionContainer container2,
			String formatPattern);

	protected abstract Number getMinValue();

	protected abstract Number getMaxValue();

	@Test
	public void testToString_givenMinNumber_mustReturnMinNumber() {
		Number minValue = getMinValue();
		StringConverter stringConverter = createStringConverter(container, null);
		String result = stringConverter.toString(minValue);
		String expected = expectedDefaultFormat.format(minValue);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenMaxNumber_mustReturnMaxNumber() {
		Number maxValue = getMaxValue();
		StringConverter stringConverter = createStringConverter(container, null);
		String result = stringConverter.toString(maxValue);
		String expected = expectedDefaultFormat.format(maxValue);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenNumberWithPrecedingZeroFormat_mustReturnNumberWithPrecedingZero() {
		Number maxValue = getMaxValue();
		int numberOfDecimals = maxValue.toString().length();
		String formatPattern = StringUtil.repeat(ZERO, numberOfDecimals + 1);
		StringConverter stringConverter = createStringConverter(container, formatPattern);
		String result = stringConverter.toString(maxValue);
		String expected = new DecimalFormat(formatPattern).format(maxValue);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testToString_givenNumberWithEuroSuffixFormat_mustReturnNumberWithEuroSuffix() {
		Number maxValue = getMaxValue();
		String formatPattern = ZERO + SPACE + EURO;
		StringConverter stringConverter = createStringConverter(container, formatPattern);
		String result = stringConverter.toString(maxValue);
		String expected = new DecimalFormat(formatPattern).format(maxValue);
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void testFromString_givenMinNumber_mustReturnMinNumber() {
		Number minValue = getMinValue();
		StringConverter stringConverter = createStringConverter(container, null);
		Number result = (Number) stringConverter.fromString(minValue.toString());
		assertThat(result).isEqualTo(minValue);
	}

	@Test
	public void testFromString_givenMaxNumber_mustReturnMaxNumber() {
		Number maxValue = getMaxValue();
		StringConverter stringConverter = createStringConverter(container, null);
		Number result = (Number) stringConverter.fromString(maxValue.toString());
		assertThat(result).isEqualTo(maxValue);
	}

	@Test
	public void testFromString_givenNumberWithPrecedingZeroFormat_mustReturnNumberWithPrecedingZero() {
		Number maxValue = getMaxValue();
		int numberOfDecimals = maxValue.toString().length();
		String formatPattern = StringUtil.repeat(ZERO, numberOfDecimals + 1);
		StringConverter stringConverter = createStringConverter(container, formatPattern);
		String stringValue = ZERO + maxValue.toString();
		Number result = (Number) stringConverter.fromString(stringValue);
		assertThat(result).isEqualTo(maxValue);
	}

	@Test
	public void testFromString_givenNumberWithEuroSuffixFormat_mustReturnNumberWithEuroSuffix() {
		Number maxValue = getMaxValue();
		String formatPattern = ZERO + SPACE + EURO;
		StringConverter stringConverter = createStringConverter(container, formatPattern);
		String stringValue = maxValue.toString() + SPACE + EURO;
		Number result = (Number) stringConverter.fromString(stringValue);
		assertThat(result).isEqualTo(maxValue);
	}

	@Test
	public void testFromString_givenMaxNumberPluseOne_mustThrowNumberExceedsMaxException() {
		Number maxValue = getMaxValue();
		Long maxValuePlusOne = maxValue.longValue() + 1;
		StringConverter stringConverter = createStringConverter(container, null);
		String stringValue = maxValuePlusOne.toString();
		assertThrows(NumberExceedsMaxException.class, () -> {
			stringConverter.fromString(stringValue);
		});
	}

	@Test
	public void testFromString_givenMinNumberMinuseOne_mustThrowNumberExceedsMinException() {
		Number minValue = getMinValue();
		Long minValueMinusOne = minValue.longValue() - 1;
		StringConverter stringConverter = createStringConverter(container, null);
		String stringValue = minValueMinusOne.toString();
		assertThrows(NumberExceedsMinException.class, () -> {
			stringConverter.fromString(stringValue);
		});
	}

	@Test
	public void testFromString_givenNoneNumber_mustThrowStringConverterException() {
		StringConverter stringConverter = createStringConverter(container, null);
		String stringValue = "INVALID NUMBER";
		assertThrows(StringConverterException.class, () -> {
			stringConverter.fromString(stringValue);
		});
	}

}
