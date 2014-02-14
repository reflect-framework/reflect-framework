package nth.introspect.ui.commandline.domain.command;

public class IntrospectCommandLineException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7693247054678345189L;

	public IntrospectCommandLineException(String message) {
		super(message);
	}
	
	public IntrospectCommandLineException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
