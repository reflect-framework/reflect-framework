package nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.reflection.behavior.BehaviorMethodInvokeException;

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
			throw new BehaviorMethodInvokeException(parameterFactoryMethod, e);
		}
	}

}
