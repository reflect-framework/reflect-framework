package nth.reflect.fw.ui.swing.style;

import java.awt.Color;

import javax.swing.UIManager;

public class ColorUtil {
	private static final String TABLE_BACKGROUND_COLOR_KEY = "Table.background";
	private static final String TABLE_GRID_COLOR_KEY = "Table.gridColor";
	private static final String TABLE_FOREGROUND_COLOR_KEY = "Table.foreground";
	private static final String PANEL_BACKGROUND_COLOR_KEY = "Panel.background";
	private static final String TEXT_HIGH_LIGHT_COLOR_KEY ="textHighlight";

	
	public static Color getDark() {
		return UIManager.getColor(TABLE_FOREGROUND_COLOR_KEY);
	}
	
	public static Color getMediumDarkColor() {
		return UIManager.getColor(TABLE_GRID_COLOR_KEY);
	}

	public static Color getMiddleColor() {
		return UIManager.getColor(PANEL_BACKGROUND_COLOR_KEY);
	}

	public static Color getLightColor() {
		return UIManager.getColor(TABLE_BACKGROUND_COLOR_KEY);
	}

	public static Color getHighLightColor() {
		return UIManager.getColor(TEXT_HIGH_LIGHT_COLOR_KEY);
	}

	
}
