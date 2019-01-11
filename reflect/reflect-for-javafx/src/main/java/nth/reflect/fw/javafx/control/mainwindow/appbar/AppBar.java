package nth.reflect.fw.javafx.control.mainwindow.appbar;

import com.jfoenix.effects.JFXDepthManager;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.mainwindow.mainmenu.MainMenuPane;
import nth.reflect.fw.javafx.control.style.StyleProperties;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

public class AppBar extends BorderPane {

	private final AppButtonBar buttonBar;

	public AppBar(UserInterfaceContainer userInterfaceContainer, MainMenuPane mainMenuPane) {
		String style = new StyleProperties()
				.setBackground(ReflectColorName.PRIMARY.BACKGROUND())
				// .setMinHeight(BAR_HEIGHT)
				// .setMinWidth(300)
				.setPadding(0).setAlignment(Pos.CENTER_LEFT).toString();
		setStyle(style);
		JFXDepthManager.setDepth(this, 1);

		HBox titleBar = new AppTitleBar(userInterfaceContainer);
		setTop(titleBar);

		buttonBar = new AppButtonBar(userInterfaceContainer, mainMenuPane);
		setBottom(buttonBar);
	}

	public double calculateHeight() {
		return computePrefHeight(-1);
	}

	public AppButtonBar getButtonBar() {
		return buttonBar;
	}

}
