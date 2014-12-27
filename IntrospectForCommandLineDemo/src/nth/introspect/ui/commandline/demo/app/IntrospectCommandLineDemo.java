package nth.introspect.ui.commandline.demo.app;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.provider.notification.DefaultNotificationProvider;
import nth.introspect.provider.notification.NotificationProvider;
import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.introspect.ui.commandline.demo.dom.TestService;

public class IntrospectCommandLineDemo extends
		IntrospectApplicationForCommandLine {

	public IntrospectCommandLineDemo(String[] arguments) throws IntrospectContainerException {
		super(arguments);
	}

	/**
	 * @param args
	 * @throws IntrospectContainerException 
	 */
	public static void main(String[] arguments) throws IntrospectContainerException {
		new IntrospectCommandLineDemo(arguments);
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		List<Class<?>> frontEndServiceClasses = new ArrayList<Class<?>>();
		frontEndServiceClasses.add(TestService.class);
		return frontEndServiceClasses;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<Class<?>>();
	}

	@Override
	public Class<? extends NotificationProvider> getNotificationProviderClass() {
		return DefaultNotificationProvider.class;
	}

}
