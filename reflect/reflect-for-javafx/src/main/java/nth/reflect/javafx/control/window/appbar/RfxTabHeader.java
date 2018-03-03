package nth.reflect.javafx.control.window.appbar;

import com.jfoenix.controls.JFXRippler;

import nth.introspect.layer1userinterface.view.View;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Wraps {@link RfxTabHeaderContent} into a {@link JFXRippler} to give it a rippler effect
 * @author nilsth
 *
 */
public class RfxTabHeader extends JFXRippler {

	private final View tab;

	public RfxTabHeader(RfxWindow rfxWindow, View tab) {
		super(new RfxTabHeaderContent(rfxWindow, tab));
		this.tab = tab;
		initStyle();
	}

	private void initStyle() {
		RfxStyleProperties properties=new RfxStyleProperties();
		properties.put("-jfx-rippler-fill", MaterialColorSetCssName.PRIMARY.BACKGROUND_HIGHLIGHTED());
		setStyle(properties.toString());
	}

	public View getTab() {
		return tab;
	}
	
}
