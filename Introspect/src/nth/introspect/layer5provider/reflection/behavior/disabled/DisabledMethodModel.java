package nth.introspect.layer5provider.reflection.behavior.disabled;

import java.lang.reflect.Method;

import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectActionMethod;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.behavior.BehaviorMethodInvokeException;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * Model that returns the value of the {@link DisabledMethod}
 * 
 * @author nilsth
 *
 */
public class DisabledMethodModel  implements DisabledModel {

	private Method disabledMethod;

	public DisabledMethodModel(Method disabledMethod)  {
		this.disabledMethod = disabledMethod;
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
