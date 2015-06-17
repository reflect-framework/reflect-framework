package nth.introspect.documentation;

import nth.introspect.Introspect;

/**
 * {@link InfrastructureObject}s 
 * provide generic technical capabilities to support the higher layers. {@link InfrastructureObject}s are often <a href="https://en.wikipedia.org/wiki/Utility_class">utility classes</a> (e.g. logging), or often communicate with other systems (such as databases, mail servers, SOAP servers, etc) or create objects (<a href="https://en.wikipedia.org/wiki/Factory_(object-oriented_programming)">factory classes</a>).  
 * <br>
 * <h3>Naming</h3> The name of an {@link InfrastructureObject}s depends on what
 * it does. It is common practice to use the following naming:
 * <ul>
 * <li>Communicating with an database:<br>
 * classes are named ...Repository<br>
 * e.g.: CustomerRepository, OrderRepository, etc)</li>
 * <li>Communicating with an other system:<br>
 * classes are named ...Client<br>
 * e.g.: EmailClient, GoogleMapsClient, SOAPClient, etc)</li>
 * <li>Logging:<br>
 * classes are named ...Logger</li>
 * <li>Creating objects:<br>
 * classes are named ...Factory<br>
 * e.g.: ReportFactory, etc</li>
 * </ul>
 * <h3>Construction</h3>
 * <p>
 * The principle of “naked objects” is that any <a
 * href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">'Plain Old Java
 * Object' (POJO)</a> can function as a {@link InfrastructureObject}. In other
 * words: a service class does not have to inherit from any special class, nor
 * implement any particular interface, nor have any specific attributes.
 * </p>
 * <p>
 * You can:
 * <ul>
 * <li>
 * write new {@link InfrastructureObject}s.</li>
 * <li>reuse {@link InfrastructureObject}s from existing projects</li>
 * <li>use or extend one of the {@link InfrastructureObject}s of one of the
 * {@link Introspect}Infrastructure projects. Open the type hierarchy of the {@link InfrastructureObject}, to see all these convenience classes</li>
 * <li>or use or extend {@link InfrastructureObject}s from other (open source)
 * projects</li>
 * </ul>
 * TODO dependency injection
 * </p>
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface InfrastructureObject extends Documentation {

}
