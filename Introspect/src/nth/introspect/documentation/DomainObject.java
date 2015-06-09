package nth.introspect.documentation;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.impl.DomainContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.Provider;
import nth.introspect.provider.domain.info.DomainInfoProvider;

/**
 * Domain objects represent entities; the nouns of the domain. If your
 * application domain is a sales application it’s likely that your domain model
 * contains domain objects such as: customers, products and orders. <br>
 * <br>
 * Domain objects are created by a developer or are reused from an existing
 * application that needs to be re-written. They can be created from scratch or
 * generated from a schema (in example from a <a
 * href="http://en.wikipedia.org/wiki/Database_schema">database schema</a>, <a
 * href="http://nl.wikipedia.org/wiki/XML_Schema">XML schema</a> or <a
 * href="http://en.wikipedia.org/wiki/Web_Services_Description_Language">web
 * service</a>)<br>
 * <br>
 * Domain objects contain:
 * <ul>
 * <li><a
 * href="http://en.wikipedia.org/wiki/Property_(programming)">Properties</a>:
 * that represent specific information within a {@link DomainObject}</li>
 * <li>Methods: that either represent a {@link DomainObjectActionMethod}s or
 * define {@link ObjectBehavior}</li>
 * <li>Annotations: that define {@link ObjectBehavior}</li>
 * </ul>
 * 
 * <h3>Naming</h3> Domain objects names are nouns, such as customer, product and
 * order. They basically describe the things that are important in your
 * application.
 * 
 * <h3>Construction</h3>
 * <p>
 * The principle of “naked objects” is that any <a
 * href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">'Plain Old Java
 * Object' (POJO)</a> can function as a domain object. In other words: a domain
 * class does not have to inherit from any special class, nor implement any
 * particular interface, nor have any specific attributes.
 * </p>
 *
 * {@link DomainObject}s can be created by different objects e.g.:
 * <ul>
 * <li>By a {@link ServiceObjectActionMethod} such as
 * CustomerService.createNewCustomer()
 * <li>By a {@link DomainObjectActionMethod}
 * such as ShoppingCart.lineItemCreate(LineItem lineItem)</li>
 * <li>By a {@link DomainContainer} such as
 * domainContainer.getObject(Customer.class)</li>
 * <li>By a {@link InfrastructureObject} method such as
 * OrderRepository.allOpenOrders() or
 * ShoppingCartFactory.createForCustomer(Customer customer).</li>
 * </ul>
 * <br>
 * There are 2 ways to create new {@link DomainObject}s:
 * <ul>
 * <li>Creating a new {@link DomainObject} with the new keyword:<br>
 * In example: Order order=new OrderLine()</li>
 * <li>Creating a domain object using Dependency Injection:<br>
 * Sometimes you want a new {@link DomainObject} to have references to other
 * objects (being other {@link DomainObject}s, {@link InfrastructureObject}s or
 * {@link Provider}Objects). In example: A Customer object needs a references to
 * a ShoppingCartFactory object. The Customer object can therefore be created by
 * the {@link DomainContainer} with Customer customer=(Customer)
 * domainContainer.getObject(Customer.class). The ShopingCart object will
 * automatically be injected as a constructor parameter of the Customer class.
 * In order to create {@link DomainObject}s using dependency injection you need
 * to:</li>
 * <ul>
 * <li>Add the reference object as a parameter in the constructor and link it to
 * a private field, so that it can be used throughout the class. TODO what happens see above</li>
 * <li>Override the {@link IntrospectApplication#getDomainClasses()} method and
 * return a list of {@link DomainObject}s that need to be created using
 * Dependency Injection</li>
 * <li>The object that creates the Customer objects needs to have a reference to
 * the {@link DomainContainer}. A CustomerService object can get a reference to
 * the {@link DomainContainer} when it is created by the
 * {@link ServiceContainer} (which is done by the {@link IntrospectFramework})</li>
 * </ul>
 * </ul>
 * 
 * <h3>Presentation</h3> An {@link UserInterfaceController} can display domain
 * objects in 3 ways:<br>
 * <ul>
 * <li>Domain object as form:<br>
 * <img src="ObjectAsForm.png"></li>
 * <li>Domain object as a field in a form:<br>
 * <img src="ObjectAsField.png"></li>
 * <li>Domain object as a row in a table:<br>
 * <img src="ObjectAsTable.png"></li>
 * </ul>
 * <br>
 * <h2>Properties</h2> {@insert DomainObjectProperty}
 * 
 * <h2>Action Methods</h2> {@insert DomainObjectActionMethod}
 * 
 * @author Nils ten Hoeve
 * @see DomainLayer
 * 
 */

public interface DomainObject extends Documentation {

}
