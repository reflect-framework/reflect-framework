package nth.reflect.fw.container.exception;

import java.lang.reflect.Constructor;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class InstantiationException extends ReflectContainerException {

	public InstantiationException(DependencyInjectionContainer container, Constructor<?> constructor, Exception exception) {
		super(createMessage(container, constructor), exception);
	}

	private static String createMessage(DependencyInjectionContainer container, Constructor<?> constructor) {
		StringBuffer message=new StringBuffer();
		message.append(container.getName());
		message.append(": Could not create a new instance for ");
		message.append(constructor.getDeclaringClass().getCanonicalName());
		message.append("(");
		Class<?>[] constructorParameterClasses = constructor.getParameterTypes();
		boolean isFirst=true;
		for (Class<?> constructorParamaterClass:constructorParameterClasses) {
			if (!isFirst) {
				message.append(", ");
			}
			message.append(constructorParamaterClass.getCanonicalName());
		}
		message.append(")");
		return message.toString();
	}

	private static final long serialVersionUID = -7371949978849872639L;

}
