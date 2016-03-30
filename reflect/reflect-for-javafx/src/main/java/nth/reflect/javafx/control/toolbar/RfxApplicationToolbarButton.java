package nth.reflect.javafx.control.toolbar;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

/**
 * Reflect Application Toolbar Button (icons) for JavaFX with Google Material
 * Design style
 * 
 * @author nilsth
 *
 */

public class RfxApplicationToolbarButton extends Button {

	// TODO RfxFlatButton
	// TODO RfxRaisedButton

	public RfxApplicationToolbarButton(ToolbarIconStyle iconStyle) {
		super();
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxApplicationToolbarButton.class));
	}


	public static void appendStyleGroups(RfxStyleSheet styleSheet, MaterialStyle materialStyle) {
		ToolbarIconStyle iconStyle = materialStyle.getTabToolbarIconStyle();
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxApplicationToolbarButton.class))
				.setBorderWidth(0).setHeight(iconStyle.getSize())
				.setPadding(iconStyle.getPadding()).setBackground(null);

		styleSheet.addStyleGroup(
				RfxStyleSelector.createFor(RfxApplicationToolbarButton.class).appendPressed())
				.setBackground(iconStyle.getPressedColor());
	}

}
