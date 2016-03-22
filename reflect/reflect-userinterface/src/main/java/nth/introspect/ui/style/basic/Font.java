package nth.introspect.ui.style.basic;

import java.net.URL;

import nth.introspect.ui.style.DisplayScale;
/**
 * This class was created to get away from AWT or JavaFX dependencies
 * @author nilsth
 *
 */
public class Font {
	private final URL url;
	private final String name;
	private final int size;
	private final boolean bold;

	public Font(URL url, String name, int size, boolean bold) {
		this.url = url;
		this.name = name;
		this.size = size;
		this.bold = bold;
	}

	public URL getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public boolean isBold() {
		return bold;
	}

}
