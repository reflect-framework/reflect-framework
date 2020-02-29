package nth.reflect.fw.layer5provider.stringconverter.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

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

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().createContainer();
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
		assertThat(result).isEqualTo(minValue.toString());
	}

	@Test
	public void testToString_givenMaxNumber_mustReturnMaxNumber() {
		Number maxValue = getMaxValue();
		StringConverter stringConverter = createStringConverter(container, null);
		String result = stringConverter.toString(maxValue);
		assertThat(result).isEqualTo(maxValue.toString());
	}

	@Test
	public void testToString_givenNumberWithPrecedingZeroFormat_mustReturnNumberWithPrecedingZero() {
		Number maxValue = getMaxValue();
		int numberOfDecimals = maxValue.toString().length();
		String formatPattern = StringUtil.repeat(ZERO, numberOfDecimals + 1);
		StringConverter stringConverter = createStringConverter(container, formatPattern);
		String result = stringConverter.toString(maxValue);
		assertThat(result).isEqualTo(ZERO + maxValue.toString());
	}

	@Test
	public void testToString_givenNumberWithEuroSuffixFormat_mustReturnNumberWithEuroSuffix() {
		Number maxValue = getMaxValue();
		String formatPattern = ZERO + SPACE + EURO;
		StringConverter stringConverter = createStringConverter(container, formatPattern);
		String result = stringConverter.toString(maxValue);
		assertThat(result).isEqualTo(maxValue.toString() + SPACE + EURO);
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
		Long maxValuePlusOne = getMaxValue().longValue() + 1;
		StringConverter stringConverter = createStringConverter(container, null);
		String stringValue = maxValuePlusOne.toString();
		assertThrows(NumberExceedsMaxException.class, () -> {
			stringConverter.fromString(stringValue);
		});
	}

	@Test
	public void testFromString_givenMinNumberPluseOne_mustThrowNumberExceedsMinException() {
		Long minValueMinusOne = getMinValue().longValue() - 1;
		StringConverter stringConverter = createStringConverter(container, null);
		String stringValue = minValueMinusOne.toString();
		assertThrows(NumberExceedsMinException.class, () -> {
			stringConverter.fromString(stringValue);
		});
	}

	@Test
	public void testFromString_givenNoneNumber_mustThrowNumberExceedsMinException() {
		StringConverter stringConverter = createStringConverter(container, null);
		String stringValue = "INVALID NUMBER";
		assertThrows(StringConverterException.class, () -> {
			stringConverter.fromString(stringValue);
		});
	}

}
