package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.AnnotatedDomainObject;
import nth.reflect.fw.stubs.GermanLanguageFile;

public class TranslatedPropertyActionMethodDisplayNameTest {

	private static final String ADD = "Add";
	private static final String PROPERTY_NAME = FullFeatureDomainObject.GET_MY_DOMAIN_OBJECT_LIST.replace("getM", "m");
	private TranslatedString propertyActionMethodDisplayName;
	private TranslatedString annotatedPropertyActionMethodDisplayName;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		propertyActionMethodDisplayName = reflectionProvider
				.getDomainClassInfo(FullFeatureDomainObject.class)
				.getPropertyInfo(PROPERTY_NAME)
				.getActionMethodInfos()
				.stream()
				.filter(p -> p.getMethod().getName().contentEquals(FullFeatureDomainObject.MY_DOMAIN_OBJECT_LIST_ADD))
				.findFirst()
				.get()
				.getDisplayName();
		annotatedPropertyActionMethodDisplayName = reflectionProvider
				.getDomainClassInfo(AnnotatedDomainObject.class)
				.getPropertyInfo(PROPERTY_NAME)
				.getActionMethodInfos()
				.stream()
				.filter(p -> p.getMethod().getName().contentEquals(FullFeatureDomainObject.MY_DOMAIN_OBJECT_LIST_ADD))
				.findFirst()
				.get()
				.getDisplayName();
	}

	@Test
	public void testToString_givenPropertyActionMethodDisplayName_returnsTranslationFromPropertyFile() {
		String actual = propertyActionMethodDisplayName.toString();
		String key = propertyActionMethodDisplayName.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testToString_givenAnnotatedPropertyActionMethodDisplayName_returnsTranslationFromPropertyFile() {
		String actual = annotatedPropertyActionMethodDisplayName.toString();
		String key = annotatedPropertyActionMethodDisplayName.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenPropertyActionMethodDisplayName_returnsTranslationFromPropertyFile() {
		String actual = propertyActionMethodDisplayName.getTranslation();
		String key = propertyActionMethodDisplayName.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetTranslation_givenAnnotatedPropertyActionMethodDisplayName_returnsTranslationFromPropertyFile() {
		String actual = annotatedPropertyActionMethodDisplayName.getTranslation();
		String key = annotatedPropertyActionMethodDisplayName.getKey();
		String expected = GermanLanguageFile.get(key);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenPropertyActionMethodDisplayName_returnsDefaultEnglish() {
		assertThat(propertyActionMethodDisplayName.getDefaultEnglish()).isEqualTo(ADD);
	}

	@Test
	public void testGetDefaultEnglish_givenAnnotatedPropertyActionMethodDisplayName_returnsAnnotatedActionMethodDisplayName() {
		assertThat(annotatedPropertyActionMethodDisplayName.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_PROPERTY_ACTION_METHOD_DISPLAY_NAME);
	}

	@Test
	public void testGetKey_givenPropertyActionMethodDisplayName_returnsPropertyActionMethodDisplayNameKey() {
		String expected = FullFeatureDomainObject.class.getCanonicalName() + "." + PROPERTY_NAME + ADD
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(propertyActionMethodDisplayName.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedPropertyActionMethodDisplayName_returnsAnnotatedPropertyActionMethodDisplayNameKey() {
		String expected = AnnotatedDomainObject.class.getCanonicalName() + "." + PROPERTY_NAME + ADD
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(annotatedPropertyActionMethodDisplayName.getKey()).isEqualTo(expected);
	}

}
