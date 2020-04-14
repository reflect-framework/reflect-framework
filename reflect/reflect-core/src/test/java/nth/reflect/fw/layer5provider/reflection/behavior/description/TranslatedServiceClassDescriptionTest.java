package nth.reflect.fw.layer5provider.reflection.behavior.description;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.GermanLanguageFile;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

public class TranslatedServiceClassDescriptionTest {

	private TranslatedString serviceObjectDescription;
	private TranslatedString annotatedServiceObjectDescription;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().addServiceClass(PersonService.class)
				.addServiceClass(AnnotatedPersonService.class).createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		serviceObjectDescription = reflectionProvider.getServiceClassInfo(PersonService.class).getDescription();
		annotatedServiceObjectDescription = reflectionProvider.getServiceClassInfo(AnnotatedPersonService.class)
				.getDescription();
	}

	@Test
	public void testToString_givenServiceObjectDescription_returnsTranslationFromPropertyFile() {
		String actual = serviceObjectDescription.toString();
		String key = serviceObjectDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testToString_givenAnnotatedServiceObjectDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedServiceObjectDescription.toString();
		String key = annotatedServiceObjectDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenServiceObjectDescription_returnsTranslationFromPropertyFile() {
		String actual = serviceObjectDescription.getTranslation();
		String key = serviceObjectDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenAnnotatedServiceObjectDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedServiceObjectDescription.getTranslation();
		String key = annotatedServiceObjectDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenServiceObjectDescription_returnsPluralOfServiceObject() {
		assertThat(serviceObjectDescription.getDefaultEnglish()).isEqualTo("People");
	}

	@Test
	public void testgetDefaultEnglish_givenAnnotatedServiceObjectDescription_returnsAnnnotatedServiceObject() {
		assertThat(annotatedServiceObjectDescription.getDefaultEnglish())
				.isEqualTo(AnnotatedPersonService.ANNOTATED_CLASS_DESCRIPTION);
	}

	@Test
	public void testGetKey_givenServiceObjectDescription_returnsServiceObjectCanonialName() {
		String expected = PersonService.class.getCanonicalName() + TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(serviceObjectDescription.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedServiceObjectDescription_returnsServiceObjectWithAnnotationsCanonialName() {
		String expected = AnnotatedPersonService.class.getCanonicalName()
				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(annotatedServiceObjectDescription.getKey()).isEqualTo(expected);
	}

}
