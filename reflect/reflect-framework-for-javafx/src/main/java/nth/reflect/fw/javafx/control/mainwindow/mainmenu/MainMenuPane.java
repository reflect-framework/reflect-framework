package nth.reflect.fw.javafx.control.mainwindow.mainmenu;

import javafx.scene.layout.BorderPane;
import nth.reflect.fw.gui.component.mainmenu.MainMenu;
import nth.reflect.fw.gui.component.mainmenu.MainMenuStyle;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.itemtreelist.ItemTreePanel;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

public class MainMenuPane extends BorderPane implements MainMenu {

	public MainMenuPane(UserInterfaceContainer userInterfaceContainer) {
		super();
		addStyleClass();

		setMinWidth(MainMenuStyle.WIDTH);
		setMaxWidth(MainMenuStyle.WIDTH);

		ItemTreePanel mainMenuList = new MainMenuItemTreePanel(userInterfaceContainer);
		setCenter(mainMenuList);
	}

	private void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(MainMenuPane.class));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		styleSheet.addStyleGroup(StyleSelector.createFor(MainMenuPane.class)).getProperties().setBorderWidth(0, 1, 0, 0)
				.setBorderColor(ReflectColorName.CONTENT.BACKGROUND_12());
		// TODO replace right border with shade (raised pane)?
	}

}
