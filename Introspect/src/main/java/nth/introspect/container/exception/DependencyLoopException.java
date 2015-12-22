package nth.introspect.container.exception;

public class DependencyLoopException extends IntrospectContainerException {

	private static final long serialVersionUID = 1332521278671440853L;

	public DependencyLoopException(Class<?> type1, Class<?> type2) {
		super(createMessage(type1, type2));
	}

	private static String createMessage(Class<?> type1, Class<?> type2) {
		StringBuilder message = new StringBuilder();
		message.append("Dependency loop with class: ");
		message.append(type1.getCanonicalName());
		message.append(" and class: ");
		message.append(type2.getCanonicalName());
		return message.toString();
	}

}
