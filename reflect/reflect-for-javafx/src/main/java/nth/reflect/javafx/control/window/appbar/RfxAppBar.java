package nth.reflect.javafx.control.window.appbar;

import com.jfoenix.effects.JFXDepthManager;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.control.style.RfxStyleProperties;

public class RfxAppBar extends BorderPane {

	public RfxAppBar(UserInterfaceContainer userInterfaceContainer) {
		String style = new RfxStyleProperties()
				.setBackground(MaterialColorSetCssName.PRIMARY.BACKGROUND())
				//.setMinHeight(BAR_HEIGHT)
				// .setMinWidth(300)
				.setPadding(0).setAlignment(Pos.CENTER_LEFT).toString();
		setStyle(style);
		JFXDepthManager.setDepth(this, 1);
		
		HBox titleBar = new RfxAppTitleBar(userInterfaceContainer);
		setTop(titleBar);
		
		RfxAppButtonBar buttonBar = new RfxAppButtonBar(userInterfaceContainer);
		setBottom(buttonBar);
	}
	
public double calculateHeight() {
	return computePrefHeight(-1);
}
		
}
