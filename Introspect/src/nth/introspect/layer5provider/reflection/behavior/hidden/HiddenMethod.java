package nth.introspect.layer5provider.reflection.behavior.hidden;

import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectActionMethod;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * You can hide a {@link DomainObjectProperty} or
 * {@link DomainObjectActionMethod} depending on the {@link DomainObject} state
 * (the value of its properties). If you have a {@link Hidden} annotation and a
 * hidden method, both need to be true in order to hide the
 * {@link DomainObjectProperty} or {@link ActionMethod}.
 * </p>
 * <p>
 * Syntax: public boolean hidden&lt;property name or actionMethodName&gt;()
 * </p>
 * <p>
 * ReturnValue: a boolean that is true if the {@link DomainObjectProperty} or
 * {@link ActionMethod} needs to be hidden
 * </p>
 * 
 * @author nilsth
 *
 */
public class HiddenMethod extends BehavioralMethod {

	@Override
	public String getMethodNameSuffix() {
		return "Hidden";
	}

	@Override
	public Class<?> getReturnType() {
		return boolean.class;
	}
	// TODO
}
