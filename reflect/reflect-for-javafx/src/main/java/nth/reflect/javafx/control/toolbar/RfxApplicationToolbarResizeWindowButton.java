package nth.reflect.javafx.control.toolbar;

import java.net.MalformedURLException;

import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Reflect Application Toolbar resize window button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarResizeWindowButton extends RfxApplicationToolbarButton {

private static final String ICON_URL_RESIZE_MAX = FontAwesomeUrl.EXPAND;
private static final String ICON_URL_RESIZE_MIN = FontAwesomeUrl.COMPRESS;
	private final RfxWindow window;

	public RfxApplicationToolbarResizeWindowButton(RfxWindow window, ToolbarIconStyle iconStyle) throws MalformedURLException {
		super(ICON_URL_RESIZE_MAX);
		this.window = window;
		initIcon();
		initAction();
	}

	private void initIcon() throws MalformedURLException {
		if (window.isMaximized()) {
			setIcon(ICON_URL_RESIZE_MIN);
			// setTooltip(new Tooltip("Restore Down"));
		} else {
			setIcon(ICON_URL_RESIZE_MAX);
			// setTooltip(new Tooltip("Maximize"));
		}
	}

	private void initAction() {
		setOnAction((action) -> {
			if (window.isMaximized()) {
				window.midimize();
			} else {
				window.maximize();
			}
			try {
				initIcon();
			} catch (Exception e) {
			}
		});
	}
	

}
