package nth.introspect.junit.layer5provider.language;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.junit.IntrospectApplicationForJUnit;
import nth.introspect.junit.layer5provider.validation.Address;
import nth.introspect.junit.layer5provider.validation.Country;
import nth.introspect.layer5provider.language.IllegalKeyFormat;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;

import org.junit.Before;
import org.junit.Test;

public class LanguageProviderWithoutFileTest {

	private static final String CLOSE_APPLICATION = "Close application";
	private static final String COM_ACME_LABEL1 = "com.ACME.label1";
	private LanguageProvider languageProvider;
	private ReflectionProvider reflectionProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() ;
		DependencyInjectionContainer container = application.createContainer();
		languageProvider= container.get(LanguageProvider.class);
		reflectionProvider=container.get(ReflectionProvider.class);
	}

	@Test
	public void getKey() {
		ClassInfo classInfo = reflectionProvider.getClassInfo(Address.class);
		String key = languageProvider.getKey(classInfo);
		assertEquals(Address.class.getCanonicalName(), key);

		key = languageProvider.getKey(Address.class.getCanonicalName());
		assertEquals(Address.class.getCanonicalName(), key);


		key = languageProvider.getKey(Country.NETHERLANDS);
		assertEquals(Country.class.getCanonicalName()+".NETHERLANDS", key);
		
		key = languageProvider.getKey(COM_ACME_LABEL1);
		assertEquals(COM_ACME_LABEL1, key);
	}

	@Test (expected=IllegalKeyFormat.class)
	public void getKeyInvalid() {
		languageProvider.getKey("bogus key");
	}
	
	@Test
	public void getDefaultValue() {
		String defaultValue=languageProvider.getDefaultValue(COM_ACME_LABEL1);
		assertEquals("Label1", defaultValue);
		
		defaultValue=languageProvider.getDefaultValue("closeApplication");
		assertEquals(CLOSE_APPLICATION, defaultValue);
	}

	@Test
	public void getTextForDefaultText() {
		String defaultValue=languageProvider.getText(CLOSE_APPLICATION);
		assertEquals(CLOSE_APPLICATION, defaultValue);
	}

	@Test
	public void getTextForKeyAndDefaultText() {
		String defaultValue=languageProvider.getText(COM_ACME_LABEL1,CLOSE_APPLICATION);
		assertEquals(CLOSE_APPLICATION, defaultValue);
	}

	@Test
	public void getTextForLocaleAndKeyAndDefaultText() {
		String defaultValue=languageProvider.getText(Locale.GERMAN, COM_ACME_LABEL1,CLOSE_APPLICATION);
		assertEquals(CLOSE_APPLICATION, defaultValue);
	}

}
