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
		super();
		this.window = window;
		resizeMaxGlyph = createResizeMaxGlyph(iconStyle);
		resizeMinGlyph = createResizeMinGlyph(iconStyle);
		initIcon();
		initAction();
	}

	private SVGGlyph createResizeMinGlyph(ToolbarIconStyle iconStyle) {
		Color color = RfxColorFactory.create(iconStyle.getColor());
		SVGGlyph resizeMinGlyph = new SVGGlyph(0, "RESIZE_MIN",
				"M431.12,41.977c0-2.475-0.955-4.661-2.857-6.567L395.711,2.861c-1.902-1.906-4.086-2.856-6.563-2.856    s-4.661,0.953-6.563,2.856L287.786,97.65l-41.104-41.112c-3.613-3.617-7.905-5.426-12.854-5.426    c-4.945,0-9.227,1.809-12.847,5.426c-3.614,3.616-5.422,7.898-5.422,12.847v127.906c0,4.952,1.808,9.233,5.422,12.851    c3.62,3.615,7.901,5.424,12.847,5.424h127.906c4.949,0,9.24-1.809,12.854-5.424c3.613-3.617,5.427-7.898,5.427-12.851    c0-4.948-1.813-9.231-5.427-12.847l-41.114-41.114l94.794-94.787C430.165,46.641,431.12,44.451,431.12,41.977z   M197.286,215.569H69.379c-4.948,0-9.231,1.809-12.847,5.426c-3.617,3.613-5.426,7.901-5.426,12.847    c0,4.948,1.809,9.232,5.426,12.847l41.112,41.113L2.857,382.589C0.949,384.492,0,386.683,0,389.153    c0,2.478,0.949,4.667,2.857,6.57L35.4,428.269c1.906,1.902,4.093,2.847,6.567,2.847s4.665-0.944,6.567-2.847l94.789-94.791    l41.112,41.11c3.615,3.614,7.898,5.428,12.847,5.428c4.952,0,9.233-1.813,12.85-5.428c3.616-3.61,5.424-7.898,5.424-12.847    V233.835c0-4.949-1.809-9.233-5.421-12.84C206.519,217.378,202.237,215.569,197.286,215.569z",
				color);
		int size = iconStyle.getSize();
		resizeMinGlyph.setSize(size,size);
		return resizeMinGlyph;
	}

	private SVGGlyph createResizeMaxGlyph(ToolbarIconStyle iconStyle) {
		Color color = RfxColorFactory.create(iconStyle.getColor());
		SVGGlyph resizeMaxGlyph = new SVGGlyph(0, "RESIZE_MAX",
				"M180.156,225.828c-1.903-1.902-4.093-2.854-6.567-2.854c-2.475,0-4.665,0.951-6.567,2.854l-94.787,94.787l-41.112-41.117    c-3.617-3.61-7.895-5.421-12.847-5.421c-4.952,0-9.235,1.811-12.851,5.421c-3.617,3.621-5.424,7.905-5.424,12.854v127.907    c0,4.948,1.807,9.229,5.424,12.847c3.619,3.613,7.902,5.424,12.851,5.424h127.906c4.949,0,9.23-1.811,12.847-5.424    c3.615-3.617,5.424-7.898,5.424-12.847s-1.809-9.233-5.424-12.854l-41.112-41.104l94.787-94.793    c1.902-1.903,2.853-4.086,2.853-6.564c0-2.478-0.953-4.66-2.853-6.57L180.156,225.828z  M433.11,5.424C429.496,1.807,425.212,0,420.263,0H292.356c-4.948,0-9.227,1.807-12.847,5.424    c-3.614,3.615-5.421,7.898-5.421,12.847s1.807,9.233,5.421,12.847l41.106,41.112l-94.786,94.787    c-1.901,1.906-2.854,4.093-2.854,6.567s0.953,4.665,2.854,6.567l32.552,32.548c1.902,1.903,4.086,2.853,6.563,2.853    s4.661-0.95,6.563-2.853l94.794-94.787l41.104,41.109c3.62,3.616,7.905,5.428,12.854,5.428s9.229-1.812,12.847-5.428    c3.614-3.614,5.421-7.898,5.421-12.847V18.268C438.53,13.315,436.734,9.04,433.11,5.424z",
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
