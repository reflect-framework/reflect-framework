package nth.introspect.container.exception;

import nth.introspect.Introspect;

public class ClassHasNoUsableConstructorException extends
		IntrospectContainerException {

	public ClassHasNoUsableConstructorException(Class<?> classToInstantiate) {
		super(createMessage(classToInstantiate));
	}

	private static String createMessage(Class<?> classToInstantiate) {
		StringBuffer message = new StringBuffer();
		message.append("Class ");
		message.append(classToInstantiate.getCanonicalName());
		message.append(" has no public constructor that can be used by the ");
		message.append(Introspect.class.getSimpleName());
		message.append(" Framework");
		return message.toString();
	}

	private static final long serialVersionUID = -8120551659126072243L;

	
}
