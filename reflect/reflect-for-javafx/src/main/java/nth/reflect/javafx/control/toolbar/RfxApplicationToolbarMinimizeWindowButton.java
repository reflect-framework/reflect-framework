package nth.reflect.javafx.control.toolbar;

import java.net.MalformedURLException;

import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Reflect Application Toolbar Minimize button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarMinimizeWindowButton extends RfxApplicationToolbarButton {
	public RfxApplicationToolbarMinimizeWindowButton(RfxWindow window, ToolbarIconStyle iconStyle) throws MalformedURLException {
		super(FontAwesomeUrl.LONG_ARROW_DOWN);
		setMenuAction(window);
	}

	private void setMenuAction(RfxWindow window) {
		setOnAction((action)->window.minimize());
	}

}
