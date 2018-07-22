package nth.reflect.fw.ui.commandline.domain.command;

public class ReflectCommandLineException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7693247054678345189L;

	public ReflectCommandLineException(String message) {
		super(message);
	}
	
	public ReflectCommandLineException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
