package nth.reflect.javafx;

import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.reflect.javafx.control.tabpane.RfxTabBarPane;
import nth.reflect.javafx.control.window.RfxWindow;
import nth.reflect.javafx.RfxView;
/**
 * ViewContainer adapter on a {@link RfxWindow}
 * TODO simplify {@link ViewContainer} like {@link RfxWindow#getTabPane()#getTabs()} or {@link RfxWindow#getTabPane()#getSelectedTab()} 
 * @author nilsth
 *
 * @param <RfxTab>
 */
public class RfxViewContainer implements ViewContainer<RfxView> {
	

	private final RfxTabBarPane tabPane;

	public RfxViewContainer(RfxWindow mainWindow) {
		tabPane=mainWindow.getTabPane();
	}

	@Override
	public int getViewCount() {
		return tabPane.getTabs().size();
	}

	@Override
	public RfxView getView(int index) {
		return tabPane.getTabs().get(index);
	}

	@Override
	public void setSelectedView(RfxView view) {
		tabPane.getSelectedTabProperty().set( view);
	}

	@Override
	public View getSelectedView() {
		return  tabPane.getSelectedTabProperty().get();
	}

	@Override
	public void addView(RfxView view) {
		tabPane.getTabs().add( view);

	}

	@Override
	public void removeView(RfxView view) {
		tabPane.getTabs().remove( view);
	}

}
