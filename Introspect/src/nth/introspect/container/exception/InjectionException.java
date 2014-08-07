package nth.introspect.container.exception;

import java.lang.reflect.Field;

public class InjectionException extends IntrospectContainerException {

	private static final long seriealVersionUID = 3495600725494599247L;

	public InjectionException(Field field, Exception exception) {
		super(createMessage(field), exception);
	}

	private static String createMessage(Field field) {
		StringBuilder message = new StringBuilder();
		message.append("Dependency Injection for field ");
		message.append(field.getDeclaringClass().getCanonicalName());
		message.append(".");
		message.append(field.getName());
		message.append(" failed.");
		return message.toString();
	}

}
