package nth.introspect.container.exception;


public class UndeclaredInjectionTypeException extends IntrospectContainerException {

	public UndeclaredInjectionTypeException(Class<?> applicationClass,
			Class<?> ownerClass, Class<?> injectionType) {
		super(createMessage(applicationClass, ownerClass, injectionType));
	}

	private static String createMessage(Class<?> applicationClass,
			Class<?> ownerClass, Class<?> injectionType) {
		StringBuilder message = new StringBuilder();
		message.append("The ");
		message.append(ownerClass.getCanonicalName());
		message.append(" class contains an injection field of type ");
		message.append(injectionType.getCanonicalName());
		message.append(" which is not declared in ");
		message.append(applicationClass.getCanonicalName());
		return message.toString();
	}

}
