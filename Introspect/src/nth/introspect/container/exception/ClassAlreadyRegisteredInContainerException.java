package nth.introspect.container.exception;

import nth.introspect.container.DependencyInjectionContainer;

public class ClassAlreadyRegisteredInContainerException extends
		IntrospectContainerException {

	public ClassAlreadyRegisteredInContainerException(DependencyInjectionContainer introspectContainer, Class<?> type) {
		super(createMessage(introspectContainer, type));
	}

	private static String createMessage(DependencyInjectionContainer introspectContainer, Class<?> type) {
		StringBuilder message=new StringBuilder();
		message.append(DependencyInjectionContainer.class.getSimpleName());
		message.append(": ");
		message.append(introspectContainer.getName());
		message.append(" Cannot register class: ");
		message.append(type.getCanonicalName());
		message.append(" because it was already rigestered.");
		return message.toString();
	}

	private static final long serialVersionUID = -2198987457083284820L;

}
