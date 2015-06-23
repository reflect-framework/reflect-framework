package nth.introspect.documentation;

import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.validation.ValidationProvider;

/**
 * This section is concerned with adding to, or modifying, the behavior of
 * domain objects and service objects, in order to build a richer application.
 * 
 * Behavior can be added with:
 * <ul>
 * <li>methods that are recognized bin the Introspect framework</li>
 * <li>methods that are recognized book the Introspect framework</li>
 * </ul>
 * TO-DO RECOGNIZED METHODS (see naked objects doc)
 * 
 * TO-DO RECOGNIZED ANNOTATIONS
 * 
 * (TODO verify if we missed a chapter by inspecting ClassInfo and MethodInfo
 * and PropertyInfo)
 * 
 * <h3>Title</h3> The Application object, domain objects and service objects
 * have a title which is displayed in the user interface. This title defaults to
 * the class name, but you can override the title.
 * 
 * (TODO title annotation + example)
 * 
 * (TODO title method + example)
 * 
 * (TODO toString method + example)
 * 
 * (TODO titlebuilder + example)
 * <p>
 * For domain objects it makes sence to override the title so that the domain
 * object can be better identified. For example: the default title for a
 * customer object will be “Customer”. It makes since to override the title with
 * the name of the customer, so that the customer object can be better
 * identified in the user interface
 * </p>
 * <h3>Description</h3> 
 * (TODO description what this does) (TODO annotation) (TODO
 * method)
 * 
 * <h3>Icon</h3> (TODO description what this does) (TODO annotation) (TODO
 * method)
 * 
 * <h3>Visible</h3> 
 * (TODO description what this does) (TODO annotation) (TODO
 * method)
 * 
 * 
 * <h3>Visible In Form</h3> (TODO specific on form)Public properties and methods
 * are visible by default but they can be hidden.
 * 
 * Methods that are not public are not visible or access able. (TODO visible
 * annotation) (TODO visible method)
 * 
 * <h3>Visible In Table</h3> (TODO specific on table)Public properties and
 * methods are visible by default but they can be hidden. (TODO properties of
 * type collection are not displayed in a table)
 * 
 * Methods that are not public are not visible or access able. (TODO visible
 * annotation) (TODO visible method) (TODO refer to Authorization Provider)
 * 
 * <h3>Enabled</h3> Properties and methods are enabled by default but they can
 * be disabled. • Public properties that are disabled are visible to the user
 * but can’t be edited. • Public methods that are disabled are visible to the
 * user but can’t be executed. (TODO annotation) (TODO method) (TODO refer to
 * Authorization Provider)
 * 
 * <h3>Order in Form</h3> (TODO description what this does) (TODO annotation)
 * (TODO method)
 * 
 * <h3>Order in Table</h3> (TODO description what this does) (TODO annotation)
 * (TODO method) <h3>Method Execution Mode</h3> (TODO description what this
 * does) (TODO annotation) <h3>Parameter Factory</h3> (TODO description what
 * this does) (TODO annotation) (TODO method)
 * 
 * <h3>FieldMode</h3> (TODO description what this does) (TODO annotation) (TODO
 * method)
 * 
 * <h3>Validation</h3> Domain objects are validated before a method is executed.
 * There is a separate section on validation (see {@link ValidationProvider})
 * 
 * <h3>MultiLanguage</h3> Sometimes applications need to support multiple
 * languages. There is as separate section on multi language (see
 * {@link LanguageProvider})
 * 
 * TODO example
 * 
 * <h3>Formatting</h3> TODO explain + example
 * 
 * @author nilsth
 *
 */
public interface ObjectBehavior extends Documentation {

}
