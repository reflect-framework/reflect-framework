package nth.reflect.fw.javafx.control.style;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
/**
 * converts Reflect color to JavaFX color
 * @author nilsth
 *
 */
public class ColorFactory {



	public static Color create(nth.reflect.fw.ui.style.basic.Color color) {
		return new Color(asDouble(color.getRed()), asDouble(color.getGreen()), asDouble(color.getBlue()), asDouble(color.getAlpha()));
	}

	private static double asDouble(int value) {
		return value/255.0;
	}

	public static Background createBackGround(nth.reflect.fw.ui.style.basic.Color backgroundColor) {
		Color color = create(backgroundColor);
		BackgroundFill backgroundFill=new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
		Background background=new Background(backgroundFill);
		return background;
	}

}
