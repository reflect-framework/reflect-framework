package nth.introspect.layer5provider.reflection.info.actionmethod;

public class NoParameterFactoryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8336535857046630049L;

	public NoParameterFactoryException(String canonicalActionMethodName) {
		super(createMessage(canonicalActionMethodName));
	}

	private static String createMessage(String canonicalActionMethodName) {
		StringBuilder message=new StringBuilder();
		message.append(canonicalActionMethodName);
		message.append(" does not have a ParameterFactory annotation or method!");
		return message.toString();
	}
}
