package nth.introspect.layer5provider.reflection.behavior;

import nth.introspect.IntrospectApplication;
import nth.introspect.documentation.Documentation;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayNameModelFactory;
import nth.introspect.layer5provider.validation.ValidationProvider;

/**
 * The {@link IntrospectApplication}, {@link ServiceObject}s and
 * {@link DomainObject}s can have behavior that defines how the objects act or
 * how they are displayed. Behavior can be defined with:
 * <ul>
 * <li>methods that are recognized by the {@link IntrospectFramework}</li>
 * <li>annotations that are recognized by the {@link IntrospectFramework}</li>
 * </ul>
 * TODO RECOGNIZED METHODS (see naked objects doc) TODO RECOGNIZED ANNOTATIONS
 * TODO verify if we missed a chapter by inspecting ClassInfo and MethodInfo and
 * PropertyInfo
 * 
 * <h2>Display Name</h2>
 * <p>
 * {@insert DisplayNameModelFactory}
 * </p>
 * <h2>Title</h2> 
 * <p>
 * {@insert TitleModelFactory}
 * </p>
 * <h2>Description</h2> 
 * <p>
 * {@insert DescriptionModelFactory}
 * </p>
 * <h2>Icon</h2> 
 * <p>
 * {@insert IconModelFactory}
 * </p>
 * <h3>Visible</h3> (TODO description what this does) (TODO annotation) (TODO
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
