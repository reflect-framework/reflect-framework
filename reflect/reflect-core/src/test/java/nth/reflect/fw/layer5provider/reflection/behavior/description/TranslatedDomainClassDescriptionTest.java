package nth.reflect.fw.layer5provider.reflection.behavior.description;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.GermanLanguageFile;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

public class TranslatedDomainClassDescriptionTest {

	private TranslatedString domainObjectDescription;
	private TranslatedString annotatedDomainObjectDescription;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainObjectDescription = reflectionProvider.getDomainClassInfo(FullFeatureDomainObject.class).getDescription();
		annotatedDomainObjectDescription = reflectionProvider
				.getDomainClassInfo(AnnotatedDomainObject.class)
				.getDescription();
	}

	@Test
	public void testToString_givenDomainObjectDescription_returnsTranslationFromPropertyFile() {
		String actual = domainObjectDescription.toString();
		String key = domainObjectDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testToString_givenAnnotatedDomainObjectDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedDomainObjectDescription.toString();
		String key = annotatedDomainObjectDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenDomainObjectDescription_returnsTranslationFromPropertyFile() {
		String actual = domainObjectDescription.getTranslation();
		String key = domainObjectDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenAnnotatedDomainObjectDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedDomainObjectDescription.getTranslation();
		String key = annotatedDomainObjectDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenDomainObjectDescription_returnsDomain_Object() {
		assertThat(domainObjectDescription.getDefaultEnglish()).isEqualTo("Full feature domain object");
	}

	@Test
	public void testgetDefaultEnglish_givenAnnotatedDomainObjectDescription_returnsAnnnotatedDomainObject() {
		assertThat(annotatedDomainObjectDescription.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_CLASS_DESCRIPTION);
	}

	@Test
	public void testGetKey_givenDomainObjectDescription_returnsDomainObjectCanonialName() {
		String expected = FullFeatureDomainObject.class.getCanonicalName()
				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(domainObjectDescription.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedDomainObjectDescription_returnsDomainObjectWithAnnotationsCanonialName() {
		String expected = AnnotatedDomainObject.class.getCanonicalName() + TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(annotatedDomainObjectDescription.getKey()).isEqualTo(expected);
	}

}
