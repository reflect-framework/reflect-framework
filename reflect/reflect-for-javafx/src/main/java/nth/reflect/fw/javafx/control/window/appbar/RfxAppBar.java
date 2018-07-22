package nth.reflect.fw.javafx.control.window.appbar;

import com.jfoenix.effects.JFXDepthManager;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nth.reflect.fw.javafx.control.style.RfxStyleProperties;
import nth.reflect.fw.javafx.control.window.mainmenu.RfxMainMenuPane;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;

public class RfxAppBar extends BorderPane {

	private final RfxAppButtonBar buttonBar;

	public RfxAppBar(UserInterfaceContainer userInterfaceContainer, RfxMainMenuPane mainMenuPane) {
		String style = new RfxStyleProperties()
				.setBackground(MaterialColorSetCssName.PRIMARY.BACKGROUND())
				// .setMinHeight(BAR_HEIGHT)
				// .setMinWidth(300)
				.setPadding(0).setAlignment(Pos.CENTER_LEFT).toString();
		setStyle(style);
		JFXDepthManager.setDepth(this, 1);

		HBox titleBar = new RfxAppTitleBar(userInterfaceContainer);
		setTop(titleBar);

		buttonBar = new RfxAppButtonBar(userInterfaceContainer, mainMenuPane);
		setBottom(buttonBar);
	}

	public double calculateHeight() {
		return computePrefHeight(-1);
	}

	public RfxAppButtonBar getButtonBar() {
		return buttonBar;
	}

}
