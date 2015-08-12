package nth.introspect.layer5provider.reflection.behavior;

import java.lang.reflect.Method;

import nth.introspect.documentation.Documentation;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * {@link BehavioralMethod}s are recognized by the {@link IntrospectFramework}
 * and dynamically define how {@link DomainObject}s,
 * {@link DomainObjectProperty}s or {@link ActionMethod}s behave (how they act
 * and how they displayed). Dynamic means that the behavior can change over
 * time, depending on the state of the object (property values).
 * </p>
 * 
 * <h3>Behavioral Method Convention</h3>
 * <ul>
 * <li>Syntax: &ltmemberName&gt;&ltbehaviourName&gt;<br>
 * &ltmemberName&gt;= can be a {@link Class}Name, a {@link DomainObjectProperty}
 * Name or a {@link ActionMethod}Name<br>
 * &ltbehaviourName&gt;= A behavior like Icon, Hidden, Disabled, Validation, etc
 * </li>
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
public abstract class BehavioralMethod {


	public abstract String getBehavioralName();

	public abstract Class<?> getReturnType();

	public boolean isBehavioralMethod(Method method) {
		boolean nameEndsWithSuffix = method.getName().endsWith(
				getBehavioralName());
		boolean returnTypeMatches = getReturnType().isAssignableFrom(
				method.getReturnType());
		return nameEndsWithSuffix && returnTypeMatches;
	}
	

}