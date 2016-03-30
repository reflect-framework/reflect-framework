package nth.reflect.javafx.control.style;

import javafx.scene.text.Font;

/**
 * converts Reflect font to JavaFX font
 * @deprecated use {@link RfxStyleSheet}
 * @author nilsth
 *
 */

public class RfxFontFactory {

	public static Font create(
			nth.introspect.ui.style.basic.Font font) {
		return Font.loadFont(font.getUrl().toExternalForm(), font.getSize());
	}



}
