package nth.introspect.layer5provider.reflection.behavior.hidden;

import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

/**
 * A {@link DomainObjectProperty} or {@link ActionMethod} is visible on
 * the user interface by default,but in some cases you may want to hide them,
 * e.g.:
 * <ul>
 * <li>Because not all information needs to be displayed in the user interface
 * (e.g. a database id key or version number for optimistic locking)</li>
 * <li>Because you want to limit the number of visible properties in a table
 * (e.g. to limit the number of columns in a table to prevent the table to
 * become cluttered with to much information)</li>
 * <li>Depending on the state of a {@link DomainObject} you do not want to display
 * Irrelevant information</li>
 * <li>Depending on the state of a domain object you do not want a user to
 * activate an action method (e.g. hide an {@link ActionMethod} “submit” once the
 * domainObject already is submitted)</li>
 * <li>Because a user is not authorized to see a property value</li>
 * <li>Because a user is not authorized to activate an {@link ActionMethod}</li>
 * </ul>
 * 
 * <h3>Hidden Annotation</h3>
 * <p>
 * {@insert Hidden}
 * </p>
 * 
 * <h3>Hidden Annotation</h3>
 * <p>
 * {@insert HiddenMethodModel}
 * </p>
 * 
 * 
 * @author nilsth
 *
 */
public class HiddenModelFactory {
	// TODO
}
