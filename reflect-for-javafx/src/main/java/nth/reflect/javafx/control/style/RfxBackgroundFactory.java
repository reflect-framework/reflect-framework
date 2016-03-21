package nth.reflect.javafx.control.style;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import nth.introspect.ui.style.basic.Color;

public class RfxBackgroundFactory  {

	public static Background fill(Color backgroundColor) {
		javafx.scene.paint.Color color=RfxColorFactory.create(backgroundColor);
		return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
	}

	
}
