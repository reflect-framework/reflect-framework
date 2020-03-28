package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

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
import nth.reflect.fw.layer5provider.reflection.behavior.description.AnnotatedDomainObject;

public class TranslatedDomainClassDisplayNameTest {

	private static final String ANZEIGENAME_DES_DOMÄNENOBJEKTS = "Anzeigename des Domänenobjekts";
	private static final String ANZEIGENAME_DES_KOMMENTIERTEN_DOMÄNENOBJEKTS = "Anzeigename des kommentierten Domänenobjekts";
	private TranslatedString domainObjectDisplayName;
	private TranslatedString annotatedDomainObjectDisplayName;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainObjectDisplayName = reflectionProvider.getDomainClassInfo(DomainObject.class).getDisplayName();
		annotatedDomainObjectDisplayName = reflectionProvider.getDomainClassInfo(AnnotatedDomainObject.class)
				.getDisplayName();
	}

	@Test
	public void testToString_givenDomainObjectDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(domainObjectDisplayName.toString()).isEqualTo(ANZEIGENAME_DES_DOMÄNENOBJEKTS);
	}

	@Test
	public void testToString_givenAnnotatedDomainObjectDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(annotatedDomainObjectDisplayName.toString()).isEqualTo(ANZEIGENAME_DES_KOMMENTIERTEN_DOMÄNENOBJEKTS);
	}

	@Test
	public void testGetTranslation_givenDomainObjectDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(domainObjectDisplayName.getTranslation()).isEqualTo(ANZEIGENAME_DES_DOMÄNENOBJEKTS);
	}

	@Test
	public void testGetTranslation_givenAnnotatedDomainObjectDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(annotatedDomainObjectDisplayName.getTranslation())
				.isEqualTo(ANZEIGENAME_DES_KOMMENTIERTEN_DOMÄNENOBJEKTS);
	}

	@Test
	public void testGetDefaultEnglish_givenDomainObjectDisplayName_returnsDomain_Object() {
		assertThat(domainObjectDisplayName.getDefaultEnglish()).isEqualTo("Domain object");
	}

	@Test
	public void testgetDefaultEnglish_givenAnnotatedDomainObjectDisplayName_returnsAnnnotatedDomainObject() {
		assertThat(annotatedDomainObjectDisplayName.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_CLASS_DISPLAY_NAME);
	}

	@Test
	public void testGetKey_givenDomainObjectDisplayName_returnsDomainObjectCanonialName() {
		String expected = DomainObject.class.getCanonicalName() + TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(domainObjectDisplayName.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedDomainObjectDisplayName_returnsDomainObjectWithAnnotationsCanonialName() {
		String expected = AnnotatedDomainObject.class.getCanonicalName()
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(annotatedDomainObjectDisplayName.getKey()).isEqualTo(expected);
	}

}
