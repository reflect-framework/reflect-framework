package nth.reflect.fw.layer5provider.reflection.behavior.disabled;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * You can disable a {@link DomainObjectProperty} or
 * {@link DomainObjectActionMethod} depending on the {@link DomainObject} state
 * (the value of its properties). 
 * </p>
 * <p>
 * If you have a {@link Disabled} annotation and a
 * disabled method, only one needs to be true in order to disable the
 * {@link DomainObjectProperty} or {@link ActionMethod}.
 * </p>
 * <p>
 * Syntax: public boolean &lt;property name or actionMethodName&gt;Disabled()
 * </p>
 * <p>
 * ReturnValue: a boolean that is true if the {@link DomainObjectProperty} or
 * {@link ActionMethod} needs to be disabled
 * </p>
 * 
 * @author nilsth
 *
 */
public class DisabledMethod extends BehavioralMethod{

	@Override
	public String getBehavioralName() {
		return "Disabled";
	}

	@Override
	public Class<?> getReturnType() {
		return boolean.class;
	}

}
