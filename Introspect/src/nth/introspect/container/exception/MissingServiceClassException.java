package nth.introspect.container.exception;

import nth.introspect.application.IntrospectApplication;


public class MissingServiceClassException extends IntrospectContainerException {

	private static final long serialVersionUID = 8927372836123865304L;

	public MissingServiceClassException(IntrospectApplication application) {
		super(createMessage(application));
	}

	private static String createMessage(IntrospectApplication application) {
		StringBuilder message = new StringBuilder();
		message.append("The ");
		message.append(application.getClass().getCanonicalName());
		message.append(".getServiceClasses method must return at least one service object class.");
		return message.toString();
	}
}
