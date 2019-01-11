package nth.reflect.fw.ui.component.tab.form.propertypanel;

import nth.reflect.fw.ui.component.ReflectStyleClass;
import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.basic.Font;

/**
 * A {@link ReflectStyleClass} for a {@link PropertyValidationLabel}
 * @author nilsth
 *
 */
public class PropertyValidationLabelStyle implements ReflectStyleClass {

	public static Font getFont() {
		return PropertyLabelStyle.getFont();
	}
	
	public static Color getFontColor() {
		return Color.RED;
	}

}
