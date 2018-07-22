package nth.reflect.fw.container.exception;

public class ReflectContainerException  extends RuntimeException {

	private static final long serialVersionUID = 7500700346364627419L;

	public ReflectContainerException (String message) {
		super(message);
	}
	
	public ReflectContainerException (String message, Exception exception) {
		super(message, exception);
	}
}
