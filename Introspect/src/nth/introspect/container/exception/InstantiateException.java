package nth.introspect.container.exception;


public class InstantiateException extends IntrospectContainerException {

	private static final long serialVersionUID = -7576863118736209738L;

	public InstantiateException(Class<?> type, Exception exception) {
		super(createMessage(type), exception);
	}

	private static String createMessage(Class<?> type) {
		StringBuilder message = new StringBuilder();
		message.append("Could not create an object from class ");
		message.append(type.getCanonicalName());
		return message.toString();
	}

}
