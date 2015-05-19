package nth.introspect.documentation;

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
 * <h3>Naming</h3>
 * Domain objects names are nouns, such as customer, product and order. They basically describe the things that are important in your application.
 * 
 * <h3>Construction</h3>
 * The principle of “naked objects” is that any <a href="http://en.wikipedia.org/wiki/Plain_Old_Java_Object">'Plain Old Java Object' (POJO)</a> can function as a domain object. In other words: a domain class does not have to inherit from any special class, nor implement any particular interface, nor have any specific attributes.
 * 
 * <h3>Presentation</h3>
 * An Introspect Interface can display domain objects in 3 ways:<br>
 * <ul>
 * <li>Domain object as form:<br><image src="ObjectAsForm.png"></li>
 * <li>Domain object as a field in a form:<br><image src="ObjectAsField.png"></li>
 * <li>Domain object as a row in a table:<br><image src="ObjectAsTable.png"></li>
 * </ul>
 * <br>
 * <h2>Properties</h2>
 * {@insert DomainInfoProvider}
 * 
 * <h3>Property types</h3>
 * TODO
 *<h2>Methods (Actions)</h2> 
 * TODO
 * @author Nils ten Hoeve
 * @see DomainLayer
 * 
 */

public interface DomainObject  extends Documentation{

}
