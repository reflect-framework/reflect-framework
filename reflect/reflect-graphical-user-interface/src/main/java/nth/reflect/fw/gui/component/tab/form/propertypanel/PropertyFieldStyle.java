package nth.reflect.fw.gui.component.tab.form.propertypanel;

import nth.reflect.fw.gui.component.ReflectStyleClass;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ReflectColors;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.gui.style.basic.Font;

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
		return reflectColors.getContentColors().getForeground();
	}
	
	public static Color getBackground(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getBackground();
	}

	public static Color getBackgroundHighLighted(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getBackground20();
	}

}
