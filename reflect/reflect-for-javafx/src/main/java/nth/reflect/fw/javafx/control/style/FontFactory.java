package nth.reflect.fw.javafx.control.style;

import javafx.scene.text.Font;

/**
 * converts Reflect font to JavaFX font
 * @author nilsth
 *
 */

public class FontFactory {

	public static Font create(
			nth.reflect.fw.gui.style.basic.Font font) {
		String url = font.getUrl().toExternalForm();
		return Font.loadFont(url, font.getSize());
	}



}
