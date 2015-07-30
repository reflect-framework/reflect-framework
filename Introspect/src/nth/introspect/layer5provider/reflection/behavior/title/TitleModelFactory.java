package nth.introspect.layer5provider.reflection.behavior.title;

import nth.introspect.generic.titlebuilder.TitleBuilder;
import nth.introspect.layer3domain.DomainObject;

/**
 * <p>
 * {@link DomainObject}s that have identity (entities) need to have a dynamic
 * title that help users to distinguish objects of the same type (e.g. Type
 * customer versus “John Doe”). This title should exist of all the properties
 * that identify the object. The title is therefore dynamic: it changes when the
 * value of these properties change.
 * </p>
 * 
 * <p>
 * In example: The title of a customer could be a customer number, followed by
 * the given name, followed by the family name. If the customer changes it’s
 * name, than so does the title (but not its identity)
 * </p>
 * 
 * <h3>Title Default</h3>
 * <p>
 * {@insert TitleDefaultModel}
 * </p>
 * 
 * <h3>Title (toString) method</h3>
 * <p>
 * {@insert TitleMethodModel}
 * </p>
 * 
 *<h3>Title builder</h3>
 * <p>
 * {@insert TitleBuilder}
 * </p>
  
 * @author nilsth
 *
 */
public class TitleModelFactory {

}
