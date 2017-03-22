package nth.reflect.javafx.control.window;

import nth.introspect.ui.style.ContentColor;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.control.RfxControl;

/**
 * {@link RfxUtil} is an ugly utility class so that Reflect JavaFX components/widgets can easily get information on presentation.
 * @author nilsth
 *
 */
public class RfxUtil implements RfxControl {

	private static RfxWindow window;
	private static MaterialColorSet primaryColorSet;
	private static MaterialColorSet accentColorSet;
	private static MaterialColorSet contentColorSet;
	
	public static void init(RfxWindow rfxWindow, Color primaryColor, Color accentColor, ContentColor contentColor) {
		window=rfxWindow;
		primaryColorSet=new MaterialColorSet(primaryColor);
		accentColorSet=new MaterialColorSet(accentColor);
		contentColorSet=new MaterialColorSet(contentColor.getColor());
	}
	

	public static RfxWindow getRfxWindow() {
		return window;
	}

	public static MaterialColorSet getPrimaryColorSet() {
		return primaryColorSet;
	}

	public static MaterialColorSet getAccentColorSet() {
		return accentColorSet;
	}

	public static MaterialColorSet getContentColorSet() {
		return contentColorSet;
	}


	

}
