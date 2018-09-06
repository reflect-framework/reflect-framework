package nth.reflect.fw.generic.exception;

public class ReflectException extends RuntimeException {

	private static final long serialVersionUID = 4807012565876694499L;

	public ReflectException(Throwable cause) {
		super(cause);
	}
	
	public ReflectException(String message) {
		super(message);
	}
	
	public ReflectException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ReflectException() {
		super();
	}

	
}
