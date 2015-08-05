package nth.introspect.layer2service;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.ConstructionInjection;
import nth.introspect.documentation.Documentation;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.reflection.behavior.ObjectBehavior;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

/**
 * <p>
 * The word 'service' implies:
 * <ul>
 * <li>
 * There is a client that needs to be served. For the IntrospectFramework this
 * is the user, trough the {@link UserInterfaceController}</li>
 * <li>There are activities\ operations. For the IntrospectFramework these are
 * defined as {@link ActionMethod}'s</li>
 * <li>An activity\ operation is done with other things. For the
 * IntrospectFramework these operations are done with or on {@link DomainObject}
 * s trough {@link ActionMethod} parameters and return values</li>
 * </ul>
 * </p>
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
 * Quoting Eric Evans: “{@link ServiceObject}s should be state-less. State-less
 * here means that any client can use any instance of the {@link ServiceObject}s
 * without regard to the instance’s individual history. The execution of the
 * service will use information that is accessible globally, and may even change
 * that global information (have side-effects). But it does not hold state of
 * its own that affects its behavior, as most domain objects do."
 * </p>
 * <p>
 * {@link ServiceObject}s do not have state and therefore should not have
 * properties (no getter and setter methods). My personal opinion is that a
 * {@link ServiceObject} with state is a <a
 * href="http://en.wikipedia.org/wiki/Code_smell">code-smell</a>, which you can
 * solve by moving the {@link ServiceObjectActionMethod}s that share state
 * (fields) to new or existing {@link DomainObject}s.</li>
 * </p>
 * <h3>Service Objects should be flat</h3>
 * <p>
 * <a href="https://en.wikipedia.org/?title=Object-oriented_programming"> Object
 * Orientated Programming</a> favors to put business logic and the validation
 * logic into the {@link DomainObject}s (and sometimes
 * {@link InfrastructureObject}s) as much as possible.
 * {@link ServiceObjectActionMethod}s should therefore not contain business
 * logic or validation logic, but delegate the work to collaborations of
 * {@link DomainObject}s and {@link InfrastructureObject}s, in order to prevent
 * the <a href="http://martinfowler.com/bliki/AnemicDomainModel.html">Anemic
 * Domain Model</a> - <a
 * href="http://en.wikipedia.org/wiki/Anti-pattern">anti-pattern</a>.
 * </p>
 * <p>
 * <h3>A web shop example</h3>
 * <ul>
 * <li>The {@link UserInterfaceController} class calls the
 * {@link ServiceObjectActionMethod} findProduct(searchCriteria) method on
 * {@link ServiceObject}: ProductService</li>
 * <li>This method will call the findProduct on the {@link InfrastructureObject}
 * : ProductRepository</li>
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
 * The principle of “naked objects” is that any <a
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
 * {@link ServiceObject}s can have references to other objects. These objects
 * are injected into the ServiceObject (see the
 * {@link ConstructionInjection} section)
 * </p>
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
 * <h2>Action Methods</h2> 
<p>
{@link ServiceObject}s are all about {@link ActionMethod}s that represent the main menu items (see section {@link ServiceObjectActionMethod})
</p>
 * 
 * @author Nils ten Hoeve
 */
public interface ServiceObject extends Documentation {

}
