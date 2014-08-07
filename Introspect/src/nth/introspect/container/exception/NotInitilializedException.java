package nth.introspect.container.exception;

import nth.introspect.Introspect;

public class NotInitilializedException extends RuntimeException {

	private static final long serialVersionUID = -8814241184500254435L;

	public NotInitilializedException() {
		super(createMessage());
	}

	private static String createMessage() {
		StringBuilder message = new StringBuilder();
		message.append("First call the ");
		message.append(Introspect.class.getCanonicalName());
		message.append(".init before calling this method.");
		return message.toString();
	}
}
