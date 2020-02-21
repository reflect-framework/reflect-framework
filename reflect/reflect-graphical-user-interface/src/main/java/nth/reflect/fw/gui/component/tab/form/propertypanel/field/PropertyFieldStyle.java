package nth.reflect.fw.gui.component.tab.form.propertypanel.field;

import nth.reflect.fw.gui.component.ReflectStyleClass;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ColorProvider;
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
	
	public static Color getForeground1(ColorProvider colorProvider) {
		return colorProvider.getContentColors().getForeground();
	}
	
	public static Color getBackground(ColorProvider colorProvider) {
		return colorProvider.getContentColors().getBackground();
	}

	public static Color getBackgroundHighLighted(ColorProvider colorProvider) {
		return colorProvider.getContentColors().getBackground20();
	}

}
