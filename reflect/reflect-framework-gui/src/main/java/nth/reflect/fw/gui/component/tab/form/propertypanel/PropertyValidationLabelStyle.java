package nth.reflect.fw.gui.component.tab.form.propertypanel;

import nth.reflect.fw.gui.component.ReflectStyleClass;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.gui.style.basic.Color;

/**
 * A {@link ReflectStyleClass} for a {@link PropertyValidationLabel}
 * 
 * @author nilsth
 *
 */
public class PropertyValidationLabelStyle extends PropertyLabelStyle implements ReflectStyleClass {

	public static final String FONT_COLOR = ReflectColorName.ERROR.BACKGROUND();

	/**
	 * @deprecated use {@link #FONT_COLOR}
	 * @return
	 */
	@Deprecated
	public static Color getFontColor() {
		return Color.RED;
	}

}
