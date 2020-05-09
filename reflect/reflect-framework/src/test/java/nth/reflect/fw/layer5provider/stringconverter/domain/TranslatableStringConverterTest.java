package nth.reflect.fw.layer5provider.stringconverter.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.GermanLanguageFile;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterTest;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class TranslatableStringConverterTest extends StringConverterTest {

	private static final String DEFAULT_ENGLISH = "Default English";
	private TranslatableStringConverter stringConverter;

	@Before
	public void setUp() throws Exception {
		StringConverterFactoryInfo info = createInfo(AllFeatureDomainObject.GET_MY_TRANSLATABLE_STRING);
		stringConverter = new TranslatableStringConverter(info);
	}

	@Test
	public void testToString_givenNull_mustReturnEmptyString() {
		String result = stringConverter.toString(null);
		assertThat(result).isEmpty();
	}

	@Test
	public void testToString_giveTranslatableStringAndEnglish_mustReturnDefaultEnglishTranslation() {
		String key = DomainObject.class.getCanonicalName() + "." + AllFeatureDomainObject.GET_MY_TRANSLATABLE_STRING;
		TranslatableString translatableString = new TranslatableString(key, DEFAULT_ENGLISH);
		String result = stringConverter.toString(translatableString);
		assertThat(result).isEqualTo(DEFAULT_ENGLISH);
	}

	@Test
	public void testToString_giveTranslatableStringAndGerman_mustReturnGermanTranslation() {
		StringConverterFactoryInfo info = createInfo(AllFeatureDomainObject.GET_MY_TRANSLATABLE_STRING);
		DefaultLanguageProvider languageProvider = info.getContainer().get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		String key = AllFeatureDomainObject.class.getCanonicalName() + "."
				+ AllFeatureDomainObject.GET_MY_TRANSLATABLE_STRING;
		TranslatableString translatableString = new TranslatableString(key, DEFAULT_ENGLISH);
		String result = stringConverter.toString(translatableString);
		assertThat(result).isEqualTo(GermanLanguageFile.get(key));
	}

	@Test
	public void testFromStringString() {
		assertThrows(MethodNotSupportedException.class, () -> {
			stringConverter.fromString(null);
		});
	}

}
