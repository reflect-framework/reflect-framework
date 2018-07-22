package nth.reflect.fw.container.exception;

public class ReflectContainerInitializationException extends RuntimeException {
 
	private static final long serialVersionUID = -5257751247046976112L;

	public ReflectContainerInitializationException(Exception exception) {
		super("The Reflect Framework failed to initialize.", exception);
	}
}
