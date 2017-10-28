package nth.reflect.javafx.control.window.appbar;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.reflect.javafx.control.button.RfxButton;
import nth.reflect.javafx.control.fonticon.FontAwesomeIconName;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarButton;
import nth.reflect.javafx.control.view.form.RfxDomainPropertyPane;
import nth.reflect.javafx.control.view.form.RfxFormView;
import nth.reflect.javafx.control.view.table.RfxTableView;
import nth.reflect.javafx.control.window.RfxWindow;
import nth.reflect.javafx.control.window.mainmenu.RfxMainMenuPane;

/**
 * Button bar as part of the {@link RfxAppBar} which contains the main
 * navigation buttons:
 * <ul>
 * <li>the MainMenu button (see {@link RfxMainMenuPane}</li>
 * <li>the {@link RfxTabButton}'s that navigate to the content views (e.g. See
 * {@link RfxFormView} and {@link RfxTableView})</li>
 * <li>a tab menu button when not all {@link RfxTabButton}'s can be displayed on
 * the {@link RfxAppButtonBar}. It shows a list of all {@link RfxTabButton}'s
 * when clicked)</li>
 * </ul>
 * 
 * TODO: This class to replace {@link RfxTabButtonBar}<br>
 * TODO: This class to extend Pane and override {@link #layoutChildren()} and compute methods (e.g. see {@link RfxDomainPropertyPane})
 * @author nilsth
 *
 */


public class RfxAppButtonBar extends BorderPane {

	public static final int BAR_HEIGHT = 28;
	private final BooleanProperty mainMenuVisibleProperty;

	public RfxAppButtonBar(UserInterfaceContainer userInterfaceContainer, RfxTabButtonBar tabButtonBar) {
		RfxWindow rfxWindow=userInterfaceContainer.get(RfxWindow.class);
		mainMenuVisibleProperty=rfxWindow.getMainMenuVisibleProperty();
		
		setPadding(new Insets(1));
		HBox menuBar = createMenuBar();
		setLeft(menuBar);

		setCenter(tabButtonBar);

		HBox rightButtonPane = new HBox();
		rightButtonPane.setMaxHeight(BAR_HEIGHT);
		setRight(rightButtonPane);
		RfxButton tabSelectionButton = createTabSelectionButton();
		rightButtonPane.getChildren().add(tabSelectionButton);
		RfxButton tabMenuButton = createTabMenuButton();
		rightButtonPane.getChildren().add(tabMenuButton);
	}

	private HBox createMenuBar() {
		HBox menuBar = new HBox();
		menuBar.setMinHeight(BAR_HEIGHT );
		menuBar.setMinWidth(RfxWindow.MENU_WIDTH);

		RfxButton mainMenuButton = createMainMenuButtton();
		menuBar.getChildren().add(mainMenuButton);

		return menuBar;
	}

	private RfxApplicationToolbarButton createTabMenuButton() {
		RfxApplicationToolbarButton tabMenuButton = new RfxApplicationToolbarButton(
				FontAwesomeIconName.ELLIPSIS_V);
		// tabMenuButton.setOnAction(this::onMenuButtonAction);
		return tabMenuButton;
	}

	private RfxApplicationToolbarButton createTabSelectionButton() {
		RfxApplicationToolbarButton tabSelectionButton = new RfxApplicationToolbarButton(
				FontAwesomeIconName.CLONE);
		// menuButton.setOnAction(this::onMenuButtonAction);
		return tabSelectionButton;
	}

	private RfxApplicationToolbarButton createMainMenuButtton() {
		RfxApplicationToolbarButton mainMenuButton = new RfxApplicationToolbarButton(
				FontAwesomeIconName.BARS);
		mainMenuButton.setOnAction(this::toggleMainMenuVisibility);
		return mainMenuButton;
	}

	private void toggleMainMenuVisibility(ActionEvent event) {
		mainMenuVisibleProperty.set(!mainMenuVisibleProperty.get());
	}

}
