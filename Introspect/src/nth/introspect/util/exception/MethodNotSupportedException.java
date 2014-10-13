package nth.introspect.util.exception;

public class MethodNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = -8216136994547733822L;

	public MethodNotSupportedException(){};
	
	public MethodNotSupportedException(String message) {
		super(message);
	}
	
}
