package nth.reflect.fw.ui.style.component;

import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.basic.Font;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyField;

/**
 * A {@link ReflectStyleClass} for a {@link PropertyField}
 * @author nilsth
 *
 */
public class PropertyFieldStyle implements ReflectStyleClass {

	public static int getMinHeight() {
		return 20;
	}
	
	public static int getMaxHeight() {
		return 10*getMinHeight();
	}
	
	public static Font getFont() {
		return MaterialFont.getRobotoRegular(16);
	}
	
	public static Color getForeground1(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getForeground1();
	}
	
	public static Color getBackground(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getBackground();
	}

	public static Color getBackgroundHighLighted(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getBackgroundHighLighted();
	}

}
