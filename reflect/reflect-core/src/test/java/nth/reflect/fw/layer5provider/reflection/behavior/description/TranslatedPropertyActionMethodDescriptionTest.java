package nth.reflect.fw.layer5provider.reflection.behavior.description;

public class TranslatedPropertyActionMethodDescriptionTest {

//	private static final String PROPERTY_NAME = DomainObject.GET_MY_DOMAIN_OBJECT_LIST.replace("getM", "m");
//	private TranslatedString propertyActionMethodDescription;
//	private TranslatedString annotatedPropertyActionMethodDescription;
//
//	@Before
//	public void setUp() throws Exception {
//		DependencyInjectionContainer container = new ReflectApplicationForJUnit().createContainer();
//		DefaultLanguageProvider languageProvider = container.get(DefaultLanguageProvider.class);
//		languageProvider.setDefaultLocale(Locale.GERMAN);
//		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
//		propertyActionMethodDescription = reflectionProvider.getDomainClassInfo(DomainObject.class)
//				.getPropertyInfo(PROPERTY_NAME).getActionMethodInfos().stream()
//				.filter(p -> p.getSimpleName().contentEquals(DomainObject.ADD_DOMAIN_OBJECT)).findFirst().get()
//				.getDescription();
//		annotatedPropertyActionMethodDescription = reflectionProvider.getDomainClassInfo(AnnotatedDomainObject.class)
//				.getPropertyInfo(PROPERTY_NAME).getActionMethodInfos().stream()
//				.filter(p -> p.getSimpleName().contentEquals(DomainObject.ADD_DOMAIN_OBJECT)).findFirst().get()
//				.getDescription();
//	}
//
//	@Test
//	public void testToString_givenPropertyActionMethodDescription_returnsTranslationFromPropertyFile() {
//		assertThat(propertyActionMethodDescription.getTranslation()).isEqualTo("Mein Byte");
//	}
//
//	@Test
//	public void testToString_givenAnnotatedPropertyActionMethodDescription_returnsTranslationFromPropertyFile() {
//		assertThat(annotatedPropertyActionMethodDescription.getTranslation()).isEqualTo("Kommentierte mein Byte");
//	}
//
//	@Test
//	public void testGetTranslation_givenPropertyActionMethodDescription_returnsTranslationFromPropertyFile() {
//		assertThat(propertyActionMethodDescription.getTranslation()).isEqualTo("Mein Byte");
//	}
//
//	@Test
//	public void testGetTranslation_givenAnnotatedPropertyActionMethodDescription_returnsTranslationFromPropertyFile() {
//		assertThat(annotatedPropertyActionMethodDescription.getTranslation()).isEqualTo("Kommentierte mein Byte");
//	}
//
//	@Test
//	public void testGetDefaultEnglish_givenPropertyActionMethodDescription_returnsDefaultEnglish() {
//		String expected = StringUtil.convertToNormalCase(PROPERTY_NAME);
//		assertThat(propertyActionMethodDescription.getDefaultEnglish()).isEqualTo(expected);
//	}
//
//	@Test
//	public void testGetDefaultEnglish_givenAnnotatedPropertyActionMethodDescription_returnsAnnotatedActionMethodDescription() {
//		assertThat(annotatedPropertyActionMethodDescription.getDefaultEnglish())
//				.isEqualTo(AnnotatedDomainObject.ANNOTATED_PROPERTY_DESCRIPTION);
//	}
//
////	TODO fix {@link DomainObjectPropertyActionMethod} canonial name so that it starts with property name,e.f.myDomainObjectListAddItem(DomainObject)
//
//	@Test
//	public void testGetKey_givenPropertyActionMethodDescription_returnsPropertyActionMethodDescriptionKey() {
//		String expected = DomainObject.class.getCanonicalName() + "." + PROPERTY_NAME
//				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
//		assertThat(propertyActionMethodDescription.getKey()).isEqualTo(expected);
//	}
//
//	@Test
//	public void testGetKey_givenAnnotatedPropertyActionMethodDescription_returnsAnnotatedPropertyActionMethodDescriptionKey() {
//		String expected = AnnotatedDomainObject.class.getCanonicalName() + "." + PROPERTY_NAME
//				+ TranslatedDescription.DESCRIPTION_KEY_SUFFIX;
//		assertThat(annotatedPropertyActionMethodDescription.getKey()).isEqualTo(expected);
//	}

}
