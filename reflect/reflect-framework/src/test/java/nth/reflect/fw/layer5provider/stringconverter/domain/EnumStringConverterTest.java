package nth.reflect.fw.layer5provider.stringconverter.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject.MyEnum;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.GermanLanguageFile;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;

public class EnumStringConverterTest extends StringConverterTest {

	private EnumStringConverter enumStringConverter;

	@Before
	public void setUp() {
		enumStringConverter = new EnumStringConverter(getLanguageProvider());
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = enumStringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_givenEnumWithDefaultEnglish_mustReturnSecond() {
		String result = enumStringConverter.toString(AllFeatureDomainObject.MyEnum.second);
		assertThat(result).isEqualTo("Second");
	}

	@Test
	public void testToString_givenEnumWithGerman_mustReturnString() {
		DefaultLanguageProvider languageProvider = getContainer().get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		MyEnum enumValue = AllFeatureDomainObject.MyEnum.second;
		String actual = enumStringConverter.toString(enumValue);
		String key = languageProvider.getKey(enumValue);
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testFromStringString() {
		assertThrows(MethodNotSupportedException.class, () -> {
			enumStringConverter.fromString(null);
		});
	}

}
