package nth.reflect.javafx.control.style;

import javafx.scene.text.Font;

/**
 * converts Reflect font to JavaFX font
 * @author nilsth
 *
 */

public class RfxFontFactory {

	public static Font create(
			nth.introspect.ui.style.basic.Font font) {
		String url = font.getUrl().toExternalForm();
		return Font.loadFont(url, font.getSize());
	}



}
