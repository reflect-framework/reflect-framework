package nth.introspect.layer4infrastructure;

import nth.introspect.container.ConstructionInjection;
import nth.introspect.documentation.Documentation;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;

/**
 * <p>
 * {@link InfrastructureObject}s provide generic technical capabilities to
 * support the higher layers. {@link InfrastructureObject}s communicate with
 * other systems such as external hardware (like disks), databases,
 * web-services, etc...
 * </p>
 * <h3>Function and naming of InfrastructureObjects</h3>
 * <p>
 * The name of an {@link InfrastructureObject}s depends on what it does. It is
 * common practice to use the following naming:
 * <ul>
 * <li>Communicating with a <a
 * href="https://en.wikipedia.org/wiki/Database">database</a>:<br>
 * class names end with 'Repository', e.g.: CustomerRepository, OrderRepository,
 * etc...</li>
 * <li>Communicating with a <a href=
 * "https://en.wikipedia.org/?title=Web_servicehttps://en.wikipedia.org/?title=Web_service"
 * >web-service</a>:<br>
 * class names end with 'Client', e.g.: EmailClient, GoogleMapsClient,
 * SOAPClient, etc..</li>
 * <li>Creating objects with a <a
 * href="https://en.wikipedia.org/wiki/Factory_(object-oriented_programming)"
 * >factory</a> class:<br>
 * class names end with 'Factory', e.g.: ReportFactory, etc..</li>
 * <li><a href="https://en.wikipedia.org/wiki/Logfile">Logging</a>:<br>
 * class names end with 'Logger'</li>
 * </ul>
 * </p>
 * <h3>Construction</h3>
 * <p>
 * The principle of “naked objects” is that any <a
 * href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">'Plain Old Java
 * Object' (POJO)</a> can function as a {@link InfrastructureObject}. In other
 * words: a {@link InfrastructureObject} class does not have to inherit from any
 * special class, nor implement any particular interface, nor have any specific
 * attributes.
 * </p>
 * <p>
 * You can:
 * <ul>
 * <li>
 * write new {@link InfrastructureObject}s.</li>
 * <li>reuse {@link InfrastructureObject}s from existing projects</li>
 * <li>use or extend one of the {@link InfrastructureObject}s of one of the
 * {@link Introspect}Infrastructure projects. Open the type hierarchy of the
 * {@link InfrastructureObject}, to see all these convenience classes</li>
 * <li>or use or extend {@link InfrastructureObject}s from other (open source)
 * projects</li>
 * </ul>
 * {@link InfrastructureObject}s can have references to other objects. These objects are
 * injected into the InfrastructureObjects (see the {@link ConstructionInjection}
 * section.)
 * </p>
 * <h3>Infrastructure Object Presentation</h3>
 * <p>
 * The methods of infrastructure object are unknown to the
 * {@link UserInterfaceController} and are not displayed on the <a
 * href="https://en.wikipedia.org/wiki/User_interface">User Interface</a>.
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface InfrastructureObject extends Documentation {

}
