package nth.reflect.javafx.control.window;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import nth.introspect.layer1userinterface.view.View;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.window.appbar.RfxAppButtonBar;

/**
 * TODO: remove this class by moving its contents to {@link RfxAppButtonBar} class
 * @author nilsth
 *
 */
public class RfxTabButtonBar extends HBox implements RfxControl {


	private RfxWindow rfxWindow;

	public RfxTabButtonBar(RfxWindow rfxWindow) {
		this.rfxWindow = rfxWindow;
	}

	private boolean matches(ObservableList<View> tabs) {
		ObservableList<Node> children = getChildren();
		if (tabs.size()!=children.size()) {
			return false;
		}
		for (int index=0;index<tabs.size();index++) {
			View tab = tabs.get(index);
			RfxTabButton tabButton= (RfxTabButton) children.get(index);
			if (!tab.equals(tabButton.getTab())) {
				return false;
			}
		}
		return true;
	}
	
	public void update(ObservableList<View> tabs) {
		if (!matches(tabs)) {
			getChildren().clear();
			for (View tab : tabs) {
				RfxTabButton tabButton = new RfxTabButton(rfxWindow, tab);
				getChildren().add(tabButton);
			}
		}
		updateTabButtonVisability();
//		layoutChildren();
	}

	private void updateTabButtonVisability() {
		// TODO Auto-generated method stub
		
	}

}
