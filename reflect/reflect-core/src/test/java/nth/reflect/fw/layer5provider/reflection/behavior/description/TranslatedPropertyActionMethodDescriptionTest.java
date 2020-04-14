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

public class TranslatedPropertyActionMethodDescriptionTest {

	private static final String ADD = "Add";
	private static final String PROPERTY_NAME = FullFeatureDomainObject.GET_MY_DOMAIN_OBJECT_LIST.replace("getM", "m");
	private TranslatedString propertyActionMethodDescription;
	private TranslatedString annotatedPropertyActionMethodDescription;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		propertyActionMethodDescription = reflectionProvider
				.getDomainClassInfo(FullFeatureDomainObject.class)
				.getPropertyInfo(PROPERTY_NAME)
				.getActionMethodInfos()
				.stream()
				.filter(p -> p.getMethod().getName().contentEquals(FullFeatureDomainObject.MY_DOMAIN_OBJECT_LIST_ADD))
				.findFirst()
				.get()
				.getDescription();
		annotatedPropertyActionMethodDescription = reflectionProvider
				.getDomainClassInfo(AnnotatedDomainObject.class)
				.getPropertyInfo(PROPERTY_NAME)
				.getActionMethodInfos()
				.stream()
				.filter(p -> p.getMethod().getName().contentEquals(FullFeatureDomainObject.MY_DOMAIN_OBJECT_LIST_ADD))
				.findFirst()
				.get()
				.getDescription();
	}

	@Test
	public void testToString_givenPropertyActionMethodDescription_returnsTranslationFromPropertyFile() {
		String actual = propertyActionMethodDescription.toString();
		String key = propertyActionMethodDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testToString_givenAnnotatedPropertyActionMethodDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedPropertyActionMethodDescription.toString();
		String key = annotatedPropertyActionMethodDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenPropertyActionMethodDescription_returnsTranslationFromPropertyFile() {
		String actual = propertyActionMethodDescription.getTranslation();
		String key = propertyActionMethodDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenAnnotatedPropertyActionMethodDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedPropertyActionMethodDescription.getTranslation();
		String key = annotatedPropertyActionMethodDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenPropertyActionMethodDescription_returnsDefaultEnglish() {
		assertThat(propertyActionMethodDescription.getDefaultEnglish()).isEqualTo(ADD);
	}

	@Test
	public void testGetDefaultEnglish_givenAnnotatedPropertyActionMethodDescription_returnsAnnotatedActionMethodDescription() {
		assertThat(annotatedPropertyActionMethodDescription.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_PROPERTY_ACTION_METHOD_DESCRIPTION);
	}

	@Test
	public void testGetKey_givenPropertyActionMethodDescription_returnsPropertyActionMethodDescriptionKey() {
		String expected = FullFeatureDomainObject.class.getCanonicalName() + "." + PROPERTY_NAME + ADD
				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(propertyActionMethodDescription.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedPropertyActionMethodDescription_returnsAnnotatedPropertyActionMethodDescriptionKey() {
		String expected = AnnotatedDomainObject.class.getCanonicalName() + "." + PROPERTY_NAME + ADD
				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(annotatedPropertyActionMethodDescription.getKey()).isEqualTo(expected);
	}

}
