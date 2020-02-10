package nth.reflect.fw.layer5provider;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.ConstructionInjection;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.about.AboutProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;

/**
 * {@link Provider}s are responsible for different <a
 * href="cross cutting concerns">cross cutting concerns</a> within an 
 * {@link ReflectApplication} such as:
 * <ul>
 * <li>Authorization (see {@link AuthorizationProvider})</li>
 * <li>Validation (see {@link ValidationProvider})</li>
 * <li>Multi language (see {@link LanguageProvider})</li>
 * <li>Notifications (see {@link NotificationProvider})</li>
 * <li>File path information (see {@link UrlProvider})</li>
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
 * {@link ReflectApplication} class. You are free to implement your own
 * {@link Provider} implementation and register it by overwriting one of the
 * {@link ReflectApplication#get...ProviderClass()} methods
 * </p>
 * 
 * <h3>Provider Construction</h3>
 * <p>
 * {@link Provider}'s are instantiated by the {@link ProviderContainer} (see
 * {@link DependencyInjectionContainer}) {@link Provider}Objects can have
 * references to other {@link Provider}Objects. These objects are injected into
 * the {@link Provider}Objects (see the {@link ConstructionInjection} section.
 * </p>
 * <h3>Provider Presentation</h3>
 * <p>
 * The methods of {@link Provider}Objects are not displayed by the
 * {@link UserInterfaceController}.
 * </p>
 *   
 * <h2>Authorization Provider</h2>
 * <p>
 * {@insert AuthorizationProvider}
 * </p>
 * <h2>Validation Provider</h2>
 * <p>
 * {@insert ValidationProvider}
 * </p>
 * <h2>Language Provider</h2>
 * <p>
 * {@insert LanguageProvider}
 * </p>
 * <h2>Notification Provider</h2>
 * <p>
 * {@insert NotificationProvider}
 * </p>
 * </p>
 * <h2>Reflection Provider</h2>
 * <p>
 * {@insert ReflectionProvider}
 * </p>
 * <h2>About Provider</h2>
 * <p>
 * {@insert AboutProvider}
 * </p>
 * <h2>URL Providers</h2>
 * <p>
 * {@insert UrlProvider}
 * </p>
 * <h2>Property Field Service</h2>
 * <p>
 * {@insert PropertyFieldService}
 * </p>
 * @author Nils ten Hoeve
 */
public interface Provider {

}
