package nth.introspect.util.exception;


public class TypeNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 2460695691202447353L;

	public TypeNotSupportedException(Class<?> notSupportedType, Class<? > ownerClass) {
		super(createMessage(notSupportedType, ownerClass));
	}


	private static String createMessage(Class<?> notSupportedType, Class<? > ownerClass) {
		StringBuilder message=new StringBuilder();
		message.append("Type: ");
		message.append(notSupportedType.getCanonicalName());
		message.append(" is not supported in class: ");
		message.append(ownerClass.getCanonicalName());
		return message.toString();
	}

}
