package nth.introspect.container.exception;

public class IntrospectContainerException  extends Exception{

	public IntrospectContainerException (String message) {
		super(message);
	}
	
	public IntrospectContainerException (String message, Exception exception) {
		super(message, exception);
	}
}
