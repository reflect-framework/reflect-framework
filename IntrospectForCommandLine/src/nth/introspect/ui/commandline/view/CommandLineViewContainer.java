package nth.introspect.ui.commandline.view;

import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.util.exception.MethodNotSupportedException;

public class CommandLineViewContainer implements ViewContainer<CommandLineView> {

	@Override
	public int getViewCount() {
		throw new MethodNotSupportedException();
	}

	@Override
	public void setSelectView(CommandLineView view) {
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
