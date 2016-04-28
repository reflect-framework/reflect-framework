package nth.reflect.javafx.control.toolbar;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialFont;
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

	public RfxApplicationToolbarButton() {
		super();
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxApplicationToolbarButton.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet, MaterialStyle materialStyle) {
		Font.loadFont(
				materialStyle.getApplicationToolbarIconStyle().getFont().getUrl().toExternalForm(),
				materialStyle.getApplicationToolbarIconStyle().getSize());//TODO other fonts???

		ToolbarIconStyle iconStyle = materialStyle.getTabToolbarIconStyle();
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxApplicationToolbarButton.class))
				.setBorderWidth(0).setHeight(iconStyle.getSize()).setPadding(iconStyle.getPadding())
				.setBackground(null).setTextFill(iconStyle.getColor())
				.setFont(materialStyle.getApplicationToolbarIconStyle().getFont()) //TODO how to do other fonts than FontAwesome?
				.setFontSize(materialStyle.getApplicationToolbarIconStyle().getSize());

		styleSheet.addStyleGroup(
				RfxStyleSelector.createFor(RfxApplicationToolbarButton.class).appendPressed())
				.setBackground(iconStyle.getPressedColor());

		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxApplicationToolbarButton.class)
				.append(ImageView.class)).setTextFill(iconStyle.getColor());
	}

}
