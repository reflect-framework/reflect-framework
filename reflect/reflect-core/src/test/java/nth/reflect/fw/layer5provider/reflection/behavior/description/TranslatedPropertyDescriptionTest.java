package nth.reflect.fw.layer5provider.reflection.behavior.description;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.stubs.GermanLanguageFile;

public class TranslatedPropertyDescriptionTest {

	private static final String PROPERTY_NAME = FullFeatureDomainObject.GET_MY_BYTE.replace("getM", "m");
	private TranslatedString propertyDescription;
	private TranslatedString annotatedPropertyDescription;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		propertyDescription = reflectionProvider
				.getDomainClassInfo(FullFeatureDomainObject.class)
				.getPropertyInfo(PROPERTY_NAME)
				.getDescription();
		annotatedPropertyDescription = reflectionProvider
				.getDomainClassInfo(AnnotatedDomainObject.class)
				.getPropertyInfo(PROPERTY_NAME)
				.getDescription();
	}

	@Test
	public void testToString_givenPropertyDescription_returnsTranslationFromPropertyFile() {
		String actual = propertyDescription.toString();
		String key = propertyDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testToString_givenAnnotatedPropertyDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedPropertyDescription.toString();
		String key = annotatedPropertyDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenPropertyDescription_returnsTranslationFromPropertyFile() {
		String actual = propertyDescription.getTranslation();
		String key = propertyDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenAnnotatedPropertyDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedPropertyDescription.getTranslation();
		String key = annotatedPropertyDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenPropertyDescription_returnsDefaultEnglish() {
		String expected = StringUtil.convertToNormalCase(PROPERTY_NAME);
		assertThat(propertyDescription.getDefaultEnglish()).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenAnnotatedPropertyDescription_returnsAnnotatedDescription() {
		assertThat(annotatedPropertyDescription.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_PROPERTY_DESCRIPTION);
	}

	@Test
	public void testGetKey_givenPropertyDescription_returnsPropertyDescriptionKey() {
		String expected = FullFeatureDomainObject.class.getCanonicalName() + "." + PROPERTY_NAME
				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(propertyDescription.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedPropertyDescription_returnsAnnotatedPropertyDescriptionKey() {
		String expected = AnnotatedDomainObject.class.getCanonicalName() + "." + PROPERTY_NAME
				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(annotatedPropertyDescription.getKey()).isEqualTo(expected);
	}

}