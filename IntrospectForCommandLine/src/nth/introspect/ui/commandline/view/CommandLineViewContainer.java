package nth.introspect.ui.commandline.view;

import nth.introspect.generic.exception.MethodNotSupportedException;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;

public class CommandLineViewContainer implements ViewContainer<CommandLineView> {

	@Override
	public int getViewCount() {
		throw new MethodNotSupportedException();
	}

	@Override
	public void setSelectedView(CommandLineView view) {
		throw new MethodNotSupportedException();
	}

	@Override
	public void addView(CommandLineView view) {
		System.out.println(view.toString());
	}

	@Override
	public void removeView(CommandLineView view) {
		throw new MethodNotSupportedException();
	}

	@Override
	public CommandLineView getView(int index) {
		throw new MethodNotSupportedException();
	}

	@Override
	public View getSelectedView() {
		return null;
	}

}
