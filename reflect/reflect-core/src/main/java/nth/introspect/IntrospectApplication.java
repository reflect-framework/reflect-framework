package nth.introspect;

import java.util.List;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.ObjectBehavior;
import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;

/**
 * {@link IntrospectApplication} is used as initialization parameter for the
 * {@link Introspect} framework.<br>
 * An Introspect application has several purposes:
 * <ul>
 * <li>It provides the name, icon and description of the application (see
 * {@link ObjectBehavior})</li>
 * <li>It provides the location of the distribution package (jar, war)</li>
 * <li>It provides the {@link UserInterfaceController} type, needed for the
 * application with the
 * {@link IntrospectApplication#getUserInterfaceControllerClass()} method. Each
 * application type (commandline, swing, vaadin) requires different
 * implementations of the {@link UserInterfaceController}</li>
 * <li>It provides a list of {@link ServiceObject} types with the
 * {@link IntrospectApplication#getServiceClasses()} method.
 * {@link ServiceObject}s basically provide the actionable/menu items</li>
 * <li>It provides a list of {@link DomainObject} types that need to be created
 * using dependency injection, with the
 * {@link IntrospectApplication#getDomainClasses()} method.</li>
 * <li>It provides a list of {@link InfrastructureObject} types with the
 * {@link IntrospectApplication#getInfrastructureClasses()} method.
 * {@link InfrastructureObject}s basically communicate to the outside world
 * (i.e. data base access objects, email clients, soap clients, etc)</li>
 * <li>It provides the {@link Provider} types with the
 * {@link IntrospectApplication#get...ProviderClass()} methods. {@link Provider}
 * Objects help with <a
 * href="https://en.wikipedia.org/wiki/Cross-cutting_concern">cross cutting
 * concerns</a>. Each application type (commandline, swing, vaadin) requires
 * different implementations of the providers</li>
 * </ul>
 * <p>
 * Each application type (command line, Android, Vaadin, etc..) has its own
 * implementation of {@link IntrospectApplication} to help initializing the
 * framework. See the type hierarchy of {@link IntrospectApplication} to learn
 * which classes can be used and view their java doc to learn how to use them.
 * </p>
 * 
 * <p>
 * If you create a new application you will be extending one of these classes.
 * You will only need to implement some of the methods mentioned above (at least
 * the {@link IntrospectApplication#getServiceClasses()} method).
 * </p>
 * 
 * An example of a desktop application:
 * 
 * <pre>
 * public class AcmeWebShop extends IntrospectApplicationForSwing {
 * 
 * 	// constructor not displayed
 * 
 * 	// main method
 * 
 * 	&#064;Override
 * 	public List&lt;Class&lt;?&gt;&gt; getServiceClasses() {
 * 		List&lt;Class&lt;?&gt;&gt; serviceClasses = new ArrayList&lt;Class&lt;?&gt;&gt;();
 * 		serviceClasses.add(ShoppingCartService.class);
 * 		serviceClasses.add(ProductService.class);
 * 		return serviceClasses;
 * 	}
 * 
 * 	&#064;Override
 * 	public List&lt;Class&lt;?&gt;&gt; getInfrastructureClasses() {
 * 		List&lt;Class&lt;?&gt;&gt; infrastructureClasses = new ArrayList&lt;Class&lt;?&gt;&gt;();
 * 		infrastructureClasses.add(ProductRepository.class);
 * 		infrastructureClasses.add(EmailClient.class);
 * 		infrastructureClasses.add(PaymentClient.class);
 * 		return infrastructureClasses;
 * 	}
 * 
 * }
 * </pre>
 * <p>
 * For more (detailed) examples see the {@link IntrospectApplicationProjects} section.
 * </p>
 * 
 * @author nilsth
 * 
 */

public interface IntrospectApplication {

	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass();

	public Class<? extends ReflectionProvider> getReflectionProviderClass();

	public Class<? extends AboutProvider> getAboutProviderClass();

//	public Class<? extends PathProvider> getPathProviderClass();

	public Class<? extends LanguageProvider> getLanguageProviderClass();

	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass();

	public Class<? extends ValidationProvider> getValidationProviderClass();

	public Class<? extends NotificationProvider> getNotificationProviderClass();
	
	public List<Class<? extends UrlProvider>> getUrlProviderClasses();

	public List<Class<?>> getServiceClasses();

	public List<Class<?>> getInfrastructureClasses();


}
