package nth.reflect.fw.layer5provider.authorization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.TestString;
import nth.reflect.fw.junit.ReflectApplicationForJUnit;

public class AuthorizationProviderTest {

	private AuthorizationProviderTestObject authorizationProvider;

	@Before
	public void setUp() throws Exception {
		ReflectApplicationForJUnit application = new ReflectApplicationForJUnit() {

			@Override
			public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
				return AuthorizationProviderTestObject.class;
			}
		};
		DependencyInjectionContainer container = application.createContainer();
		authorizationProvider = container.get(AuthorizationProviderTestObject.class);
	}

	@Test()
	public void notLoggedIn() {
		String username = authorizationProvider.getCurrentUserName();
		assertEquals("", username);
	}

	@Test(expected = InvalidNameOrPasswordException.class)
	public void inCorrectLogin() throws InvalidNameOrPasswordException {
		authorizationProvider.login(TestString.BOGUS, TestString.BOGUS);
	}

	@Test()
	public void correctLogin() throws InvalidNameOrPasswordException {
		authorizationProvider.login("carla", "password1");
		String username = authorizationProvider.getCurrentUserName();
		assertEquals("carla", username);
	}

	@Test()
	public void inRole() throws InvalidNameOrPasswordException {
		authorizationProvider.login("carla", "password1");
		boolean inRole = authorizationProvider.userInRole("salesmanager");
		assertTrue(inRole);
		inRole = authorizationProvider.userInRole("customer");
		assertFalse(inRole);
	}

}
