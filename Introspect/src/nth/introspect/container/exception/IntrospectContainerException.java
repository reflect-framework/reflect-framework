package nth.introspect.container.exception;

public class IntrospectContainerException  extends RuntimeException {//TODO change to Exception!!!

	private static final long serialVersionUID = 7500700346364627419L;

	public IntrospectContainerException (String message) {
		super(message);
	}
	
	public IntrospectContainerException (String message, Exception exception) {
		super(message, exception);
	}
}
