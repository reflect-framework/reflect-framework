package nth.reflect.fw.layer5provider.reflection.behavior.hidden;

import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectActionMethod;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * You can hide a {@link DomainObjectProperty} or
 * {@link DomainObjectActionMethod} depending on the {@link DomainObject} state
 * (the value of its properties), with an  ...Hidden method. If you have a {@link Hidden} annotation and a
 * ...Hidden method, both need to be true in order to hide the
 * {@link DomainObjectProperty} or {@link ActionMethod}. 
 * </p>
 * <p>
 * Note that you can not change the visibility with a ...Hidden method for {@link DomainObjectProperty} in a {@link TableTab}!
 * </p>
 * <p>
 * Syntax: public boolean &lt;property name or actionMethodName&gtHidden;()
 * </p>
 * <p>
 * ReturnValue: a boolean that is true if the {@link DomainObjectProperty} or
 * {@link ActionMethod} needs to be hidden
 * </p>
 * 
 * @author nilsth
 *
 */
public class HiddenMethod extends BehavioralMethod{


	@Override
	public String getBehavioralName() {
		return "Hidden";
	}

	@Override
	public Class<?> getReturnType() {
		return boolean.class;
	}
}
