package nth.introspect.provider.authorization;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.documentation.ActionMethod;
import nth.introspect.documentation.DomainObject;
import nth.introspect.documentation.DomainObjectProperty;
import nth.introspect.documentation.InfrastructureObject;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.documentation.ObjectBehavior;
import nth.introspect.documentation.ServiceObject;
import nth.introspect.provider.Provider;

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
 * <li>
 * the {@link DefaultAuthorizationProvider} (always returns true on the
 * {@link #userInRole(String)} method)</li>
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
 * TODO add example of a hard coded {@link AuthorizationProvider}
 * 
 * <h3>Registering an AuthorizationProvider</h3>
 * <p>
 * The {@link AuthorizationProvider} is registered to the framework with the
 * {@link IntrospectApplication#getAuthorizationProviderClass()} method. By
 * default it returns the {@link DefaultAuthorizationProvider}, which always
 * returns true on the {@link #userInRole(String)} method. You can register
 * another {@link AuthorizationProvider} implementation by overriding the
 * {@link IntrospectApplication#getAuthorizationProviderClass()} method.
 * </p>
 * 
 * <h3>Using an AuthorizationProvider</h3>
 * <p>
 * You can get a reference to the {@link AuthorizationProvider} by adding a
 * {@link AuthorizationProvider} parameter in the constructor of your
 * {@link ServiceObject}, {@link DomainObject} or {@link InfrastructureObject}.
 * The registered {@link AuthorizationProvider} will than be given to the
 * constructor when the object in which you need it will be instantiated by the
 * {@link IntrospectFramework}. You can then link the constructor parameter to a
 * private field so you can use the {@link AuthorizationProvider} throughout the
 * class. It is often used with behavioral enabled and visible methods (see
 * chapter {@link ObjectBehavior}).
 * </p>
 * 
 * Code example
 * <pre>
 * public class OrderService {
 * 
 * 	private final AuthorizationProvider authorizationProvider;
 * 	private final AuthorizationProvider OrderRepository;
 * 
 * 	public OrderService(AuthorizationProvider authorizationProvider,
 * 			OrderRepository orderRepository) {
 * 		this.authorizationProvider = authorizationProvider;
 * 	}
 * 
 * 	public List&lt;Orders&gt; ordersWaitingForPayment() {
 * 		orderrepository.ordersWaitingForPayment();
 * 	}
 * 
 * 	public boolean ordersWaitingForPaymentVisible() {
 * 		return authorizationProvider.userInRole(UserRoles.SalesManager);
 * 	}
 * }
 * 
 * </pre>
 * 
 * <p>
 * Note that if an user is not authorized to change a
 * {@link DomainObjectProperty} or call an {@link ActionMethod} it is best to
 * hide the method or property instead of disabling it. In general you do not
 * want to confuse users with options that they are not allowed to use anyway.
 * </p>
 * 
 * <p>
 * Note that {@link ServiceObject}s and {@link InfrastructureObject}s are
 * automatically created by the framework. DomainObjects that need dependency
 * injection will need to be created using the {@link ServiceContainer} (see
 * {@link DomainObject} chapter).
 * </p>
 * 
 * @author nilsth
 *
 */

public interface AuthorizationProvider extends Provider {
	public String getCurrentUserName();

	public boolean userInRole(String userRole);
}
