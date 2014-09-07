package nth.introspect.definition;


//TODO synchronize with manual
/**
 * This interface is for documentation purposes only. {@link DomainObject}s do
 * not need to implement any interface or superclass.<br>
 * <br>
 * {@link DomainObject}s are placed in the {@link DomainLayer}<br>
 * {@link DomainObject}s represent entities; the nouns of the domain. If your
 * application domain is a sales application it’s likely that your domain model
 * contains domain objects such as: customers, products and orders.
 * 
 * {@link DomainObject}s already exist in an application that needs to be
 * re-written, or are created by the developer. They can be created from scratch
 * or generated from a schema (in example from a database schema, XML schema or
 * web service) web service)<br>
 * <br>
 * {@link DomainObject}s contain:
 * <ul>
 * <li><a
 * href="http://en.wikipedia.org/wiki/Property_(programming)">Properties</a>:
 * a special type of class members and are an intermediate between a <a
 * href="http://en.wikipedia.org/wiki/Field_(computer_science)">field</a> and a
 * method. Properties are read and sometimes written.
 * 
 * Introspect properties uses the same naming convention as <a
 * href="http://en.wikipedia.org/wiki/JavaBeans">JavaBeans</a></li>
 * <li>Methods that either represent menu items or define behavior</li>
 * <li>Annotations that define behavior</li>
 * </ul>
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface DomainObject {

}
