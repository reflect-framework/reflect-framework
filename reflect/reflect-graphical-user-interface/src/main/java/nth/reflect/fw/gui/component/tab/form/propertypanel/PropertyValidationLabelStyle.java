package nth.reflect.fw.gui.component.tab.form.propertypanel;

import nth.reflect.fw.gui.component.ReflectStyleClass;
import nth.reflect.fw.gui.style.basic.Color;
import nth.reflect.fw.gui.style.basic.Font;

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
