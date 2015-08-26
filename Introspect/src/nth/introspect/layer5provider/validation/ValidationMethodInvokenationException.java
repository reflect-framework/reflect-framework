package nth.introspect.layer5provider.validation;

import java.lang.reflect.Method;

public class ValidationMethodInvokenationException extends RuntimeException {

	private static final long serialVersionUID = -9046908889355740168L;

	public ValidationMethodInvokenationException(Method validationMethod, Throwable throwable) {
		super(createMessage(validationMethod), throwable);
	}

	private static String createMessage(Method validationMethod) {
		StringBuilder message=new StringBuilder();
		message.append("Error invoking validation method: ");
		message.append(validationMethod.getDeclaringClass().getCanonicalName());
		message.append(".");
		message.append(validationMethod.getName());
		return message.toString();
	}

}
