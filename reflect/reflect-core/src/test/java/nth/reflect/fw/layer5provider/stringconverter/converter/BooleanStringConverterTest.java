package nth.reflect.fw.layer5provider.stringconverter.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterException;

public class BooleanStringConverterTest {

	private static final String NOT_A_BOOLEAN = "Not a boolean";
	private static final String EMPTY_STRING = "";
	private static final String WAHR = "Wahr";
	private static final String FALSCH = "Falsch";
	private static final String TRUE = "True";
	private static final String FALSE = "False";
	private static final String SPACE = " ";
	private BooleanStringConverter booleanStringConverter;
	private DependencyInjectionContainer container;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		container = application.createContainer();
		booleanStringConverter = new BooleanStringConverter(container, EMPTY_STRING);
	}

	@Test
	public void testToStringBoolean_givenNull_mustReturnEmpty() {
		String result = booleanStringConverter.toString(null);
		assertThat(result).isEqualTo(EMPTY_STRING);
	}

	@Test
	public void testToStringBoolean_givenPrimitiveFalse_mustReturnTrue() {
		String result = booleanStringConverter.toString(false);
		assertThat(result).isEqualTo(FALSE);
	}

	@Test
	public void testToStringBoolean_givenPrimitiveTrue_mustReturnTrue() {
		String result = booleanStringConverter.toString(true);
		assertThat(result).isEqualTo(TRUE);
	}

	@Test
	public void testToStringBoolean_givenTrue_mustReturnTrue() {
		String result = booleanStringConverter.toString(Boolean.TRUE);
		assertThat(result).isEqualTo(TRUE);
	}

	@Test
	public void testToStringBoolean_givenFalse_mustReturnFalse() {
		String result = booleanStringConverter.toString(Boolean.FALSE);
		assertThat(result).isEqualTo(FALSE);
	}

	@Test
	public void testToStringBoolean_givenPrimitiveFalseInGerman_mustReturnFalsh() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		String result = booleanStringConverter.toString(false);
		assertThat(result).isEqualTo(FALSCH);
	}

	@Test
	public void testToStringBoolean_givenPrimitiveTrueInGerman_mustReturnWahr() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		String result = booleanStringConverter.toString(true);
		assertThat(result).isEqualTo(WAHR);
	}

	@Test
	public void testToStringBoolean_givenFalseInGerman_mustReturnFalsch() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		String result = booleanStringConverter.toString(Boolean.FALSE);
		assertThat(result).isEqualTo(FALSCH);
	}

	@Test
	public void testToStringBoolean_givenTrueInGerman_mustReturnWahr() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		String result = booleanStringConverter.toString(Boolean.TRUE);
		assertThat(result).isEqualTo(WAHR);
	}

	@Test
	public void testFromStringString_givenNull_mustReturnNull() {
		Boolean result = booleanStringConverter.fromString(null);
		assertThat(result).isEqualTo(null);
	}

	@Test
	public void testFromStringString_givenEmptyString_mustReturnNull() {
		Boolean result = booleanStringConverter.fromString(EMPTY_STRING);
		assertThat(result).isEqualTo(null);
	}

	@Test
	public void testFromStringString_givenSpace_mustReturnNull() {
		Boolean result = booleanStringConverter.fromString(SPACE);
		assertThat(result).isEqualTo(null);
	}

	@Test
	public void testFromStringString_givenNoBoolean_mustThrowException() {
		assertThrows(StringConverterException.class, () -> {
			booleanStringConverter.fromString(NOT_A_BOOLEAN);
		});
	}

	@Test
	public void testFromStringString_givenNoBooleanInGerman_mustThrowException() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		assertThrows(StringConverterException.class, () -> {
			booleanStringConverter.fromString(NOT_A_BOOLEAN);
		});
	}

	@Test
	public void testFromStringString_givenSpaceLowercaseTrue_mustReturnPrimitiveTrue() {
		Boolean result = booleanStringConverter.fromString(SPACE + TRUE.toLowerCase());
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void testFromStringString_givenSpaceLowercaseFalseSpace_mustReturnPrimitiveFalse() {
		Boolean result = booleanStringConverter.fromString(SPACE + FALSE.toLowerCase() + SPACE);
		assertThat(result).isEqualTo(false);
	}

	@Test
	public void testFromStringString_givenSpaceTrue_mustReturnPrimitiveTrue() {
		Boolean result = booleanStringConverter.fromString(SPACE + TRUE);
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void testFromStringString_givenSpaceFalseSpace_mustReturnPrimitiveFalse() {
		Boolean result = booleanStringConverter.fromString(SPACE + FALSE + SPACE);
		assertThat(result).isEqualTo(false);
	}

	@Test
	public void testFromStringString_givenSpaceTrueInGerman_mustReturnPrimitiveTrue() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		Boolean result = booleanStringConverter.fromString(SPACE + TRUE);
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void testFromStringString_givenSpaceFalseSpaceInGerman_mustReturnPrimitiveFalse() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		Boolean result = booleanStringConverter.fromString(SPACE + FALSE + SPACE);
		assertThat(result).isEqualTo(false);
	}

	@Test
	public void testFromStringString_givenSpaceWahrInGerman_mustReturnPrimitiveTrue() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		Boolean result = booleanStringConverter.fromString(SPACE + WAHR);
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void testFromStringString_givenSpaceFalschSpaceInGerman_mustReturnPrimitiveFalse() {
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		Boolean result = booleanStringConverter.fromString(SPACE + FALSCH + SPACE);
		assertThat(result).isEqualTo(false);
	}

}
