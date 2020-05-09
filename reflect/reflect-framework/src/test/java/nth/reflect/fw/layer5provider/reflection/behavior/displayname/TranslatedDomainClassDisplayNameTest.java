package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.GermanLanguageFile;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.AnnotatedDomainObject;

public class TranslatedDomainClassDisplayNameTest {

	private TranslatedString domainObjectDisplayName;
	private TranslatedString annotatedDomainObjectDisplayName;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		domainObjectDisplayName = reflectionProvider.getDomainClassInfo(AllFeatureDomainObject.class).getDisplayName();
		annotatedDomainObjectDisplayName = reflectionProvider
				.getDomainClassInfo(AnnotatedDomainObject.class)
				.getDisplayName();
	}

	@Test
	public void testToString_givenDomainObjectDisplayName_returnsTranslationFromPropertyFile() {
		String actual = domainObjectDisplayName.toString();
		String key = domainObjectDisplayName.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testToString_givenAnnotatedDomainObjectDisplayName_returnsTranslationFromPropertyFile() {
		String actual = annotatedDomainObjectDisplayName.toString();
		String key = annotatedDomainObjectDisplayName.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenDomainObjectDisplayName_returnsTranslationFromPropertyFile() {
		String actual = domainObjectDisplayName.getTranslation();
		String key = domainObjectDisplayName.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenAnnotatedDomainObjectDisplayName_returnsTranslationFromPropertyFile() {
		String actual = annotatedDomainObjectDisplayName.getTranslation();
		String key = annotatedDomainObjectDisplayName.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenDomainObjectDisplayName_returnsDomain_Object() {
		String simpleClassName = AllFeatureDomainObject.class.getSimpleName();
		String expected = StringUtil.convertToNormalCase(simpleClassName);
		String actual = domainObjectDisplayName.getDefaultEnglish();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testgetDefaultEnglish_givenAnnotatedDomainObjectDisplayName_returnsAnnnotatedDomainObject() {
		assertThat(annotatedDomainObjectDisplayName.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_CLASS_DISPLAY_NAME);
	}

	@Test
	public void testGetKey_givenDomainObjectDisplayName_returnsDomainObjectCanonialName() {
		String expected = AllFeatureDomainObject.class.getCanonicalName()
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(domainObjectDisplayName.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedDomainObjectDisplayName_returnsDomainObjectWithAnnotationsCanonialName() {
		String expected = AnnotatedDomainObject.class.getCanonicalName()
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(annotatedDomainObjectDisplayName.getKey()).isEqualTo(expected);
	}

}
