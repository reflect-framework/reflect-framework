package nth.reflect.javafx.control.mainmenu;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.MaterialColors;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.tabpane.RfxTabBarPane;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxMainMenuView extends BorderPane {

	public RfxMainMenuView(UserInterfaceContainer userInterfaceContainer) {
		super();
		setMinWidth(RfxWindow.MENU_WIDTH);
		setMaxWidth(RfxWindow.MENU_WIDTH);
		
		javafx.scene.paint.Color lineColor = RfxColorFactory
				.create(MaterialColors.getContentColorSet().getForeground3());
		BorderStroke borderStroke = new BorderStroke(null, lineColor, null, null, null,
				BorderStrokeStyle.SOLID, null, null, null, null, Insets.EMPTY);
		Border border = new Border(borderStroke);
		setBorder(border);

		RfxItemTreeView mainMenuList = new RfxMainMenuItemTreeView(userInterfaceContainer);
		setCenter(mainMenuList);
	}
	

}
