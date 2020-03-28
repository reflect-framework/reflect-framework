package nth.reflect.fw.layer5provider.reflection.behavior.description;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;

public class TranslatedDomainClassDescriptionTest {

	private static final String KOMMENTIERTES_DOMÄNENOBJEKT = "Kommentiertes Domänenobjekt";
	private static final String DOMÄNENOBJEKT_BESCHREIBUNG = "Domänenobjekt Beschreibung";
	private TranslatedString domainObjectDescription;
	private TranslatedString annotatedDomainObjectDescription;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainObjectDescription = reflectionProvider.getDomainClassInfo(DomainObject.class).getDescription();
		annotatedDomainObjectDescription = reflectionProvider.getDomainClassInfo(AnnotatedDomainObject.class)
				.getDescription();
	}

	@Test
	public void testToString_givenDomainObjectDescription_returnsTranslationFromPropertyFile() {
		assertThat(domainObjectDescription.toString()).isEqualTo(DOMÄNENOBJEKT_BESCHREIBUNG);
	}

	@Test
	public void testToString_givenAnnotatedDomainObjectDescription_returnsTranslationFromPropertyFile() {
		assertThat(annotatedDomainObjectDescription.toString()).isEqualTo(KOMMENTIERTES_DOMÄNENOBJEKT);
	}

	@Test
	public void testGetTranslation_givenDomainObjectDescription_returnsTranslationFromPropertyFile() {
		assertThat(domainObjectDescription.getTranslation()).isEqualTo(DOMÄNENOBJEKT_BESCHREIBUNG);
	}

	@Test
	public void testGetTranslation_givenAnnotatedDomainObjectDescription_returnsTranslationFromPropertyFile() {
		assertThat(annotatedDomainObjectDescription.getTranslation()).isEqualTo(KOMMENTIERTES_DOMÄNENOBJEKT);
	}

	@Test
	public void testGetDefaultEnglish_givenDomainObjectDescription_returnsDomain_Object() {
		assertThat(domainObjectDescription.getDefaultEnglish()).isEqualTo("Domain object");
	}

	@Test
	public void testgetDefaultEnglish_givenAnnotatedDomainObjectDescription_returnsAnnnotatedDomainObject() {
		assertThat(annotatedDomainObjectDescription.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_CLASS_DESCRIPTION);
	}

	@Test
	public void testGetKey_givenDomainObjectDescription_returnsDomainObjectCanonialName() {
		String expected = DomainObject.class.getCanonicalName() + TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(domainObjectDescription.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedDomainObjectDescription_returnsDomainObjectWithAnnotationsCanonialName() {
		String expected = AnnotatedDomainObject.class.getCanonicalName() + TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(annotatedDomainObjectDescription.getKey()).isEqualTo(expected);
	}

}
