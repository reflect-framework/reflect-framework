package nth.introspect.ui.commandline.demo.app;

import nth.introspect.Introspect;
import nth.introspect.ui.commandline.ReflectInitializerForCommandLine;
import nth.introspect.ui.commandline.demo.dom.TestService;

public class IntrospectCommandLineDemo  {


	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		ReflectInitializerForCommandLine initializer = new ReflectInitializerForCommandLine(new IntrospectCommandLineDemo(), arguments) ;
		initializer.registerFrontEndServiceClass(TestService.class);
		Introspect.init(initializer);
	}


}
