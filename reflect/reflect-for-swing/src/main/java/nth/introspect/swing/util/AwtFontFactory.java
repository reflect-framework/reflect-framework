package nth.introspect.swing.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class AwtFontFactory {

	public static Font create(nth.introspect.ui.style.basic.Font font) {
		try {
			InputStream inputStream = font.getUrl().openStream();
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(awtFont);
			inputStream.close();
			awtFont=awtFont.deriveFont((float)font.getSize());
			return awtFont;
		} catch (FontFormatException ffe) {
			throw new RuntimeException(ffe);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

}
