package nth.introspect.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.Method;

import nth.introspect.generic.exception.IntrospectException;

public class InvalidActionMethodException extends IntrospectException {

	private static final long serialVersionUID = -7423921399017493023L;

	public InvalidActionMethodException(String message, Method method) {
		super(createMessage(message, method));
	}

	public InvalidActionMethodException(String message) {
		super(message);
	}

	private static String createMessage(String message, Method method) {
		String conicalMethodName = ActionMethodInfo.createCanonicalName(method);
		return String.format(message, conicalMethodName);
	}

}
