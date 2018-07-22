package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.Method;

import nth.reflect.fw.generic.exception.ReflectException;

public class InvalidActionMethodException extends ReflectException {

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
