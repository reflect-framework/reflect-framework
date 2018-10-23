package nth.reflect.ui.vaadin.mainwindow;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.style.ReflectColorSet;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.basic.Color;

public class JavaScriptToSetCssColors {

	private static final String CSS_VARIABLE_PREFIX = "--";
	private static final String CSS_STYLE = "cssStyle";
	private static final char QUOTE = '"';

	public static String createFor(UserInterfaceContainer userInterfaceContainer) {
		ReflectColors colors = ReflectColors.getFrom(userInterfaceContainer);
		StringBuilder javascript = new StringBuilder();
		javascript.append(CSS_STYLE);
		javascript.append("=document.documentElement.style;");
		append(javascript, ReflectColorName.PRIMARY, colors.getPrimaryColors());
		append(javascript, ReflectColorName.ACCENT, colors.getAccentColors());
		append(javascript, ReflectColorName.CONTENT, colors.getContentColors());
		append(javascript, ReflectColorName.ERROR, colors.getErrorColors());
		return javascript.toString();
	}

	private static void append(StringBuilder javascript, ReflectColorName colorSetName, ReflectColorSet colorSet) {
		append(javascript, colorSetName.BACKGROUND(), colorSet.getBackground());
		append(javascript, colorSetName.BACKGROUND_12(), colorSet.getBackground12());
		append(javascript, colorSetName.BACKGROUND_20(), colorSet.getBackground20());
		append(javascript, colorSetName.FOREGROUND(), colorSet.getForeground());
	}

	private static void append(StringBuilder javascript, String colorName, Color color) {
		javascript.append(CSS_STYLE);
		javascript.append(".setProperty(");
		javascript.append(QUOTE);
		javascript.append(CSS_VARIABLE_PREFIX);
		javascript.append(colorName);
		javascript.append(QUOTE);
		javascript.append(",");
		javascript.append(QUOTE);
		javascript.append("rgb(");
		javascript.append(color.getRed());
		javascript.append(",");
		javascript.append(color.getGreen());
		javascript.append(",");
		javascript.append(color.getBlue());
		javascript.append(",");
		javascript.append(color.getAlpha() / 255.0);
		javascript.append(")");
		javascript.append(QUOTE);
		javascript.append(");");
	}


}
