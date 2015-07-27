package nth.introspect.ui.commandline.view;

import java.net.URI;

import nth.introspect.generic.exception.MethodNotSupportedException;
import nth.introspect.layer5provider.userinterface.view.View;

public abstract class CommandLineView implements View {

	private static final String NO_NEED_FOR_THIS_METHOD_FOR_A_COMMAND_LINE_USER_INTERFACE = "No need for this method for a command line user interface";

	@Override
	public String getViewTitle() {
		throw new MethodNotSupportedException(NO_NEED_FOR_THIS_METHOD_FOR_A_COMMAND_LINE_USER_INTERFACE); 
	}

	@Override
	public String getViewDescription() {
		throw new MethodNotSupportedException(NO_NEED_FOR_THIS_METHOD_FOR_A_COMMAND_LINE_USER_INTERFACE);
	}
	
	@Override
	public URI getViewIconURI() {
		throw new MethodNotSupportedException(NO_NEED_FOR_THIS_METHOD_FOR_A_COMMAND_LINE_USER_INTERFACE);
	}

	/**
	 * Must override toString method to get text output
	 */
	@Override
	public abstract String toString() ;
}
