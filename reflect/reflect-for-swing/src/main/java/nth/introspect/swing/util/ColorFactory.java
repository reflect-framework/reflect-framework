package nth.introspect.swing.util;

import java.awt.Color;

public class ColorFactory {

	public static Color create(nth.introspect.ui.style.basic.Color color) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}

}
