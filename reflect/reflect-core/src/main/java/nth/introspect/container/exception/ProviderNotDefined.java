package nth.introspect.container.exception;

import nth.introspect.IntrospectApplication;

public class ProviderNotDefined extends IntrospectContainerException {

	private static final long serialVersionUID = -5905046492730652717L;
	
	public ProviderNotDefined(IntrospectApplication application, Class<?> providerType) {
		super(createMessage(application, providerType));
	}

	private static String createMessage(IntrospectApplication application,
			Class<?> providerType) {
		StringBuilder message=new StringBuilder();
		message.append("The ");
		message.append(providerType.getCanonicalName());
		message.append(" is not defined in ");
		message.append(application.getClass().getCanonicalName());
		return message.toString();
	}

}
