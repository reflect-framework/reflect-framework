package nth.reflect.javafx.control.toolbar;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.style.RfxColorFactory;

/**
 * Reflect Application Toolbar Button (icons) for JavaFX with Google Material
 * Design style
 * 
 * @author nilsth
 *
 */

public class RfxApplicationToolbarButton extends Button {

	// TODO RfxToolbarIconButton
	// TODO RfxFlatButton
	// TODO RfxRaisedButton

	public RfxApplicationToolbarButton(ToolbarIconStyle iconStyle) {
		super();
		initStyleProperties(iconStyle);
	}

	private void initStyleProperties(ToolbarIconStyle iconStyle) {
		setBorder(Border.EMPTY);
		setHeight(iconStyle.getSize());
		setPadding(new Insets(iconStyle.getPadding()));
		Color pressedColor = RfxColorFactory.create( iconStyle.getPressedColor());
		backgroundProperty().bind(Bindings.when(pressedProperty())
				.then(new Background(
						new BackgroundFill(pressedColor, new CornerRadii(2), Insets.EMPTY)))
				.otherwise(Background.EMPTY));
	}

}
