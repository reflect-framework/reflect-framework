package nth.reflect.fw;

import java.util.List;

import nth.reflect.fw.documentation.ReflectApplicationProjects;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.about.AboutProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.ObjectBehavior;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;

/**
 * {@link ReflectApplication} is used as initialization parameter for the
 * {@link ReflectFramework}.<br>
 * A {@link ReflectApplication} has several purposes:
 * <ul>
 * <li>It provides the name, icon and description of the application (see
 * {@link ObjectBehavior})</li>
 * <li>It provides the location of the distribution package (jar, war)</li>
 * <li>It provides the {@link UserInterfaceController} type, needed for the
 * application with the
 * {@link ReflectApplication#getUserInterfaceControllerClass()} method. Each
 * application type (e.g. for the command line, desktop, mobile devices or the
 * web) requires different implementations of the
 * {@link UserInterfaceController}</li>
 * <li>It provides a list of {@link ServiceObject} types with the
 * {@link ReflectApplication#getServiceClasses()} method. {@link ServiceObject}s
 * basically provide the actionable/menu items</li>
 * <li>It provides a list of {@link DomainObject} types that need to be created
 * using dependency injection, with the
 * {@link ReflectApplication#getDomainClasses()} method.</li>
 * <li>It provides a list of {@link InfrastructureObject} types with the
 * {@link ReflectApplication#getInfrastructureClasses()} method.
 * {@link InfrastructureObject}s basically communicate to the outside world
 * (i.e. data base access objects, email clients, soap clients, etc)</li>
 * <li>It provides the {@link Provider} types with the
 * {@link ReflectApplication#get...ProviderClass()} methods. {@link Provider}
 * Objects help with
 * <a href="https://en.wikipedia.org/wiki/Cross-cutting_concern">cross cutting
 * concerns</a>. Each application type (e.g. for the command line, desktop,
 * mobile devices or the web) requires different implementations of the
 * providers</li>
 * </ul>
 * <p>
 * Each application type (e.g. for the command line, desktop, mobile devices or
 * the web) has its own implementation of {@link ReflectApplication} to help
 * initializing the framework. See the type hierarchy of
 * {@link ReflectApplication} to learn which classes can be used and read their
 * java doc to learn how to use them.
 * </p>
 * 
 * <p>
 * If you create a new application you will be extending one of these classes.
 * You will only need to implement some of the methods mentioned above (at least
 * the {@link ReflectApplication#getServiceClasses()} method).
 * </p>
 * 
 * An example of a desktop application:
 * 
 * <pre>
 * public class WebShopForJavaFX extends ReflectApplicationForJavaFX {
 * 
 * 	private List&lt;Class&lt;?&gt;&gt; serviceClasses;
 * 	private List&lt;Class&lt;?&gt;&gt; infrastructureClasses;
 * 
 * 	public class WebShopForJavaFX() {
 *		serviceClasses=Arrays.asList(ShoppingCartService.class, ProductService.class);
 *		infrastructureClasses=Arrays.asList(ProductRepository.class, EmailClient.class, PaymentClient.class);
 * 	}
 *
 * 	&#064;Override
 * 	public List&lt;Class&lt;?&gt;&gt; getServiceClasses() {
 * 		return serviceClasses;
 * 	}
 * 
 * 	&#064;Override
 * 	public List&lt;Class&lt;?&gt;&gt; getInfrastructureClasses() {
 * 		return infrastructureClasses;
 * 	}
 * 
 * }
 * </pre>
 * <p>
 * For more (detailed) examples see the {@link ReflectApplicationProjects}
 * section.
 * </p>
 * 
 * @author nilsth
 * 
 */

public interface ReflectApplication {

	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass();

	public Class<? extends ReflectionProvider> getReflectionProviderClass();

	public Class<? extends AboutProvider> getAboutProviderClass();

	public Class<? extends LanguageProvider> getLanguageProviderClass();

	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass();

	public Class<? extends ValidationProvider> getValidationProviderClass();

	public Class<? extends NotificationProvider> getNotificationProviderClass();

	public List<Class<? extends UrlProvider>> getUrlProviderClasses();

	public List<Class<?>> getServiceClasses();

	public List<Class<?>> getInfrastructureClasses();

}
