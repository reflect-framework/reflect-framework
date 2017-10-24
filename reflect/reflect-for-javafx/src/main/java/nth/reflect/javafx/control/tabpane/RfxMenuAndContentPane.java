package nth.reflect.javafx.control.tabpane;

import com.jfoenix.controls.JFXDrawer;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer2service.MainMenu;
import nth.introspect.ui.style.MaterialColors;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.window.RfxWindow;
import nth.reflect.javafx.control.window.mainmenu.RfxMainMenuPane;

public class RfxMenuAndContentPane extends StackPane {

	private final BorderPane mainMenuPane;
	private final BorderPane contentPane;
	private final BooleanBinding windowExtraWideBinding;

	/**
	 * TODO move content window to the right when wide screen and menu opened.
	 * see {@link JFXDrawer}
	 * 
	 * @param userInterfaceContainer
	 * @param windowExtraWideBinding
	 */
	public RfxMenuAndContentPane(UserInterfaceContainer userInterfaceContainer,
			RfxMainMenuPane mainMenuPane) {

		RfxWindow rfxWindow = userInterfaceContainer.get(RfxWindow.class);
		this.windowExtraWideBinding = rfxWindow.getExtraWideBinding();
		this.mainMenuPane = mainMenuPane;
		contentPane = createContent();
		getChildren().add(contentPane);

		getChildren().add(mainMenuPane);
		setAlignment(mainMenuPane, Pos.TOP_LEFT);
	}

	// private BorderPane createMenuPane(UserInterfaceContainer
	// userInterfaceContainer) {
	// BorderPane mainMenuPane = new BorderPane();
	//// mainMenuPane.setBackground(RfxColorFactory
	//// .createBackGround(MaterialColors.getContentColorSet().getBackground()));
	// mainMenuPane.setMinWidth(RfxTabBarPane.MENU_WIDTH);
	// mainMenuPane.setMaxWidth(RfxTabBarPane.MENU_WIDTH);
	//
	// javafx.scene.paint.Color lineColor = RfxColorFactory
	// .create(MaterialColors.getContentColorSet().getForeground3());
	// BorderStroke borderStroke = new BorderStroke(null, lineColor, null, null,
	// null,
	// BorderStrokeStyle.SOLID, null, null, null, null, Insets.EMPTY);
	// Border border = new Border(borderStroke);
	// mainMenuPane.setBorder(border);
	//
	// RfxItemTreeView mainMenuList = new
	// RfxItemTreeView(userInterfaceContainer);
	// mainMenuPane.setCenter(mainMenuList);
	// return mainMenuPane;
	// }

	private BorderPane createContent() {
		BorderPane content = new BorderPane();
		content.setBackground(RfxColorFactory
				.createBackGround(MaterialColors.getContentColorSet().getBackground()));
		Label label = new Label("Content");
		content.setCenter(label);
		return content;
	}

	public void clearContentPane() {
		contentPane.setCenter(null);
	}

	public void setContentPane(Node content) {
		contentPane.setCenter(content);
	}

}
