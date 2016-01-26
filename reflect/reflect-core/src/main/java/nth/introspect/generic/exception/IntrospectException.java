package nth.introspect.generic.exception;

public class IntrospectException extends RuntimeException {

	private static final long serialVersionUID = 4807012565876694499L;

	public IntrospectException() {
		super();
	}
	
	public IntrospectException(String message) {
		super(message);
	}
	
	public IntrospectException(String message, Throwable throwable) {
		super(message, throwable);
	}

	
}
