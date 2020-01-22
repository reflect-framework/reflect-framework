package nth.reflect.fw.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.UserInterfaceLayer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.about.AboutProvider;
import nth.reflect.fw.layer5provider.about.DefaultAboutProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.url.application.ApplicationUrlProvider;
import nth.reflect.fw.layer5provider.url.classresource.ClassResourceUrlProvider;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;

/**
 * The {@link ReflectApplicationForJUnit} is created to be used for
 * <a href="http://en.wikipedia.org/wiki/JUnit">JUnit tests</a>. Its big
 * advantage over using one of the other {@link ReflectApplication}
 * implementations in a JUnit test is that the
 * {@link ReflectApplicationForJUnit}:
 * <ul>
 * <li>Does not have a {@link UserInterfaceLayer}, because it is not needed for
 * testing. None of the {@link ServiceObject}s, {@link DomainObject}'s
 * {@link InfrastructureObject}, {@link Provider} Objects that you create or
 * maintain do know the UserTestObject Interface (see
 * {@link ReflectArchitecture}). We do not need to test the
 * {@link ReflectFramework} {@link UserInterfaceLayer} if we assume it works as
 * it should.</li>
 * <li>You only need to register the classes that need testing. You register
 * classes by overriding the get...Class or get...Classes methods in the
 * {@link ReflectApplicationForJUnit} class</li>
 * </ul>
 * <p>
 * If you are new to JUnit test I recommend to watch one of the many instruction
 * video's on the Internet. Your IDE most likely supports you with implementing
 * and running JUnit tests (and if not you can probably download a plug-in for
 * it)
 * </p>
 * 
 * <h3>How to download a ReflectForJUnit demo project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>How to create a new ReflectForJUnit project</h3>
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
 * 		DependencyInjectionContainer container = new ReflectApplicationForJUnit()
 * 				.addServiceClass(ProductService.class).addInfrastructureClass(ProductRepositoryMockup.class)
 * 				.createContainer();
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
public class ReflectApplicationForJUnit implements ReflectApplication {

	private final List<Class<?>> serviceClasses;
	private final List<Class<?>> infrastructureClasses;

	public ReflectApplicationForJUnit() {
		serviceClasses = new ArrayList<>();
		infrastructureClasses = new ArrayList<>();
	}

	/**
	 * Fluent interface method to add ServiceClasses
	 * 
	 * @param serviceClass A class that represents a {@link ServiceObject}
	 * @return {@link ReflectApplicationForJUnit} to be used for testing
	 */
	public ReflectApplicationForJUnit addServiceClass(Class<?> serviceClass) {
		serviceClasses.add(serviceClass);
		return this;
	}

	/**
	 * Fluent interface method to add InfrastructureClasses
	 * 
	 * @param infrastructureClass A class that represents a {@link InfrastructureObject}
	 * @return {@link ReflectApplicationForJUnit} to be used for testing
	 * */
	public ReflectApplicationForJUnit addInfrastructureClass(Class<?> infrastructureClass) {
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
	public List<Class<? extends UrlProvider>> getUrlProviderClasses() {
		return Arrays.asList(ClassResourceUrlProvider.class, ApplicationUrlProvider.class);
	}

}
