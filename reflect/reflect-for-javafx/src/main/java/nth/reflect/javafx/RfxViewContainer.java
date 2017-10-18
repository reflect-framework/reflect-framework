package nth.reflect.javafx;

import javafx.application.Platform;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.reflect.javafx.control.tabpane.RfxTabBarPane;
import nth.reflect.javafx.control.window.RfxWindow;
/**
 * ViewContainer adapter on a {@link RfxWindow}
 * TODO simplify {@link ViewContainer} like {@link RfxWindow#getTabPane()#getTabs()} or {@link RfxWindow#getTabPane()#getSelectedTab()} 
 * @author nilsth
 *
 * @param <RfxTab>
 */
public class RfxViewContainer implements ViewContainer<View> {
	

	private final RfxTabBarPane tabPane;

	public RfxViewContainer(RfxWindow mainWindow) {
		tabPane=mainWindow.getTabPane();
	}

	@Override
	public int getViewCount() {
		return tabPane.getTabs().size();
	}

	@Override
	public View getView(int index) {
		return tabPane.getTabs().get(index);
	}

	@Override
	public void setSelectedView(View view) {
		tabPane.getSelectedTabProperty().set( view);
	}

	@Override
	public View getSelectedView() {
		return  tabPane.getSelectedTabProperty().get();
	}

	@Override
	public void addView(View view) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				tabPane.getTabs().add( view);
			}
		});

	}

	@Override
	public void removeView(View view) {
		tabPane.getTabs().remove( view);
	}

}
