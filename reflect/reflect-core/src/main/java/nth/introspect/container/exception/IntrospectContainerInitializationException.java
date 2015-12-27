package nth.introspect.container.exception;

public class IntrospectContainerInitializationException extends RuntimeException {
 
	private static final long serialVersionUID = -5257751247046976112L;

	public IntrospectContainerInitializationException(Exception exception) {
		super("The Introspect Framework failed to initialize.", exception);
	}
}
