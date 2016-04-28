package nth.reflect.javafx.control.toolbar;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.introspect.ui.style.fontawesome.FontAwesome;
import nth.reflect.javafx.control.style.RfxFontFactory;

/**
 * Reflect Application Toolbar menu button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarMenuButton extends RfxApplicationToolbarButton {

	public RfxApplicationToolbarMenuButton(ToolbarIconStyle iconStyle) {
		super();
		initIcon();
		initAction();
	}

	private void initAction() {
		// TODO Auto-generated method stub
	}

	private void initIcon() {
		setText(String.valueOf(FontAwesome. fa_navicon.character()));
	}

}
