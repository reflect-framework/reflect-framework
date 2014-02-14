package nth.introspect.ui.commandline.demo.app;

import nth.introspect.Introspect;
import nth.introspect.ui.commandline.CommandLineIntrospectInitializer;
import nth.introspect.ui.commandline.demo.dom.TestService;

public class IntrospectCommandLineDemo {

	private IntrospectCommandLineDemo(String[] arguments) {
		// initialize introspect framework
		CommandLineIntrospectInitializer initializer = new CommandLineIntrospectInitializer(this, arguments);
		initializer.addServiceClass(TestService.class);
		Introspect.init(initializer);
	}

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new IntrospectCommandLineDemo(arguments);
	}

}
