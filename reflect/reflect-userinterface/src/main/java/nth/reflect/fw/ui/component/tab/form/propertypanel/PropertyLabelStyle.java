package nth.reflect.fw.ui.component.tab.form.propertypanel;

import nth.reflect.fw.ui.component.ReflectStyleClass;
import nth.reflect.fw.ui.style.MaterialFont;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.basic.Font;

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

	public static Color getForeground1(ReflectColors reflectColors) {
		return reflectColors.getContentColors().getForeground();
	}

}
