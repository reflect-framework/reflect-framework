package nth.introspect.layer5provider.reflection.behavior.parameterfactory;

import java.lang.reflect.Method;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.reflection.behavior.BehaviorMethodInvokeException;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * Model that returns the value of a {@link ParameterFactoryMethod}
 * 
 * @author nilsth
 *
 */
public class ParameterFactoryMethodModel implements ParameterFactoryModel{

	private final Method parameterFactoryMethod;

	public ParameterFactoryMethodModel(Method parameterFactoryMethod) {
		this.parameterFactoryMethod = parameterFactoryMethod;
	}
	
	@Override
	public Object createNewMethodParameter(Object methodOwner) {
		Object[] arguments = new Object[0];
		try {
			Object domainObject = parameterFactoryMethod.invoke(methodOwner, arguments);
			return domainObject;
		} catch (Exception e) {
			throw new BehaviorMethodInvokeException(parameterFactoryMethod);
		}
	}

}
