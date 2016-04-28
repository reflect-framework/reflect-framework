package nth.introspect.ui.commandline.demo.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.introspect.layer5provider.path.url.ClassResourceUrlHandler;
import nth.introspect.layer5provider.path.url.ReflectUrlConnection;
import nth.introspect.ui.commandline.IntrospectApplicationForCommandLine;
import nth.introspect.ui.commandline.demo.dom.TestService;

public class IntrospectCommandLineDemo extends
		IntrospectApplicationForCommandLine {

	public static void main(String[] arguments) {
		launch(arguments);;
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(TestService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<Class<?>>();
	}

	@Override
	public List<ReflectUrlConnection> getReflectUrlStreamHandlers() {
		return Arrays.asList(new ClassResourceUrlHandler());
	}


}
