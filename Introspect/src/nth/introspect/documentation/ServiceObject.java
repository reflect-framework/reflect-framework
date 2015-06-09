package nth.introspect.documentation;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.impl.DomainContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.Provider;

/**
 * {@link ServiceObject}s do not need to implement any interface or superclass.<br>
 * <br>
 * {@link ServiceObject}s are placed in the {@link ServiceLayer}<br>
 * {@link ServiceObject}s contain methods that basically represent the main menu
 * items in {@link UserInterfaceController} <br>
 * {@link ServiceObject}s are normally named to the
 * {@link DomainObject}s that they service (e.g. CustomerService).
 * 
 * 
 * •	To provide methods for creating and retrieving domain objects where the user does not have an existing object to navigate from. Services that perform this role are often referred to as 'repositories'. (Some designers prefer to draw a distinction between a 'factory' service, which has responsibility for creating domain objects, and a 'repository' service for retrieving them. If you prefer to maintain this distinction, then they may be implemented in two separate services. However, there is merit in keeping the ideas together, and we tend to refer to the combination as a 'repository'. For more information see Section 2.2.5, “Repository”.
•	To provide a bridge to external functionality. For more information see Section 2.2.6, “External or System service”.
•	To provide functionality that is to be shared by multiple classes of domain objects which do not necessarily have any common superclass. This is achieved through the concept of contributed actions whereby methods are written on a service but appear to the user as actions on a domain object. For more information see Section 2.2.7, “Contributed action”.
In whichever of these roles, a service is just an ordinary POCO class but without any state - just methods. Just like a domain object it does not have to inherit from any special class, nor implement any interface, nor include any specific attribute. What makes it a service is simply that it is registered as a service in the Services property of the Run class. Registering the service serves two purposes:
•	It makes the service's actions available to the user
•	It instructs the Naked Objects framework to inject that service into any domain object that needs access to it. See Dependency Injection.
A service may provide multiple methods any of which may appear as user actions - following the same rules as for actions on a domain object.


* Service objects contain:
 * <ul>
 *  * <li>Methods: that either represent a {@link ServiceObjectActionMethod}s or
 * define {@link ObjectBehavior}</li>
 * <li>Annotations: that define {@link ObjectBehavior}</li>
 * </ul>
 * TODO service objects are not suppose to have state (TODO explain why) and therefore may not have properties (no getter and setter methods).
 * 
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
 *TODO: Service objects need to be registred to the {@link IntrospectApplication#getServiceClasses()} method
 * 
 * <br>
 * {@link ServiceObject}s are created by the {@link ServiceContainer}. TODO how objects are created with Dependency Injection by the {@link IntrospectFramework}:<br>
 * 
 * {@link ServiceObject}s can have references to other objects (being other {@link ServiceObject}s, {@link DomainObject}s, {@link InfrastructureObject}s or
 * {@link Provider} objects). In example: TODO: A Customer object needs a references to
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
 * <h3>Presentation</h3> TODO menu items
 * <br>
 * <h2>Action Methods</h2> {@insert ServiceObjectActionMethod}
 * 
 * @author Nils ten Hoeve
 */
public interface ServiceObject extends Documentation {

}
