package nth.reflect.javafx.control.toolbar;

import com.jfoenix.svg.SVGGlyph;

import javafx.scene.paint.Color;
import nth.introspect.ui.style.control.ToolbarIconStyle;
import nth.reflect.javafx.control.style.RfxColorFactory;
import nth.reflect.javafx.control.window.RfxWindow;

/**
 * Reflect Application Toolbar resize window button for JavaFX with Google Material Design style
 * 
 * @author nilsth
 *
 */
public class RfxApplicationToolbarResizeWindowButton extends RfxApplicationToolbarButton {

	private SVGGlyph resizeMaxGlyph;
	private SVGGlyph resizeMinGlyph;
	private final RfxWindow window;

	public RfxApplicationToolbarResizeWindowButton(RfxWindow window, ToolbarIconStyle iconStyle) {
		super(iconStyle);
		this.window = window;
		resizeMaxGlyph = createResizeMaxGlyph(iconStyle);
		resizeMinGlyph = createResizeMinGlyph(iconStyle);
		initIcon();
		initAction();
	}

	private SVGGlyph createResizeMinGlyph(ToolbarIconStyle iconStyle) {
		Color color = RfxColorFactory.create(iconStyle.getIconColor());
		SVGGlyph resizeMinGlyph = new SVGGlyph(0, "RESIZE_MIN",
				"M80.842 943.158v-377.264h565.894v377.264h-565.894zM0 404.21v619.79h727.578v-619.79h-727.578zM377.264 161.684h565.894v377.264h-134.736v80.842h215.578v-619.79h-727.578v323.37h80.842v-161.686z",
				color);
		int size = iconStyle.getSize();
		resizeMinGlyph.setSize(size,size);
		return resizeMinGlyph;
	}

	private SVGGlyph createResizeMaxGlyph(ToolbarIconStyle iconStyle) {
		Color color = RfxColorFactory.create(iconStyle.getIconColor());
		SVGGlyph resizeMaxGlyph = new SVGGlyph(0, "RESIZE_MAX",
				"M726 810v-596h-428v596h428zM726 44q34 0 59 25t25 59v768q0 34-25 60t-59 26h-428q-34 0-59-26t-25-60v-768q0-34 25-60t59-26z",
				color);
		int size = iconStyle.getSize();
		resizeMaxGlyph.setSize(size,size);
		return resizeMaxGlyph;
	}

	private void initIcon() {
		if (window.isMaximized()) {
			setGraphic(resizeMinGlyph);
			// setTooltip(new Tooltip("Restore Down"));
		} else {
			setGraphic(resizeMaxGlyph);
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
			initIcon();
		});
	}
	

}
