package nth.introspect.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.UserInterfaceLayer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.Provider;
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
import nth.introspect.layer5provider.path.url.ClassResourceUrlHandler;
import nth.introspect.layer5provider.path.url.ReflectUrlConnection;
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
 * 
 * <pre>
 * public class ProductServiceTest {
 * 
 * 	private ProductService productService;
 * 
 * 	&#064;Before
 * 	public void setUp() throws Exception {
 * 		DependencyInjectionContainer container = new IntrospectApplicationForJUnit()
 * 				.addServiceClass(ProductService.class)
 * 				.addInfrastructureClass(ProductRepositoryMockup.class)
 *              .createContainer();
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
 * 
 * </pre>
 * 
 * @author nilsth
 *
 */
public class IntrospectApplicationForJUnit implements IntrospectApplication {

	private final List<Class<?>> serviceClasses;
	private final List<Class<?>> infrastructureClasses;

	public IntrospectApplicationForJUnit() {
		serviceClasses = new ArrayList<>();
		infrastructureClasses = new ArrayList<>();
	}

	/**
	 * Fluent interface method to add ServiceClasses
	 */
	public IntrospectApplicationForJUnit addServiceClass(Class<?> serviceClass) {
		serviceClasses.add(serviceClass);
		return this;
	}

	/**
	 * Fluent interface method to add InfrastructureClasses
	 */
	public IntrospectApplicationForJUnit addInfrastructureClass(Class<?> infrastructureClass) {
		infrastructureClasses.add(infrastructureClass);
		return this;
	}

	/**
	 * @return a {@link DependencyInjectionContainer} for testing
	 */
	public DependencyInjectionContainer createContainer() {
		return new UserInterfaceContainer(this, false);
	}

	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserInterfaceControllerForJUnit.class;
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
	public final List<Class<?>> getServiceClasses() {
		return serviceClasses;
	}

	@Override
	public final List<Class<?>> getInfrastructureClasses() {
		return infrastructureClasses;
	}

	@Override
	public List<ReflectUrlConnection> getReflectUrlStreamHandlers() {
		return Arrays.asList(new ClassResourceUrlHandler());
	}

}
