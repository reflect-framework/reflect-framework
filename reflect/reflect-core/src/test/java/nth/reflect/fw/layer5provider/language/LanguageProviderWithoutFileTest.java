package nth.reflect.fw.layer5provider.language;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.stubs.Address;
import nth.reflect.fw.stubs.Country;
import nth.reflect.fw.stubs.GermanLanguageFile;

public class LanguageProviderWithoutFileTest {

	private static final String CLOSE_APPLICATION = "Close application";
	private static final String COM_ACME_LABEL1 = "com.ACME.label1";
	private LanguageProvider languageProvider;
	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit();
		DependencyInjectionContainer container = application.createContainer();
		languageProvider = container.get(LanguageProvider.class);
		reflectionProvider = container.get(ReflectionProvider.class);
	}

	@Test
	public void getKey() {
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(Address.class);
		String key = languageProvider.getKey(domainClassInfo);
		assertThat(key).isEqualTo(Address.class.getCanonicalName());

		key = languageProvider.getKey(Address.class.getCanonicalName());
		assertThat(key).isEqualTo(Address.class.getCanonicalName());

		key = languageProvider.getKey(Country.NETHERLANDS);
		assertThat(key).isEqualTo(Country.class.getCanonicalName() + ".NETHERLANDS");

		key = languageProvider.getKey(COM_ACME_LABEL1);
		assertThat(key).isEqualTo(COM_ACME_LABEL1);
	}

	@Test
	public void getDefaultValue() {
		String actual = languageProvider.getDefaultValueFromKey(COM_ACME_LABEL1);
		assertThat(actual).isEqualTo("Label1");

		actual = languageProvider.getDefaultValueFromKey("closeApplication");
		assertThat(actual).isEqualTo(CLOSE_APPLICATION);
	}

	@Test
	public void getTextForDefaultText() {
		String actual = languageProvider.getText(CLOSE_APPLICATION);
		assertThat(actual).isEqualTo(CLOSE_APPLICATION);
	}

	@Test
	public void getTextForKeyAndDefaultText() {
		String actual = languageProvider.getText(COM_ACME_LABEL1, CLOSE_APPLICATION);
		assertThat(actual).isEqualTo(CLOSE_APPLICATION);
	}

	@Test
	public void getTextForLocaleAndKeyAndDefaultText() {
		String actual = languageProvider.getText(Locale.GERMAN, COM_ACME_LABEL1, CLOSE_APPLICATION);
		String expected = GermanLanguageFile.get(COM_ACME_LABEL1);
		assertThat(actual).isEqualTo(expected);
	}

}
