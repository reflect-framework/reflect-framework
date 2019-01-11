package nth.reflect.fw.ui.style;

import java.net.URL;

import nth.reflect.fw.ui.style.basic.Font;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

/**
 * See
 * https://material.io/design/typography/the-type-system.html
 * 
 */

public class MaterialFont {

	private static final String ROBOTO_LIGHT_URL = "/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Light.ttf";
	private static final String ROBOTO_REGULAR_URL = "/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Regular.ttf";
	private static final String ROBOTO_MEDIUM_URL = "/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Medium.ttf";;
	// private static final String FONT_AWESOME_URL =
	// "/META-INF/resources/webjars/font-awesome/4.5.0/fonts/fontawesome-webfont.ttf";


	private static Font createFont(String path, int size) {
		String name = getFontName(path);
		return createFont(name, path, size);
	}

	private static Font createFont(String name, String path, int size) {
		URL url = MaterialFont.class.getResource(path);
		Font font = new Font(url, name, size, false);
		return font;
	}

	private static String getFontName(String path) {
		int beginIndex = path.lastIndexOf("/") + 1;
		int endIndex = path.indexOf(".", beginIndex);
		String name = path.substring(beginIndex, endIndex);
		name = name.replace("-", " ");
		return name;
	}

	public static Font getRobotoLight(int size) {
		return createFont(ROBOTO_LIGHT_URL, size);
	}

	

	
	public static Font getRobotoMedium(int size) {
		return createFont(ROBOTO_MEDIUM_URL, size);
	}


	public static Font getRobotoRegular(int size) {
		return createFont(ROBOTO_REGULAR_URL, size);
	}


	public static Font getFontAwesome() {
		return createFont(FontAwesomeUrl.FONTAWESOME_NAME, FontAwesomeUrl.FONTAWESOME_URL, 32);
	}

}
