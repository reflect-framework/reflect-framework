package nth.reflect.fw.ui.style.component;

import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.basic.Font;

/**
 * {@link GridStyle} is a {@link ReflectStyleClass} for a {@link Grid}
 * 
 * @author nilsth
 *
 */
public class GridStyle implements ReflectStyleClass {

	public static Color getTextColor(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getForeground();
	}
	
	public static Color getBackground(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getBackground();
	}

	public static Color getBackgroundHighLighted(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getBackground20();
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
		return MaterialFont.getRobotoLight(16);
	}

}
