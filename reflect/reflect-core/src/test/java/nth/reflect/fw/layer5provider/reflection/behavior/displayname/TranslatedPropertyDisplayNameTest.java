package nth.reflect.fw.layer5provider.reflection.behavior.displayname;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.AnnotatedDomainObject;

public class TranslatedPropertyDisplayNameTest {

	private static final String MEIN_BYTE_ANZEIGENAME = "Mein Byte Anzeigename";
	private static final String KOMMENTIERTES_DOMÄNENOBJEKT_MEIN_BYTE_ANZEIGENAME = "Kommentiertes Domänenobjekt Mein Byte Anzeigename";
	private static final String PROPERTY_NAME = DomainObject.GET_MY_BYTE.replace("getM", "m");
	private TranslatedString propertyDisplayName;
	private TranslatedString annotatedPropertyDisplayName;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		propertyDisplayName = reflectionProvider.getDomainClassInfo(DomainObject.class).getPropertyInfo(PROPERTY_NAME)
				.getDisplayName();
		annotatedPropertyDisplayName = reflectionProvider.getDomainClassInfo(AnnotatedDomainObject.class)
				.getPropertyInfo(PROPERTY_NAME).getDisplayName();
	}

	@Test
	public void testToString_givenPropertyDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(propertyDisplayName.getTranslation()).isEqualTo(MEIN_BYTE_ANZEIGENAME);
	}

	@Test
	public void testToString_givenAnnotatedPropertyDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(annotatedPropertyDisplayName.getTranslation())
				.isEqualTo(KOMMENTIERTES_DOMÄNENOBJEKT_MEIN_BYTE_ANZEIGENAME);
	}

	@Test
	public void testGetTranslation_givenPropertyDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(propertyDisplayName.getTranslation()).isEqualTo(MEIN_BYTE_ANZEIGENAME);
	}

	@Test
	public void testGetTranslation_givenAnnotatedPropertyDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(annotatedPropertyDisplayName.getTranslation())
				.isEqualTo(KOMMENTIERTES_DOMÄNENOBJEKT_MEIN_BYTE_ANZEIGENAME);
	}

	@Test
	public void testGetDefaultEnglish_givenPropertyDisplayName_returnsDefaultEnglish() {
		String expected = StringUtil.convertToNormalCase(PROPERTY_NAME);
		assertThat(propertyDisplayName.getDefaultEnglish()).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenAnnotatedPropertyDisplayName_returnsAnnotatedDisplayName() {
		assertThat(annotatedPropertyDisplayName.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_PROPERTY_DISPLAY_NAME);
	}

	@Test
	public void testGetKey_givenPropertyDisplayName_returnsPropertyDisplayNameKey() {
		String expected = DomainObject.class.getCanonicalName() + "." + PROPERTY_NAME
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(propertyDisplayName.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedPropertyDisplayName_returnsAnnotatedPropertyDisplayNameKey() {
		String expected = AnnotatedDomainObject.class.getCanonicalName() + "." + PROPERTY_NAME
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(annotatedPropertyDisplayName.getKey()).isEqualTo(expected);
	}

}