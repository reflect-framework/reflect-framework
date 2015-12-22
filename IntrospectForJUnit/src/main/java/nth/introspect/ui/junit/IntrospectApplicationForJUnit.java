package nth.introspect.ui.junit;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.layer1userinterface.UserInterfaceLayer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceContainer;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureContainer;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.about.DefaultAboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.layer5provider.language.DefaultLanguageProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.DefaultNotificationProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.path.DefaultPathProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.DefaultReflectionProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.validation.DefaultValidationProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;

/**
 * The {@link IntrospectApplicationForJUnit} is created to be used for <a
 * href="http://en.wikipedia.org/wiki/JUnit">JUnit tests</a>. Its big advantage
 * over using one of the other {@link IntrospectApplication} implementations in
 * a JUnit test is that the {@link IntrospectApplicationForJUnit}:
 * <ul>
 * <li>Does not have a {@link UserInterfaceLayer}, because it is not needed for
 * testing. None of the {@link ServiceObject}s, {@link DomainObject}'s
 * {@link InfrastructureObject}, {@link Provider} Objects that you create or
 * maintain do know the User Interface (see {@link IntrospectArchitecture}). We
 * do not need to test the {@link Introspect} {@link UserInterfaceLayer} if we
 * assume it works as it should.</li>
 * <li>You only need to register the classes that need testing. You register
 * classes by overriding the get...Class or get...Classes methods in the
 * {@link IntrospectApplicationForJUnit} class</li>
 * </ul>
 * <p>
 * If you are new to JUnit test I recommend to watch one of the many instruction
 * video's on the Internet. Your IDE most likely supports you with implementing
 * and running JUnit tests (and if not you can probably download a plug-in for
 * it)
 * </p>
 * 
 * <h3>How to download a IntrospectForJUnit demo project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>How to create a new IntrospectForJUnit project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>Example of a testing a ServiceObject and InfrastructureMockUpObject in a
 * JUnit test case</h3>
 * 
 * <pre>
 * public class ProductServiceTest {
 * 
 * 	private ProductService productService;
 * 
 * 	&#064;Before
 * 	public void setUp() throws Exception {
 * 		IntrospectApplicationForJUnit application = new IntrospectApplicationForJUnit() {
 * 
 * 			&#064;Override
 * 			public List&lt;Class&lt;?&gt;&gt; getServiceClasses() {
 * 				return new ClassList(ProductService.class);
 * 			}
 * 
 * 			&#064;Override
 * 			public List&lt;Class&lt;?&gt;&gt; getInfrastructureClasses() {
 * 				return new ClassList(ProductRepositoryMockup.class);
 * 			}
 * 		};
 * 		DependencyInjectionContainer container = application.createContainer();
 * 		productService = container.get(ProductService.class);
 * 	}
 * 
 * 	&#064;Test
 * 	public void bestSellingProductsTest() {
 * 		List&lt;Product&gt; products = productService.bestSellingProducts();
 * 		// assert method calls ...
 * 	}
 * 
 * 	// other test methods ...
 * }
 * </pre>
 * 
 * @author nilsth
 *
 */
public class IntrospectApplicationForJUnit implements IntrospectApplication {

	public DependencyInjectionContainer createContainer() {
		if (hasServiceObjects()) {
			return new ServiceContainer(this);
		} else if (hasInfrastructureObjects()) {
			return new InfrastructureContainer(this);
		} else {
			return new ProviderContainer(this);
		}
	}

	private boolean hasInfrastructureObjects() {
		return getInfrastructureClasses() != null
				&& getInfrastructureClasses().size() > 0;
	}

	private boolean hasServiceObjects() {
		return getServiceClasses() != null && getServiceClasses().size() > 0;
	}

	@Override
	public Class<? extends UserInterfaceController<?>> getUserInterfaceControllerClass() {
		return null;// JUnit has no user interface controller
	}

	@Override
	public Class<? extends ReflectionProvider> getReflectionProviderClass() {
		return DefaultReflectionProvider.class;
	}

	@Override
	public Class<? extends AboutProvider> getAboutProviderClass() {
		return DefaultAboutProvider.class;
	}

	/**
	 * You might want to use the override this method and use the
	 * {@link DefaultPathProvider#DefaultPathProvider(java.net.URI) constructor}
	 */
	@Override
	public Class<? extends PathProvider> getPathProviderClass() {
		return DefaultPathProvider.class;
	}

	@Override
	public Class<? extends LanguageProvider> getLanguageProviderClass() {
		return DefaultLanguageProvider.class;
	}

	@Override
	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
		return DefaultAuthorizationProvider.class;
	}

	@Override
	public Class<? extends ValidationProvider> getValidationProviderClass() {
		return DefaultValidationProvider.class;
	}

	@Override
	public Class<? extends NotificationProvider> getNotificationProviderClass() {
		return DefaultNotificationProvider.class;
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return new ArrayList<Class<?>>();
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<Class<?>>();
	}

}
