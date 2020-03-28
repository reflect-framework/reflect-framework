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

public class TranslatedActionMethodDisplayNameTest {

	private static final String DOMÄNENOBJEKT_AKTIONSMETHODE = "Domänenobjekt Aktionsmethode";
	private static final String ANZEIGENAME_DER_KOMMENTIERTEN_AKTIONSMETHODE = "Anzeigename der kommentierten Aktionsmethode";
	private static final String METHOD_NAME = DomainObject.ACTION_METHOD;
	private TranslatedString methodDisplayName;
	private TranslatedString annotatedMethodDisplayName;

	@Before
	public void setUp() throws Exception {
		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
		languageProvider.setDefaultLocale(Locale.GERMAN);
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		methodDisplayName = reflectionProvider.getDomainClassInfo(DomainObject.class).getActionMethodInfo(METHOD_NAME)
				.getDisplayName();
		annotatedMethodDisplayName = reflectionProvider.getDomainClassInfo(AnnotatedDomainObject.class)
				.getActionMethodInfo(METHOD_NAME).getDisplayName();
	}

	@Test
	public void testToString_givenMethodDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(methodDisplayName.getTranslation()).isEqualTo(DOMÄNENOBJEKT_AKTIONSMETHODE);
	}

	@Test
	public void testToString_givenAnnotatedMethodDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(annotatedMethodDisplayName.getTranslation()).isEqualTo(ANZEIGENAME_DER_KOMMENTIERTEN_AKTIONSMETHODE);
	}

	@Test
	public void testGetTranslation_givenMethodDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(methodDisplayName.getTranslation()).isEqualTo(DOMÄNENOBJEKT_AKTIONSMETHODE);
	}

	@Test
	public void testGetTranslation_givenAnnotatedMethodDisplayName_returnsTranslationFromPropertyFile() {
		assertThat(annotatedMethodDisplayName.getTranslation()).isEqualTo(ANZEIGENAME_DER_KOMMENTIERTEN_AKTIONSMETHODE);
	}

	@Test
	public void testGetDefaultEnglish_givenMethodDisplayName_returnsDefaultEnglish() {
		String expected = StringUtil.convertToNormalCase(METHOD_NAME);
		assertThat(methodDisplayName.getDefaultEnglish()).isEqualTo(expected);
	}

	@Test
	public void testGetDefaultEnglish_givenAnnotatedMethodDisplayName_returnsAnnotatedDisplayName() {
		assertThat(annotatedMethodDisplayName.getDefaultEnglish())
				.isEqualTo(AnnotatedDomainObject.ANNOTATED_ACTION_METHOD_DISPLAY_NAME);
	}

	@Test
	public void testGetKey_givenMethodDisplayName_returnsMethodDisplayNameKey() {
		String expected = DomainObject.class.getCanonicalName() + "." + METHOD_NAME
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(methodDisplayName.getKey()).isEqualTo(expected);
	}

	@Test
	public void testGetKey_givenAnnotatedMethodDisplayName_returnsAnnotatedMethodDisplayNameKey() {
		String expected = AnnotatedDomainObject.class.getCanonicalName() + "." + METHOD_NAME
				+ TranslatedDisplayName.DISPLAY_NAME_KEY_SUFFIX;
		assertThat(annotatedMethodDisplayName.getKey()).isEqualTo(expected);
	}
}
