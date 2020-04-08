package nth.reflect.fw.layer5provider.reflection.behavior;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;

/**
 * The {@link ReflectApplication}, {@link ServiceObject}s and
 * {@link DomainObject}s can have behavior that defines how the objects act or
 * how they are displayed. Behavior can be defined with:
 * <ul>
 * <li>{@link BehavioralAnnotation}s</li>
 * <li>{@link BehavioralMethod}s</li>
 * </ul>
 * 
 * <h3>Behavioral Annotations</h3> {@insert BehavioralAnnotation}
 * 
 * <h3>Behavioral Methods</h3> {@insert BehavioralMethod}
 * 
 * <h2>Display Name</h2> {@insert TranslatedDisplayName}
 * 
 * <h2>Description</h2> {@insert TranslatedDescription}
 * 
 * <h2>Title</h2> {@insert TitleModel}
 * 
 * <h2>FontIcon</h2>{@insert FontIconModelFactory}
 * 
 * <h2>ApplicationIcon</h2> {@insert ApplicationIconModelFactory}
 * 
 * <h2>Hidden</h2> {@insert HiddenModelFactory}
 * 
 * <h2>Disabled</h2> {@insert DisabledModelFactory}
 * 
 * <h2>Order</h2> {@insert Order}
 * 
 * <h2>Format</h2>{@insert Format}
 * 
 * <h2>Text Field Mode</h2> {@insert TextFieldMode}
 * 
 * <h2>Date Time Field Mode</h2> {@insert DateTimeFieldMode}
 * 
 * <h2>Execution Mode</h2>{@insert ExecutionMode}
 * 
 * <h2>Read Only Action Method</h2>{@insert ReadOnlyActionMethod}
 * 
 * <h2>Execution Mode</h2> {@insert ExecutionMode}
 * 
 * <h2>Parameter Factory</h2>{@insert ParameterFactoryModelFactory}
 * 
 * <h2>Validation</h2> {@insert ValidationMethodFactory}
 * 
 * <h2>Options</h2> {@insert OptionsModelFactory}
 * 
 * 
 * @author nilsth
 *
 */
public interface ObjectBehavior extends ReflectDocumentationInterface {

}
