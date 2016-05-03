package nth.reflect.javafx.control.toolbar;

import java.net.MalformedURLException;

import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;

/**
 * Reflect Application Toolbar Close Window button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarCloseWindowButton extends RfxApplicationToolbarButton {

	public RfxApplicationToolbarCloseWindowButton(ToolbarIconStyle iconStyle) throws MalformedURLException {
		super(FontAwesomeUrl.CLOSE);//  FontAwesomeUrl.CLOSE);
		initAction();
	}

	private void initAction() {
		setOnAction((action)-> System.exit(0));
	}

}
