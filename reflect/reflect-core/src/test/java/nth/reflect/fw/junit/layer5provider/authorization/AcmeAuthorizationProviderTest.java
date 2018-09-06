package nth.reflect.fw.junit.layer5provider.authorization;

public class AcmeAuthorizationProviderTest {

//	private AcmeAuthorizationProvider authorizationProvider;
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
//		authorizationProvider = container.get(AcmeAuthorizationProvider.class);
//	}
//
//	@Test()
//	public void notLoggedIn() {
//		String username = authorizationProvider.getCurrentUserName();
//		assertEquals("", username);
//	}
//
//	@Test(expected = InvalidNameOrPasswordException.class)
//	public void inCorrectLogin() throws InvalidNameOrPasswordException {
//		authorizationProvider.login("bogus", "bugus");
//	}
//
//	@Test()
//	public void correctLogin() throws InvalidNameOrPasswordException {
//		authorizationProvider.login("carla", "password1");
//		String username = authorizationProvider.getCurrentUserName();
//		assertEquals("carla", username);
//	}
//
//	@Test()
//	public void inRole() throws InvalidNameOrPasswordException {
//		authorizationProvider.login("carla", "password1");
//		boolean inRole = authorizationProvider.userInRole("salesmanager");
//		assertTrue(inRole);
//		inRole = authorizationProvider.userInRole("customer");
//		assertFalse(inRole);
//	}

}
