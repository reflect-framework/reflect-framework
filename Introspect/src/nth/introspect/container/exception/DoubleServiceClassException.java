package nth.introspect.container.exception;


public class DoubleServiceClassException extends IntrospectContainerException {

	private static final long serialVersionUID = -8543668683126929170L;

	public DoubleServiceClassException(Class<?> doubleClass) {
		super(createMessage(doubleClass ));
	}

	private static String createMessage(Class<?> doubleClass) {
		StringBuilder message=new StringBuilder();
		message.append("Service class: ");
		message.append(doubleClass.getCanonicalName());
		message.append(" is declared as both a 'front end service class' and a 'back end service class'. This is a breach of the 'Separation of Concerns' pattern.");
		return message.toString();
	}

}
