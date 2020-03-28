package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.AnnotatedPersonService;
import nth.reflect.fw.layer5provider.reflection.behavior.description.PersonService;

public class TranslatedServiceClassDisplayNameTest {

	private static final String ANZEIGENAME_DER_SERVICEKLASSE = "Anzeigename der Serviceklasse";
	private static final String ANZEIGENAME_DER_KOMMENTIERTEN_SERVICEKLASSE = "Anzeigename der kommentierten Serviceklasse";
	private TranslatedString serviceObjectDisplayName;
	private TranslatedString annotatedServiceObjectDisplayName;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().addServiceClass(PersonService.class)
				.addServiceClass(AnnotatedPersonService.class).createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceObjectDisplayName = reflectionProvider.getServiceClassInfo(PersonService.class).getDisplayName();
		annotatedServiceObjectDisplayName = reflectionProvider.getServiceClassInfo(AnnotatedPersonService.class)
				.getDisplayName();
	}

	@Test
	public void testToString_givenServiceObjectDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(serviceObjectDisplayName.toString()).isEqualTo(ANZEIGENAME_DER_SERVICEKLASSE);
	}

	@Test
	public void testToString_givenAnnotatedServiceObjectDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(annotatedServiceObjectDisplayName.toString()).isEqualTo(ANZEIGENAME_DER_KOMMENTIERTEN_SERVICEKLASSE);
	}

	@Test
	public void testGetTranslation_givenServiceObjectDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(serviceObjectDisplayName.getTranslation()).isEqualTo(ANZEIGENAME_DER_SERVICEKLASSE);
	}

	@Test
	public void testGetTranslation_givenAnnotatedServiceObjectDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(annotatedServiceObjectDisplayName.getTranslation())
				.isEqualTo(ANZEIGENAME_DER_KOMMENTIERTEN_SERVICEKLASSE);
	}

	@Test
	public void testGetDefaultEnglish_givenServiceObjectDisplayName_returnsPluralOfServiceObject() {
		assertThat(serviceObjectDisplayName.getDefaultEnglish()).isEqualTo("People");
	}

	@Test
	public void testgetDefaultEnglish_givenAnnotatedServiceObjectDisplayName_returnsAnnnotatedServiceObject() {
		assertThat(annotatedServiceObjectDisplayName.getDefaultEnglish())
				.isEqualTo(AnnotatedPersonService.ANNOTATED_CLASS_DISPLAY_NAME);
	}

	@Test
	public void testGetKey_givenServiceObjectDisplayName_returnsServiceObjectCanonialName() {
		String expected = PersonService.class.getCanonicalName() + TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(serviceObjectDisplayName.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedServiceObjectDisplayName_returnsServiceObjectWithAnnotationsCanonialName() {
		String expected = AnnotatedPersonService.class.getCanonicalName()
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(annotatedServiceObjectDisplayName.getKey()).isEqualTo(expected);
	}

}
