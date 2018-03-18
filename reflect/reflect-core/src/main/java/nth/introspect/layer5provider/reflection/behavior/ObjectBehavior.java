package nth.introspect.layer5provider.reflection.behavior;

import nth.introspect.IntrospectApplication;
import nth.introspect.documentation.Documentation;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;

/**
 * The {@link IntrospectApplication}, {@link ServiceObject}s and
 * {@link DomainObject}s can have behavior that defines how the objects act or
 * how they are displayed. Behavior can be defined with:
 * <ul>
 * <li>{@link BehavioralAnnotation}s</li>
 * <li>{@link BehavioralMethod}s</li>
 * </ul>
 * 
 * <h3>Behavioral Annotations</h3>
 * <p>
 * {@insert BehavioralAnnotation}
 * </p>
 * 
 * <h3>Behavioral Methods</h3>
 * <p>
 * {@insert BehavioralMethod}
 * </p>
 * 
 * <h2>Display Name</h2>
 * <p>
 * {@insert DisplayNameModel}
 * </p>
 * 
 * <h2>Description</h2>
 * <p>
 * {@insert DescriptionModel}
 * </p>
 * 
 * <h2>Title</h2>
 * <p>
 * {@insert TitleModel}
 * </p>
 * 
 * <h2>Icon</h2>
 * <p>
 * {@insert IconModel}
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
 * <h2>Field Mode</h2>
 * <p>
 * {@insert TextFieldMode}
 * </p>
 * 
 * <h2>Execution Mode</h2>
 * <p>
 * {@insert ExecutionMode}
 * </p>
 * 
 * <h2>Parameter Factory</h2>
 * <p>
 * {@insert ParameterFactoryModelFactory}
 * </p>
 * 
 * <h2>Validation</h2>
 * <p>
 * {@insert ValidationMethodFactory}
 * </p>
 * 
 * <h2>Service Object Children<h2>
 * <p>
 * {@insert ServiceObjectChildrenModel}
 * </p>
 * @author nilsth
 *
 */
public interface ObjectBehavior extends Documentation {

}
