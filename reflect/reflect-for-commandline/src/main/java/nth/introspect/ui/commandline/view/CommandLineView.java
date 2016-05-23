package nth.introspect.ui.commandline.view;

import java.net.URI;

import nth.introspect.generic.exception.MethodNotSupportedException;
import nth.introspect.layer1userinterface.view.View;

public abstract class CommandLineView  {


	/**
	 * Must override toString method to get text output
	 */
	@Override
	public abstract String toString() ;
}
