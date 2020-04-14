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

public class TranslatedActionMethodDescriptionTest {

	private static final String METHOD_NAME = FullFeatureDomainObject.ACTION_METHOD;
	private TranslatedString methodDescription;
	private TranslatedString annotatedMethodDescription;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		methodDescription = reflectionProvider
				.getDomainClassInfo(FullFeatureDomainObject.class)
				.getActionMethodInfo(METHOD_NAME)
				.getDescription();
		annotatedMethodDescription = reflectionProvider
				.getDomainClassInfo(AnnotatedDomainObject.class)
				.getActionMethodInfo(METHOD_NAME)
				.getDescription();
	}

	@Test
	public void testToString_givenMethodDescription_returnsTranslationFromPropertyFile() {
		String actual = methodDescription.toString();
		String key = methodDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testToString_givenAnnotatedMethodDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedMethodDescription.toString();
		String key = annotatedMethodDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenMethodDescription_returnsTranslationFromPropertyFile() {
		String actual = methodDescription.getTranslation();
		String key = methodDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenAnnotatedMethodDescription_returnsTranslationFromPropertyFile() {
		String actual = annotatedMethodDescription.getTranslation();
		String key = annotatedMethodDescription.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenMethodDescription_returnsDefaultEnglish() {
		String expected = StringUtil.convertToNormalCase(METHOD_NAME);
		assertThat(methodDescription.getDefaultEnglish()).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenAnnotatedMethodDescription_returnsAnnotatedDescription() {
		assertThat(annotatedMethodDescription.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_ACTION_METHOD_DESCRIPTION);
	}

	@Test
	public void testGetKey_givenMethodDescription_returnsMethodDescriptionKey() {
		String expected = FullFeatureDomainObject.class.getCanonicalName() + "." + METHOD_NAME
				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(methodDescription.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedMethodDescription_returnsAnnotatedMethodDescriptionKey() {
		String expected = AnnotatedDomainObject.class.getCanonicalName() + "." + METHOD_NAME
				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
		assertThat(annotatedMethodDescription.getKey()).isEqualTo(expected);
	}
}
