package nth.introspect.layer5provider;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;

/**
 * {@link Provider}s are responsible for different <a
 * href="cross cutting concerns">cross cutting concerns</a> within the
 * {@link IntrospectFramework} such as:
 * <ul>
 * <li>Authorization (see {@link AuthorizationProvider})</li>
 * <li>Validation (see {@link ValidationProvider})</li>
 * <li>Multi language (see {@link LanguageProvider})</li>
 * <li>Notifications (see {@link NotificationProvider})</li>
 * <li>File path information (see {@link PathProvider})</li>
 * <li>Object meta information (see {@link ReflectionProvider})</li>
 * <li>Version information (see {@link AboutProvider})</li>
 * </ul>
 * <p>
 * {@link Provider}s may be used by any class within an application.
 * </p>
 * <p>
 * Providers are interfaces and can have multiple implementations (depending on
 * what type of application you are using/writing). Which implementation of each
 * provider needs to be used within an application is defined in the
 * {@link IntrospectApplication} class. You are free to implement your own
 * {@link Provider} implementation and register it by overwriting one of the
 * {@link IntrospectApplication#get...ProviderClass()} methods
 * </p>
 * <h3>Authorization Provider</h3>
 * <p>
 * {@insert AuthorizationProvider}
 * </p>
 * <h3>Validation Provider</h3>
 * <p>
 * {@insert ValidationProvider}
 * </p>
 * <h3>Language Provider</h3>
 * <p>
 * {@insert LanguageProvider}
 * </p>
 * <h3>Notification Provider</h3>
 * <p>
 * {@insert NotificationProvider}
 * </p>
 * <h3>Path Provider</h3>
 * <p>
 * {@insert PathProvider}
 * </p>
 * <h3>Reflection Provider</h3>
 * <p>
 * {@insert ReflectionProvider}
 * </p>
 * <h3>About Provider</h3>
 * <p>
 * {@insert AboutProvider}
 * </p>
 * 
 * <h3>Provider Construction</h3>
 * <p>
 * {@link Provider}'s are instantiated by the {@link IntrospectFramework} with
 * the {@link IntrospectContainer}. {@link Provider}'s may have references to
 * other providers by adding the reference providers as a constructor parameter.
 * These providers are passed to the constructor as a parameter when the
 * provider is instantiated.
 * </p>
 * <h3>Provider Presentation</h3>
 * <p>
 * The methods of {@link Provider} object are unknown to the
 * {@link UserInterfaceController} and are not displayed on the <a
 * href="https://en.wikipedia.org/wiki/User_interface">User Interface</a>.
 * 
 * @author Nils ten Hoeve
 */
public interface Provider {

}
