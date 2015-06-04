package nth.introspect.documentation;

import nth.introspect.provider.Provider;
import nth.introspect.provider.domain.info.DomainInfoProvider;


/**
 * Domain objects represent entities; the nouns of the domain. If your
 * application domain is a sales application it’s likely that your domain model
 * contains domain objects such as: customers, products and orders.
 * 
 * Domain objects are created by a developer or are reused from an existing application that needs to be
 * re-written. They can be created from scratch or generated from a schema (in example from a <a href="http://en.wikipedia.org/wiki/Database_schema">database schema</a>, <a href="http://nl.wikipedia.org/wiki/XML_Schema">XML schema</a> or
 * <a href="http://en.wikipedia.org/wiki/Web_Services_Description_Language">web service</a>)<br>
 * <br>
 * Domain objects contain:
 * <ul>
 * <li><a
 * href="http://en.wikipedia.org/wiki/Property_(programming)">Properties</a>:
 * that represent specific information within a {@link DomainObject}</li>
 * <li>Methods: that either represent {@link ActionMethod}s or define {@link ObjectBehavior}</li>
 * <li>Annotations: that define {@link ObjectBehavior}</li>
 * </ul>
 * 
 * <h3>Naming</h3>
 * Domain objects names are nouns, such as customer, product and order. They basically describe the things that are important in your application.
 * 
 * <h3>Construction</h3>
 * The principle of “naked objects” is that any <a href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">'Plain Old Java Object' (POJO)</a> can function as a domain object. In other words: a domain class does not have to inherit from any special class, nor implement any particular interface, nor have any specific attributes.
 * TODO domain objects are created by ....
 * TODO adding references to {@link InfrastructureObject}s or {@link Provider} objects
 
 * <h3>Presentation</h3>
 * An Introspect Interface can display domain objects in 3 ways:<br>
 * <ul>
 * <li>Domain object as form:<br><img src="ObjectAsForm.png"></li>
 * <li>Domain object as a field in a form:<br><img src="ObjectAsField.png"></li>
 * <li>Domain object as a row in a table:<br><img src="ObjectAsTable.png"></li>
 * </ul>
 * <br>
 * <h2>Properties</h2>
 * {@insert DomainObjectProperty}
 * 
 *<h2>Action Methods</h2> 
 * {@insert ActionMethod}
 * 
 * @author Nils ten Hoeve
 * @see DomainLayer
 * 
 */

public interface DomainObject  extends Documentation{

}
