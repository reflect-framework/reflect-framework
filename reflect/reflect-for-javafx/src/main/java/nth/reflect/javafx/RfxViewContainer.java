package nth.reflect.javafx;

import javafx.application.Platform;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.reflect.javafx.control.window.RfxWindow;
/**
 * ViewContainer (adapter on {@link RfxWindow})
 * @author nilsth
 *
 */
public class RfxViewContainer implements ViewContainer<View> {
	

	private RfxWindow rfxWindow;

	public RfxViewContainer(RfxWindow rfxWindow) {
		this.rfxWindow = rfxWindow;
	}

	@Override
	public int getViewCount() {
		return rfxWindow.getTabsProperty().size();
	}

	@Override
	public View getView(int index) {
		return rfxWindow.getTabsProperty().get(index);
	}

	@Override
	public void setSelectedView(View view) {
		rfxWindow.getSelectedTabProperty().set( view);
	}

	@Override
	public View getSelectedView() {
		return  rfxWindow.getSelectedTabProperty().get();
	}

	@Override
	public void addView(View view) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				rfxWindow.getTabsProperty().add( view);
			}
		});

	}

	@Override
	public void removeView(View view) {
		rfxWindow.getTabsProperty().remove( view);
	}

}
