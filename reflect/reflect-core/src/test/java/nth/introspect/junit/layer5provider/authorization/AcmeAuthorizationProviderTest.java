package nth.introspect.junit.layer5provider.authorization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.junit.IntrospectApplicationForJUnit;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.authorization.InvalidNameOrPasswordException;

import org.junit.Before;
import org.junit.Test;

import com.acme.web.shop.provider.authorization.AcmeAuthorizationProvider;

public class AcmeAuthorizationProviderTest {

	private AcmeAuthorizationProvider authorizationProvider;

	@Before
	public void setUp() throws Exception {
		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {
			
			@Override
			public java.lang.Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
				return AcmeAuthorizationProvider.class;
			}
		};
		DependencyInjectionContainer container = application.createContainer();
		authorizationProvider = container.get(AcmeAuthorizationProvider.class);
	}

	@Test()
	public void notLoggedIn() {
		String username = authorizationProvider.getCurrentUserName();
		assertEquals("", username);
	}

	@Test(expected = InvalidNameOrPasswordException.class)
	public void inCorrectLogin() throws InvalidNameOrPasswordException {
		authorizationProvider.login("bogus", "bugus");
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
