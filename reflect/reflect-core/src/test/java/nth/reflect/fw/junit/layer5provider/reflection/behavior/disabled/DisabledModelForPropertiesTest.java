package nth.reflect.fw.junit.layer5provider.reflection.behavior.disabled;

public class DisabledModelForPropertiesTest {

//	private ClassInfo classInfo;
//
//	@Before
//	public void setUp() throws Exception {
//		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit() {
//
//			@Override
//			public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
//				return AcmeAuthorizationProvider.class;
//			}
//		};
//		DependencyInjectionContainer container = application.createContainer();
//		AcmeAuthorizationProvider authorizationProvider = container
//				.get(AcmeAuthorizationProvider.class);
//		authorizationProvider.login("carla", "password1");
//		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
//		classInfo=reflectionProvider.getClassInfo(DisabledModelForPropertiesTestObject.class);
//	}
//
//
//	@Test
//	public void propertyDisabled() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyDisabled");
//		assertFalse(propertyInfo.isEnabled(obj));
//
//	}
//
//
//	@Test
//	public void propertyEnabled() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyEnabled");
//		assertTrue(propertyInfo.isEnabled(obj));
//
//	}
//	
//	@Test
//	public void propertyDisabledNotInRole() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo( "propertyDisabledNotInRole");
//		assertFalse(propertyInfo.isEnabled(obj));
//	}
//
//	@Test
//	public void propertyEnabledInRole() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyEnabledInRole");
//		assertTrue(propertyInfo.isEnabled(obj));
//
//	}
//
//	@Test
//	public void propertyCollection() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyCollection");
//		assertTrue(propertyInfo.isEnabled(obj));
//	}
//
//	@Test
//	public void propertyDisabledMethod() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyDisabledMethod" );
//		assertFalse(propertyInfo.isEnabled(obj));
//
//	}
//
//	@Test
//	public void propertyEnabledMethod() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyEnabledMethod");
//		assertTrue(propertyInfo.isEnabled(obj));
//	}
//
//	@Test
//	public void propertyDisabledAnnotationDisabledMethod() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyDisabledAnnotationDisabledMethod" );
//		assertFalse(propertyInfo.isEnabled(obj));
//	}
//
//	@Test
//	public void propertyDisabledAnnotationEnabledMethod() {
//		DisabledModelForPropertiesTestObject obj = new DisabledModelForPropertiesTestObject();
//		PropertyInfo propertyInfo = classInfo.getPropertyInfo("propertyDisabledAnnotationEnabledMethod");
//		assertFalse(propertyInfo.isEnabled(obj));
//	}
}
