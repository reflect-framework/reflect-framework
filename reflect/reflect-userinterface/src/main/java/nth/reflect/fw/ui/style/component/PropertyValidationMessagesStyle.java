package nth.reflect.fw.ui.style.component;

import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.basic.Font;
import nth.reflect.fw.ui.tab.form.propertypanel.PropertyValidationMessages;

/**
 * A {@link ReflectStyleClass} for a {@link PropertyValidationMessages}
 * @author nilsth
 *
 */
public class PropertyValidationMessagesStyle implements ReflectStyleClass {

	public static Font getFont() {
		return PropertyLabelStyle.getFont();
	}
	
	public static Color getFontColor() {
		return Color.RED;
	}

}
