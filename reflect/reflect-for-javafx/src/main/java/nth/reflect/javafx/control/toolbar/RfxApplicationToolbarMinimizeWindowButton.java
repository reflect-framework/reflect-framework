package nth.reflect.javafx.control.toolbar;

import com.jfoenix.svg.SVGGlyph;

import javafx.scene.paint.Color;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Reflect Application Toolbar Minimize button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarMinimizeWindowButton extends RfxApplicationToolbarButton {
	public RfxApplicationToolbarMinimizeWindowButton(RfxWindow window, ToolbarIconStyle iconStyle) {
		super(iconStyle);
		setIcon(iconStyle);
		setMenuAction(window);
	}

	private void setIcon(ToolbarIconStyle iconStyle) {
		Color color = RfxColorFactory.create(iconStyle.getIconColor());
		SVGGlyph minus = new SVGGlyph(0, "MINUS",
				"M804.571 420.571v109.714q0 22.857-16 38.857t-38.857 16h-694.857q-22.857 0-38.857-16t-16-38.857v-109.714q0-22.857 16-38.857t38.857-16h694.857q22.857 0 38.857 16t16 38.857z",
				color);
		minus.setSize(iconStyle.getSize(), 2);
		minus.setTranslateY(4);
		setGraphic(minus);
	}

	private void setMenuAction(RfxWindow window) {
		setOnAction((action)->window.minimize());
	}

}
