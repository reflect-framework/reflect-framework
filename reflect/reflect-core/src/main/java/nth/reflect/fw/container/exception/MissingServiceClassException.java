package nth.reflect.fw.container.exception;

import nth.reflect.fw.ReflectApplication;


public class MissingServiceClassException extends ReflectContainerException {

	private static final long serialVersionUID = 8927372836123865304L;

	public MissingServiceClassException(ReflectApplication application) {
		super(createMessage(application));
	}

	private static String createMessage(ReflectApplication application) {
		StringBuilder message = new StringBuilder();
		message.append("The ");
		message.append(application.getClass().getCanonicalName());
		message.append(".getServiceClasses method must return at least one service object class.");
		return message.toString();
	}
}
