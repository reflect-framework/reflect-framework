package nth.reflect.fw.gui.component.table;

import nth.reflect.fw.gui.component.ReflectStyleClass;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.gui.style.basic.Font;

/**
 * {@link TableStyle} is a {@link ReflectStyleClass} for a {@link Table}
 * 
 * @author nilsth
 *
 */
public class TableStyle implements ReflectStyleClass {

	public static Color getTextColor(ColorProvider colorProvider) {
		return colorProvider.getContentColors().getForeground();
	}

	public static Color getBackground(ColorProvider colorProvider) {
		return colorProvider.getContentColors().getBackground();
	}

	public static Color getBackgroundHighLighted(ColorProvider colorProvider) {
		return colorProvider.getContentColors().getBackground20();
	}

	public static int getMinHeight() {
		return 48;
	}

	public static int getPaddingLeft() {
		return 16;
	}

	public static int getPaddingRight() {
		return 16;
	}

	public static int getIndent() {
		return 32;
	}

	public static Font getCellFont() {
		return MaterialFont.getRobotoLight(FONT_SIZE);
	}

	public static final int FONT_SIZE = 16;
}
