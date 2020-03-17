package nth.reflect.fw.layer5provider.stringconverter.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;

public class EnumStringConverterTest extends StringConverterTest {

	private EnumStringConverter enumStringConverter;

	@Before
	public void setUp() {
		enumStringConverter = new EnumStringConverter(createInfo(DomainObject.GET_MY_TEXT));
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = enumStringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenEnumWithDefaultEnglish_mustReturnSecond() {
		String result = enumStringConverter.toString(DomainObject.MyEnum.second);
		assertThat(result).isEqualTo("Second");
	}

	@Test
	public void testToString_givenEnumWithGerman_mustReturnString() {
		DefaultLanguageProvider languageProvider = getContainer().get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		String result = enumStringConverter.toString(DomainObject.MyEnum.second);
		assertThat(result).isEqualTo("Zweite");
	}

	@Test
	public void testFromStringString() {
		assertThrows(MethodNotSupportedException.class, () -> {
			enumStringConverter.fromString(null);
		});
	}

}
