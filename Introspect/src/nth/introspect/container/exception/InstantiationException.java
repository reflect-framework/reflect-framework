package nth.introspect.container.exception;

import java.lang.reflect.Constructor;

import nth.introspect.container.IntrospectContainer;

public class InstantiationException extends IntrospectContainerException {

	public InstantiationException(IntrospectContainer introspectContainer, Constructor<?> constructor, Exception exception) {
		super(createMessage(introspectContainer, constructor), exception);
	}

	private static String createMessage(IntrospectContainer introspectContainer, Constructor<?> constructor) {
		StringBuffer message=new StringBuffer();
		message.append(introspectContainer.getName());
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
