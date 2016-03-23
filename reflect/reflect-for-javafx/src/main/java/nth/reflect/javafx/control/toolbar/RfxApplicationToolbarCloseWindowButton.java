package nth.reflect.javafx.control.toolbar;

import com.jfoenix.svg.SVGGlyph;

import javafx.scene.paint.Color;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.style.RfxColorFactory;

/**
 * Reflect Application Toolbar Close Window button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarCloseWindowButton extends RfxApplicationToolbarButton {

	public RfxApplicationToolbarCloseWindowButton(ToolbarIconStyle iconStyle) {
		super(iconStyle);
		initIcon(iconStyle);
		initAction();
	}

	private void initIcon(ToolbarIconStyle iconStyle) {
		//TODO create own SVGGlyph so we can get rid of all jfounix libs (remove lib folder)
		Color color = RfxColorFactory.create(iconStyle.getIconColor());
		SVGGlyph glyph = new SVGGlyph(0, "CLOSE", "M810 274l-238 238 238 238-60 60-238-238-238 238-60-60 238-238-238-238 60-60 238 238 238-238z", color);
		int size = iconStyle.getSize();
		glyph.setSize(size,size);
		setGraphic(glyph);
	}

	private void initAction() {
		setOnAction((action)-> System.exit(0));
	}

}
