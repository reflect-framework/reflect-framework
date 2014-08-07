package nth.introspect.container.exception;


public class MissingServiceClassException extends IntrospectContainerException {

	public MissingServiceClassException(Class<?> applicationClass) {
		super(createMessage(applicationClass));
	}

	private static String createMessage(Class<?> applicationClass) {
		StringBuilder message = new StringBuilder();
		message.append("The ");
		message.append(applicationClass.getCanonicalName());
		message.append(".getFrontEndServiceClasses method must return at least one service object class.");
		return message.toString();
	}
}
