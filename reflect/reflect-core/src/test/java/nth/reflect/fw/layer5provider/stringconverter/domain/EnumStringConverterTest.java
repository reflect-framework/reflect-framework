package nth.reflect.fw.layer5provider.stringconverter.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.exception.MethodNotSupportedException;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;

public class EnumStringConverterTest {

	private EnumStringConverter enumStringConverter;
	private DependencyInjectionContainer container;

	@Before
	public void setUp() throws Exception {
		container = new ReflectApplicationForJUnit().createContainer();
		enumStringConverter = new EnumStringConverter(container, null);
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
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
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
