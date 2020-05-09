package nth.reflect.fw.gui.component.tab.form.propertypanel;

import nth.reflect.fw.gui.component.ReflectStyleClass;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.gui.style.basic.Font;

/**
 * A {@link ReflectStyleClass} for a {@link PropertyLabel}
 * 
 * @author nilsth
 *
 */
public class PropertyLabelStyle implements ReflectStyleClass {

	public static Font getFont() {
		return MaterialFont.getRobotoRegular(12);
	}

	public static Color getForeground1(ColorProvider colorProvider) {
		return colorProvider.getContentColors().getForeground();
	}

}
