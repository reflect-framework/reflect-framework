package nth.reflect.javafx.control.tabpane;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import nth.reflect.javafx.RfxView;

public class RfxTabButtonBar extends HBox {


	private RfxTabBarPane rfxTabBarPane;

	public RfxTabButtonBar(RfxTabBarPane rfxTabBarPane) {
		this.rfxTabBarPane = rfxTabBarPane;
	}

	private boolean matches(ObservableList<RfxView> tabs) {
		ObservableList<Node> children = getChildren();
		if (tabs.size()!=children.size()) {
			return false;
		}
		for (int index=0;index<tabs.size();index++) {
			RfxView tab = tabs.get(index);
			RfxTabButton tabButton= (RfxTabButton) children.get(index);
			if (!tab.equals(tabButton.getTab())) {
				return false;
			}
		}
		return true;
	}
	
	public void update(ObservableList<RfxView> tabs) {
		if (!matches(tabs)) {
			getChildren().clear();
			for (RfxView tab : tabs) {
				RfxTabButton tabButton = new RfxTabButton(rfxTabBarPane, tab);
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
