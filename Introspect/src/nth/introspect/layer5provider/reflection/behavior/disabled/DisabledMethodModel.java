package nth.introspect.layer5provider.reflection.behavior.disabled;

import java.lang.reflect.Method;

import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectActionMethod;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.behavior.BehaviorMethodInvokeException;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * You can disable a {@link DomainObjectProperty} or
 * {@link DomainObjectActionMethod} depending on the {@link DomainObject} state
 * (the value of its properties). If you have a {@link Disabled} annotation and a
 * disabled method, both need to be true in order to hide the
 * {@link DomainObjectProperty} or {@link ActionMethod}.
 * </p>
 * <p>
 * Syntax: public boolean disabled&lt;property name or actionMethodName&gt;()
 * </p>
 * <p>
 * ReturnValue: a boolean that is true if the {@link DomainObjectProperty} or
 * {@link ActionMethod} needs to be disabled
 * </p>
 * 
 * @author nilsth
 *
 */
public class DisabledMethodModel extends BehavioralMethod implements DisabledModel {

	private Method disabledMethod;

	public DisabledMethodModel(Method disabledMethod)  {
		this.disabledMethod = disabledMethod;
	}
	

	@Override
	public String getBehavioralName() {
		return "Disabled";
	}

	@Override
	public Class<?> getReturnType() {
		return boolean.class;
	}

	@Override
	public boolean isDisabled(Object obj) {
		Object[] arguments = new Object[0];
		try {
			return (boolean) disabledMethod.invoke(obj, arguments);
		} catch (Exception e) {
			throw new BehaviorMethodInvokeException(disabledMethod);
		}
	}

	


}
