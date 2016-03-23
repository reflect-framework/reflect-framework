package nth.reflect.javafx.control.toolbar;

import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import javafx.scene.paint.Color;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.style.RfxColorFactory;

/**
 * Reflect Application Toolbar menu button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarMenuButton extends RfxApplicationToolbarButton {

	public RfxApplicationToolbarMenuButton(ToolbarIconStyle iconStyle) {
		super(iconStyle);
		initIcon(iconStyle);
		initAction();
	}

	private void initAction() {
		// TODO Auto-generated method stub
	}

	private void initIcon(ToolbarIconStyle iconStyle) {
		GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
		Glyph glyph = fontAwesome.create(org.controlsfx.glyphfont.FontAwesome.Glyph.NAVICON);//TODO Icon URI with fonts
		Color color = RfxColorFactory.create(iconStyle.getIconColor());
		glyph.setTextFill(color);
		int size = iconStyle.getSize();
		glyph.setFontSize(size);
		setGraphic(glyph);
	}

}
