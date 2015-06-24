package nth.introspect.documentation;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xml.internal.security.encryption.Reference;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.impl.DomainContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.Provider;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

/**
 * Eric Evans explains in his book <a
 * href="https://en.wikipedia.org/wiki/Domain-driven_design">Domain Driven
 * Design</a>, that a good {@link ServiceObject} has three characteristics:
 * <ul>
 * <li>
 * The operation relates to a domain concept that is not a natural part of a
 * {@link DomainObject} (Entities or Value Objects).</li>
 * <li>The interface is defined in terms of other elements of the domain model.</li>
 * <li>The operation is state-less</li>
 * </ul>
 * These characteristics are discussed in more detail in the following
 * paragraphs.
 * 
 * <h3>Service objects operations relates to a domain concept that is not a
 * natural part of a DomainObject</h3>
 * <p>
 * {@link ServiceObject}s contain {@link ServiceObjectActionMethod}s. The
 * {@link UserInterfaceController} displays these methods as menu items and
 * invokes these methods once a user clicks on the menu item.
 * {@link ServiceObjectActionMethod}s create or retrieve {@link DomainObject}s
 * where the user does not have an existing {@link DomainObject} to navigate
 * from.
 * </p>
 * <h3>Service objects define an interface in terms of the domain model</h3>
 * <p>
 * {@link ServiceObject}s basically provides the user (via the
 * {@link UserInterfaceController}) access to {@link DomainObject}s, so that the
 * user can work on them (via the {@link UserInterfaceController}).
 * </p>
 * 
 * <h3>Service objects are state-less</h3>
 * <p>
 * According to Eric Evans: {@link ServiceObject}s should be state-less.
 * State-less here means that any client can use any instance of the
 * {@link ServiceObject}s without regard to the instance�s individual history.
 * The execution of the service will use information that is accessible
 * globally, and may even change that global information (have side-effects).
 * But it does not hold state of its own that affects its behavior, as most
 * domain objects do.
 * </p>
 * <p>
 * {@link ServiceObject}s do not have state and therefore should not have
 * properties (no getter and setter methods). My personal opinion is that a
 * {@link ServiceObject} with state is a <a
 * href="http://en.wikipedia.org/wiki/Code_smell">code-smell</a>, which you can
 * solve by moving the {@link ServiceObjectActionMethod}s that share state
 * (fields) to an new or existing {@link DomainObject}s.</li>
 * </p>
 * <h3>Service Objects should be flat</h3>
 * <p>
 * <a href="https://en.wikipedia.org/?title=Object-oriented_programming"> Object
 * Orientated Programming</a> favors to put business logic and the validation
 * logic into the {@link DomainObject}s (and sometimes
 * {@link InfrastructureObject}s) as much as possible.
 * {@link ServiceObjectActionMethod}s should therefore not contain business
 * logic or validation logic, but delegate the work to collaborations of
 * {@link DomainObject}s and {@link InfrastructureObject}, in order to prevent
 * the <a href="http://martinfowler.com/bliki/AnemicDomainModel.html">Anemic
 * Domain Model</a> - <a
 * href="http://en.wikipedia.org/wiki/Anti-pattern">anti-pattern</a>.
 * </p>
 * <p>
 * A web shop example:
 * <ul>
 * <li>The {@link UserInterfaceController} class calls the
 * {@link ServiceObjectActionMethod} findProduct(searchCriteria) method on
 * {@link ServiceObject}: ProductService</li>
 * <li>This method will call the findProduct on {@link InfrastructureObject}:
 * ProductRepository</li>
 * <li>This method will return a list of {@link DomainObject}s that meet the
 * search criteria</li>
 * <li>The {@link UserInterfaceController} displays the found list of
 * {@link DomainObject}s as a table in a new tab.</li>
 * </ul>
 * </p>
 * 
 * <h3>Naming</h3>
 * <p>
 * {@link ServiceObject}s are normally named after the {@link DomainObject}s
 * that they service and have the suffix 'Service' (e.g. CustomerService,
 * OrderService, etc).
 * </p>
 * 
 * <h3>Construction</h3>
 * <p>
 * The principle of �naked objects� is that any <a
 * href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">'Plain Old Java
 * Object' (POJO)</a> can function as a {@link ServiceObject}. In other words: a
 * service class does not have to inherit from any special class, nor implement
 * any particular interface, nor have any specific attributes.
 * </p>
 * <p>
 * {@link ServiceObject}s are instiantated by the {@link IntrospectFramework},
 * and therefore need to be registered to the
 * {@link IntrospectApplication#getServiceClasses()} method.
 * </p>
 * In example:
 * 
 * <pre>
 * public class WebShop extends IntrospectApplicationFor... {
 * 	&#64;Override
 * 	public List<Class<?>> getServiceClasses() {
 * 		List<Class<?>> serviceClasses = new ArrayList<>();
 * 		serviceClasses.add(ProductService.class);
 * 		serviceClasses.add(ShoppingCartService.class);
 * 		serviceClasses.add(OrderService.class);
 * 		return serviceClasses;
 * 	}
 * 	// etc...
 * }
 * </pre>
 * <p>
 * {@link ServiceObject}s can have references to other objects. These reference
 * objects are injected into the {@link ServiceObject} as constructor parameter
 * during the creation of the {@link ServiceObject} by the
 * {@link IntrospectFramework} (by the {@link ServiceContainer}). This
 * constructor can than be linked to a private final field, so that it can be
 * used throughout the {@link ServiceObject}.
 * </p>
 * In example:
 * 
 * <pre>
 * public class ProductService {
 * 
 * 	private final ProductRepository productRepository;
 * 
 * 	public ProductService(ProductRepository productRepository) {
 * 		this.productRepository = productRepository;
 * 	}
 * 
 * 	&#064;GenericReturnType(Product.class)
 * 	public List&lt;Product&gt; findProduct(ProductSearchCritiria searchCritiria) {
 * 		return productRepository.findProduct(searchCritiria);
 * 	}
 * 
 * 	// other action methods...
 * }
 * </pre>
 * <p>
 * Note that you can only inject reference object types (use constructor
 * parameters types) that are known to the {@link IntrospectFramework}.
 * {@link ServiceObject}s can be injected with the following types (see also
 * {@link IntrospectArchitecture}):
 * <ul>
 * <li>The {@link IntrospectApplication} class</li>
 * <li>Types registered to the {@link IntrospectApplication#getServiceClasses()}
 * method</li>
 * <li>Types registered to the {@link IntrospectApplication#getDomainClasses()}
 * method</li>
 * <li>Types registered to the
 * {@link IntrospectApplication#getInfrastructureClasses()} method</li>
 * <li>The {@link Provider} classes</li>
 * </ul>
 * </p>
 * <p>
 * Note that it is good practice to link the constructor parameter (reference
 * object) to a <a
 * href="https://en.wikibooks.org/wiki/Java_Programming/Keywords/private"
 * >private</a> <a
 * href="https://en.wikipedia.org/wiki/Final_(Java)#Final_variables">final</a>
 * field, so that it is encapsulated and immutable.
 * </p>
 * <p>
 * Note that that if your {@link ServiceObject} needs a lot of references to
 * other objects (too many constructor parameters), your {@link ServiceObject}
 * has most likely to many responsibilities, which could be solved by splitting
 * up a {@link ServiceObject}.
 * </p>
 * 
 * <h3>Presentation</h3> TODO main menu's, object menu's, property menu's <br>
 * 
 * <h3>Service object members</h3>
 * <p>
 * Service objects contain:
 * <ul>
 * <li>{@link ServiceObjectActionMethod}s: that define user actions</li>
 * <li>Methods: that define {@link ObjectBehavior}</li>
 * <li>Annotations: that define {@link ObjectBehavior}</li>
 * </ul>
 * These members are discussed in more detail in the following paragraphs.
 * </p>
 * <h2>Action Methods</h2> {@insert ServiceObjectActionMethod}
 * 
 * @author Nils ten Hoeve
 */
public interface ServiceObject extends Documentation {

}
