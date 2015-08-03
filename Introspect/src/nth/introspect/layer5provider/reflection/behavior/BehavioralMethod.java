package nth.introspect.layer5provider.reflection.behavior;

import nth.introspect.documentation.Documentation;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

/**
 * <p>
 * {@link BehavioralMethod}s are recognized by the {@link IntrospectFramework}
 * and dynamically define how {@link DomainObject}s, {@link DomainObjectProperty}s or
 * {@link ActionMethod}s behave (how they act and how they displayed). Dynamic
 * means that the behavior can change over time, depending on the state of the
 * object (property values).
 * </p>
 * 
 * <h3>Behavioral Method Convention</h3>
 * <ul>
 * <li>Syntax: &ltbehaviourName&gt;&ltmemberName&gt;<br>
 * &ltbehaviourName&gt;= A behavior like Icon, Hidden, Disabled, etc)<br>
 * &ltmemberName&gt;= can be a {@link Class}Name, {@link DomainObjectProperty}
 * Name, or a {@link ActionMethod}Name</li>
 * <li>{@link BehavioralMethod}s do NOT have any parameters</li>
 * <li>{@link BehavioralMethod}s ALWAYS return a value (see
 * {@link BehavioralMethod#returnType()} of the different implementations</li>
 * <li>{@link BehavioralMethod}s are public</li>
 * <li>{@link BehavioralMethod}s are not static</li>
 * <li>{@link BehavioralMethod}s are normally located after the declaration of
 * the member:</li>
 * <ul>
 * <li>{@link BehavioralMethod}s for classes: are located after the constructors
 * </li>
 * <li>{@link BehavioralMethod}s for {@link DomainObjectProperty}: are located
 * after the getter and setter methods of the properties</li>
 * <li>{@link BehavioralMethod}s for {@link ActionMethod}: are located after the
 * {@link ActionMethod}</li>
 * </ul>
 * </ul>
 * 
 * @author nilsth
 *
 */
public interface BehavioralMethod extends Documentation {

	public String methodNamePrefix();

	public Class<?> returnType();
}
