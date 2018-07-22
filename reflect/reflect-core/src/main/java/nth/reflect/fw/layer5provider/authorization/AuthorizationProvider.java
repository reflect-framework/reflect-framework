package nth.reflect.fw.layer5provider.authorization;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.ConstructionInjection;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.behavior.ObjectBehavior;

/**
 * <p>
 * <a href=
 * "https://en.wikipedia.org/wiki/Authorization_(computer_access_control)"
 * >Authorization</a>, means the ability to control what an individual user can
 * see and do within an application, based upon their identity and the role(s)
 * assigned to them.
 * </p>
 * 
 * The {@link AuthorizationProvider} facilitates this with methods:
 * <ul>
 * <li> {@link AuthorizationProvider#getCurrentUserName()}</li>
 * <li> {@link AuthorizationProvider#userInRole(String userRole)}</li>
 * </ul>
 * 
 * <h3>Types of AuthorizationProvider</h3>
 * 
 * There can be different types of {@link AuthorizationProvider}
 * implementations, depending on the authorization mechanism you prefer. In
 * example: you could write an implementation that uses:
 * <ul>
 * <li>hard coded authorization</li>
 * <li>file based authorization</li>
 * <li>database authorization</li>
 * <li>web container authorization (e.g. <a
 * href="https://tomcat.apache.org/tomcat-7.0-doc/realm-howto.html">Apache
 * Tomcat Realm</a>)</li>
 * <li><a
 * href="https://nl.wikipedia.org/wiki/Lightweight_Directory_Access_Protocol"
 * >LDAP</a></li>
 * <li><a href="https://nl.wikipedia.org/wiki/Active_Directory">Active
 * directory</a></li>
 * <li>or other</li>
 * </ul>
 * 
 * An example of a basic hard coded validation
 * 
 * <pre>
 * public class AcmeAuthorizationProvider implements AuthorizationProvider {
 * 
 * 	private final List&lt;User&gt; users;
 * 	private User currentUser;
 * 
 * 	public AcmeAuthorizationProvider() {
 * 		users = new ArrayList&lt;&gt;();
 * 		users.add(new User(&quot;carla&quot;, &quot;pasword1&quot;, &quot;salesmanager&quot;));
 * 		users.add(new User(&quot;john&quot;, &quot;pasword2&quot;, &quot;customer&quot;));
 * 	}
 * 
 * 	public void login(String userName, String password)
 * 			throws InvalidNameOrPasswordException {
 * 		for (User user : users) {
 * 			if (user.isValid(userName, password)) {
 * 				currentUser = user;
 * 				return;
 * 			}
 * 		}
 * 		throw new InvalidNameOrPasswordException();
 * 	}
 * 
 * 	&#064;Override
 * 	public String getCurrentUserName() {
 * 		return currentUser == null ? &quot;&quot; : currentUser.getName();
 * 	}
 * 
 * 	&#064;Override
 * 	public boolean userInRole(String role) {
 * 		return currentUser == null ? false : currentUser.inRole(role);
 * 	}
 * 
 * }
 * 
 * </pre>
 * 
 * <h3>Registering an AuthorizationProvider</h3>
 * <p>
 * The {@link AuthorizationProvider} is registered to the framework with the
 * {@link ReflectApplication#getAuthorizationProviderClass()} method. By
 * default it returns the {@link DefaultAuthorizationProvider}, which always
 * returns true on the {@link #userInRole(String)} method. You can register
 * another {@link AuthorizationProvider} implementation by overriding the
 * {@link ReflectApplication#getAuthorizationProviderClass()} method.
 * </p>
 * 
 * <h3>Using an AuthorizationProvider</h3>
 * <p>
 * The {@link AuthorizationProvider} is used by the @Hidden and @Disabled
 * annotations (see {@link ObjectBehavior}). If you want to use the
 * {@link AuthorizationProvider} in your code you need to inject it into your
 * object (see {@link ConstructionInjection})
 * 
 * @author nilsth
 *
 */

public interface AuthorizationProvider extends Provider {
	public String getCurrentUserName();

	public boolean userInRole(String userRole);
}
