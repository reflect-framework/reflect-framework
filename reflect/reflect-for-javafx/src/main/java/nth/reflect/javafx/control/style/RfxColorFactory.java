package nth.reflect.javafx.control.style;

import javafx.scene.paint.Color;
/**
 * converts Reflect color to JavaFX color
 * @deprecated use {@link RfxStyleSheet}
 * @author nilsth
 *
 */
public class RfxColorFactory {



	public static Color create(nth.introspect.ui.style.basic.Color color) {
		return new Color(asDouble(color.getRed()), asDouble(color.getGreen()), asDouble(color.getBlue()), asDouble(color.getAlpha()));
	}

	private static double asDouble(int value) {
		return value/255.0;
	}

}
