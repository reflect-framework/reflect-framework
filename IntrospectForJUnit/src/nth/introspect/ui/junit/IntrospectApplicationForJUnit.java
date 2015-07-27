package nth.introspect.ui.junit;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.layer1userinterface.UserInterfaceLayer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceContainer;
import nth.introspect.layer2service.ServiceLayer;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureContainer;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.Provider;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.about.DefaultAboutProvider;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.provider.domain.info.DefaultDomainInfoProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.language.DefaultLanguageProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.notification.DefaultNotificationProvider;
import nth.introspect.provider.notification.NotificationProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.validation.DefaultValidationProvider;
import nth.introspect.provider.validation.ValidationProvider;

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
 * <li>Does not need to have a {@link ServiceLayer}. If you are testing
 * {@link DomainObject}'s or {@link InfrastructureObject}, there is no need for
 * {@link ServiceObject}s</li>
 * </ul>
 * If you are new to JUnit test I recommend to watch one of the many instruction
 * video's on the Internet. Your IDE most likely supports you with implementing
 * and running JUnit tests (and if not you can probably download a plug-in for
 * it)<br>
 * <br>
 * <h3>Example of a testing a {@link InfrastructureObject} in a JUnit test case</h3>
 * 
 * <pre>
 * {@code 
 *  import static org.junit.Assert.*;
 *  ...
 *    
 *  public class ProductRepositoryTest {
 * 
 * 	private ProductRepository productRepository;
 * 
 * 	&#064Before
 * 	public void setUp() throws Exception {
 * 		List&lt;Class&lt;?&gt;&gt; infrastuctureClasses = Arrays.asList(ProductRepository.class);
 * 		IntrospectApplicationForJUnit application=new IntrospectApplicationForJUnit(ProductSales.class, infrastuctureClasses);
 * 		productRepository=(ProductRepository) application.get(ProductRepository.class);
 * 		
 * 	}
 * 
 * 	&#064Test
 * 	public void getProductsTest() throws URISyntaxException, IOException {
 * 		List<Product> products = productRepository.getProducts();
 * 		assertEquals(...}
 * </pre>
 * <h3>Example of a testing a {@link ServiceObject} in a JUnit test case</h3>
 * 
 * <pre>
 * {@code 
 *  import static org.junit.Assert.*;
 *  ...
 *    
 *  public class ProductRepositoryTest {
 * 
 * 	private ProductRepository productRepository;
 * 
 * 	&#064Before
 * 	public void setUp() throws Exception {
 *		List&lt;Class&lt;?&gt;&gt; serviceClasses = Arrays.asList(ProductService.class);		
 *		List&lt;Class&lt;?&gt;&gt; infrastuctureClasses = Arrays.asList(ProductRepositoryMockup.class);
 * 		IntrospectApplicationForJUnit application=new IntrospectApplicationForJUnit(ProductSales.class, serviceClasses, infrastuctureClasses);
 * 		productService=(ProductService) application.get(ProductService.class);
 * 		
 * 	}
 * 
 * 	&#064Test
 * 	public void bestSellingProductsTest() {
 * 		List<Product> products = productService.bestSellingProducts();
 * 		assertEquals(...}
 * </pre>
 * 
 * 
 * 
 * 
 * @author nilsth
 *
 */
public class IntrospectApplicationForJUnit implements IntrospectApplication {

	private final IntrospectContainer topContainer;
	private final List<Class<?>> serviceClasses;
	private final List<Class<?>> infrastructureClasses;
	private final Class<? extends IntrospectApplication> introspectApplicationClass;

	/**
	 * Constructur if you only want to test infrastructure classes
	 * 
	 * @param introspectApplicationClass
	 *            needed to set the right path for the pathProvider
	 * @param infrastructureClasses
	 */
	public IntrospectApplicationForJUnit(
			Class<? extends IntrospectApplication> introspectApplicationClass,
			List<Class<?>> infrastructureClasses) {
		this(introspectApplicationClass, null, infrastructureClasses);
	}

	public IntrospectApplicationForJUnit(
			Class<? extends IntrospectApplication> introspectApplicationClass,
			List<Class<?>> serviceClasses, List<Class<?>> infrastructureClasses) {
		this.introspectApplicationClass = introspectApplicationClass;
		this.serviceClasses = serviceClasses;
		this.infrastructureClasses = infrastructureClasses;
		if (serviceClasses == null || serviceClasses.isEmpty()) {
			topContainer = new InfrastructureContainer(this);
		} else {
			topContainer = new ServiceContainer(this);
		}
	}

	public Object get(Class<?> type) {
		return topContainer.get(type);
	}

	@Override
	public Class<? extends UserInterfaceController<?>> getUserInterfaceControllerClass() {
		return null;// JUnit has no user interface controller
	}

	@Override
	public Class<? extends DomainInfoProvider> getDomainInfoProviderClass() {
		return DefaultDomainInfoProvider.class;
	}

	@Override
	public Class<? extends AboutProvider> getVersionProviderClass() {
		return DefaultAboutProvider.class;
	}

	@Override
	public Class<? extends PathProvider> getPathProviderClass() {
		return PathProviderForJUnit.class;
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
		return serviceClasses;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return infrastructureClasses;
	}

	public Class<IntrospectApplication> getIntrospectApplicationClass() {
		return (Class<IntrospectApplication>) introspectApplicationClass;
	}

}
