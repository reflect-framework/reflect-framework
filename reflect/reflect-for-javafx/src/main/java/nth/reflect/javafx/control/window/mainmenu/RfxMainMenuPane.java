package nth.reflect.javafx.control.window.mainmenu;

import javafx.scene.layout.BorderPane;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxMainMenuPane extends BorderPane {

	public RfxMainMenuPane(UserInterfaceContainer userInterfaceContainer) {
		super();
	addStyleClass();
	
		setMinWidth(RfxWindow.MENU_WIDTH);
		setMaxWidth(RfxWindow.MENU_WIDTH);

		RfxItemTreeView mainMenuList = new RfxMainMenuItemTreeView(userInterfaceContainer);
		setCenter(mainMenuList);
	}
	

	private void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxMainMenuPane.class));
	}
	
	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxMainMenuPane.class)).getProperties()
		.setBorderWidth(0,1,0,0)
		.setBorderColor(MaterialColorSetCssName.CONTENT.FOREGROUND3());
		//TODO replace right border with shade (raised pane)?
	}	


}
