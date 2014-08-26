package nth.introspect.container.exception;

public class DependencyLoopException extends IntrospectContainerException {

	private static final long serialVersionUID = 3793311422386005548L;

	public DependencyLoopException(Class<?> ownerClass, Class<?> constructorParameterClass) {
		super(createMessage(ownerClass, constructorParameterClass));
	}

	private static String createMessage(Class<?> ownerClass, Class<?> constructorParameterClass) {
		StringBuilder message = new StringBuilder();
		message.append("Detected an dependency loop in class: ");
		message.append(ownerClass.getCanonicalName());
		message.append(" for constructor parameter of type: ");
		message.append(constructorParameterClass.getCanonicalName());
		return message.toString();
	}

}
