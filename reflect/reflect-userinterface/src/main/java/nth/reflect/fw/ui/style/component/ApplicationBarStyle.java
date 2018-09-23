package nth.reflect.fw.ui.style.component;

import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.basic.Font;

/**
 * {@link ApplicationBarStyle} is a {@link ReflectStyleClass} for a {@link ApplicationBar}
 * 
 * @author nilsth
 *
 */
public class ApplicationBarStyle {

	public static int getHeightInPixels() {
		return 56;
	}

	public static Color getForeground1(ReflectColors reflectColors) {
		return reflectColors.getPrimaryColors().getForeground1();
	}
	
	public static Color getBackGround(ReflectColors reflectColors) {
		return reflectColors.getPrimaryColors().getBackground();
	}

	public static Color getBackgroundHighLighted(ReflectColors reflectColors) {
		return reflectColors.getPrimaryColors().getBackgroundHighLighted();
	}

	public static int getIconPadding() {
		return 16;
	}

	public static int getIconSize() {
		return 20;
	}

	public static Font getTitleFont() {
		return MaterialFont.getRobotoMedium(20);
	}
}
