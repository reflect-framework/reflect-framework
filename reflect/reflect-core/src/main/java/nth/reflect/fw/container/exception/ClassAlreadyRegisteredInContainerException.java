package nth.reflect.fw.container.exception;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class ClassAlreadyRegisteredInContainerException extends
		ReflectContainerException {

	public ClassAlreadyRegisteredInContainerException(DependencyInjectionContainer container, Class<?> type) {
		super(createMessage(container, type));
	}

	private static String createMessage(DependencyInjectionContainer container, Class<?> type) {
		StringBuilder message=new StringBuilder();
		message.append(DependencyInjectionContainer.class.getSimpleName());
		message.append(": ");
		message.append(container.getName());
		message.append(" Cannot register class: ");
		message.append(type.getCanonicalName());
		message.append(" because it was already rigestered.");
		return message.toString();
	}

	private static final long serialVersionUID = -2198987457083284820L;

}
