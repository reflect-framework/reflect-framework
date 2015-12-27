package nth.introspect.layer5provider.reflection.behavior;

import java.lang.reflect.Method;

public class BehaviorMethodInvokeException extends RuntimeException {

	private static final long serialVersionUID = -8447500654942721817L;

	public BehaviorMethodInvokeException(Method behavioralMethod) {
		super(createMessage(behavioralMethod));
	}

	private static String createMessage(Method behavioralMethod) {
		StringBuilder message=new StringBuilder();
		message.append("Error invoking behavioral method: ");
		message.append(behavioralMethod.getDeclaringClass().getCanonicalName());
		message.append(".");
		message.append(behavioralMethod.getName());
		return message.toString();
	}

	
}
