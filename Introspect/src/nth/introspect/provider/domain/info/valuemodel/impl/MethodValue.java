package nth.introspect.provider.domain.info.valuemodel.impl;

import java.lang.reflect.Method;

import nth.introspect.valuemodel.IntrospectedValueModelReadOnly;

public class MethodValue extends IntrospectedValueModelReadOnly {

	private final Method method;

	public MethodValue(Method method) {
		this.method = method;
		if (method.getParameterTypes().length > 0) {
			throw new IllegalArgumentException("The method " + method.getClass().getCanonicalName() + "." + method.getName() + " may not have any arguments to be used in a " + getClass().getName());
		}
	}

	public Object getValue(Object serviceOrDomainObject) {
		Object[] arguments = new Object[0];
		try {
			return method.invoke(serviceOrDomainObject, arguments);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return method.getDeclaringClass().getCanonicalName() + "." + method.getName();
	}

	@Override
	public Class<?> getValueType() {
		return method.getReturnType();
	}

	@Override
	public boolean canGetValue() {
		return true;
	}

	// public Method getMethod() {
	// return method;
	// }

}
