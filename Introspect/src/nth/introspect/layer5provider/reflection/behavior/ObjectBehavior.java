package nth.introspect.layer5provider.reflection.behavior;

import nth.introspect.IntrospectApplication;
import nth.introspect.documentation.Documentation;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayNameModelFactory;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
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
 * 
 * <h2>Title</h2>
 * <p>
 * {@insert TitleModelFactory}
 * </p>
 * 
 * <h2>Description</h2>
 * <p>
 * {@insert DescriptionModelFactory}
 * </p>
 * 
 * <h2>Icon</h2>
 * <p>
 * {@insert IconModelFactory}
 * </p>
 * 
 * <h2>Hidden</h2>
 * <p>
 * {@insert HiddenModelFactory}
 * </p>
 * 
 * <h2>Disabled</h2>
 * <p>
 * {@insert DisabledModelFactory}
 * </p>
 * 
 * <h2>Order</h2>
 * <p>
 * {@insert Order}
 * </p>
 * 
 * <h2>Format</h2>
 * <p>
 * {@insert Format}
 * </p>
 * 
 *  <h2>Field Mode</h2>
 * <p>
 * {@insert FieldMode}
 * </p>
 * 
 * <h2>Execution Mode</h2>
 * <p>
 * {@insert ExecutionMode}
 * </p>
 * 
 * 
 * @author nilsth
 *
 */
public interface ObjectBehavior extends Documentation {

}
