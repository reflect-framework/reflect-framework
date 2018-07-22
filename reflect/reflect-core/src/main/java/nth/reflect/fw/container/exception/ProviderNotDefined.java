package nth.reflect.fw.container.exception;

import nth.reflect.fw.ReflectApplication;

public class ProviderNotDefined extends ReflectContainerException {

	private static final long serialVersionUID = -5905046492730652717L;
	
	public ProviderNotDefined(ReflectApplication application, Class<?> providerType) {
		super(createMessage(application, providerType));
	}

	private static String createMessage(ReflectApplication application,
			Class<?> providerType) {
		StringBuilder message=new StringBuilder();
		message.append("The ");
		message.append(providerType.getCanonicalName());
		message.append(" is not defined in ");
		message.append(application.getClass().getCanonicalName());
		return message.toString();
	}

}
