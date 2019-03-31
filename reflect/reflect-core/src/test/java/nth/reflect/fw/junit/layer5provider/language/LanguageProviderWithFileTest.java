package nth.reflect.fw.junit.layer5provider.language;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.junit.stubs.Address;
import nth.reflect.fw.junit.stubs.Country;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

public class LanguageProviderWithFileTest {

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
		assertEquals(Address.class.getCanonicalName(), key);

		key = languageProvider.getKey(Address.class.getCanonicalName());
		assertEquals(Address.class.getCanonicalName(), key);

		key = languageProvider.getKey(Country.NETHERLANDS);
		assertEquals(Country.class.getCanonicalName() + ".NETHERLANDS", key);

		key = languageProvider.getKey(COM_ACME_LABEL1);
		assertEquals(COM_ACME_LABEL1, key);
	}

	@Test
	public void getDefaultValue() {
		String defaultValue = languageProvider.getDefaultValueFromKey(COM_ACME_LABEL1);
		assertEquals("Label1", defaultValue);

		defaultValue = languageProvider.getDefaultValueFromKey("closeApplication");
		assertEquals(CLOSE_APPLICATION, defaultValue);
	}

	@Test
	public void getTextForDefaultText() {
		String defaultValue = languageProvider.getText(CLOSE_APPLICATION);
		assertEquals(CLOSE_APPLICATION, defaultValue);
	}

	@Test
	public void getTextForKeyAndDefaultText() {
		String defaultValue = languageProvider.getText(COM_ACME_LABEL1, CLOSE_APPLICATION);
		assertEquals(CLOSE_APPLICATION, defaultValue);
	}

	@Test
	public void getTextForLocaleAndKeyAndDefaultText() {
		String defaultValue = languageProvider.getText(Locale.FRENCH, COM_ACME_LABEL1, CLOSE_APPLICATION);
		assertEquals("From France file", defaultValue);
	}

}
