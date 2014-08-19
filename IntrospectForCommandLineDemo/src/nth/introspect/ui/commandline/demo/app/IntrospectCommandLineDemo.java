package nth.introspect.ui.commandline.demo.app;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.introspect.ui.commandline.demo.dom.TestService;

public class IntrospectCommandLineDemo extends
		IntrospectApplicationForCommandLine {

	public IntrospectCommandLineDemo(String[] arguments) {
		super(arguments);
	}

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		new IntrospectCommandLineDemo(arguments);
	}

	@Override
	public List<Class<?>> getFrontEndServiceClasses() {
		List<Class<?>> frontEndServiceClasses = new ArrayList<Class<?>>();
		frontEndServiceClasses.add(TestService.class);
		return frontEndServiceClasses;
	}

	@Override
	public List<Class<?>> getBackEndServiceClasses() {
		return new ArrayList<Class<?>>();
	}

}
