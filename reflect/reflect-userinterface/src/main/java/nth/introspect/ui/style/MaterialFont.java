package nth.introspect.ui.style;

import java.net.URL;

import nth.introspect.ui.style.basic.Font;
import nth.introspect.ui.style.fontawesome.FontAwesomeUrl;

/**
 * See
 * https://www.google.com/design/spec/style/typography.html#typography-typeface
 * 
 */

public class MaterialFont {

	private static final String ROBOTO_LIGHT_URL = "/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Light.ttf";
	private static final String ROBOTO_REGULAR_URL = "/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Regular.ttf";
	private static final String ROBOTO_MEDIUM_URL = "/META-INF/resources/webjars/roboto-fontface/0.4.3/fonts/Roboto-Medium.ttf";;
//	private static final String FONT_AWESOME_URL = "/META-INF/resources/webjars/font-awesome/4.5.0/fonts/fontawesome-webfont.ttf";

	private static final String ROBOTO = "Roboto";
	
	
	private static Font createFont(String path, int size) {
		URL url = MaterialFont.class.getResource(path);
		String name = getFontName(path);
		return createFont(name, path, size);
	}
	
	private static Font createFont(String name, String path, int size) {
		URL url = MaterialFont.class.getResource(path);
		Font font = new Font(url, name, size, false);
		return font;
	}

	private static String getFontName(String path) {
		int beginIndex=path.lastIndexOf("/")+1;
		int endIndex=path.indexOf(".",beginIndex);
		String name=path.substring(beginIndex, endIndex);
		name=name.replace("-", " ");
		return name;
	}

	public static Font getDisplay4(DisplayScale displayScale) {
		return createFont(ROBOTO_LIGHT_URL, displayScale.scale(112));
	}

	public static Font getDisplay3(DisplayScale displayScale) {
		return createFont(ROBOTO_REGULAR_URL, displayScale.scale(56));
	}

	public static Font getDisplay2(DisplayScale displayScale) {
		return createFont(ROBOTO_REGULAR_URL, displayScale.scale(45));
	}

	public static Font getDisplay1(DisplayScale displayScale) {
		return createFont(ROBOTO_REGULAR_URL, displayScale.scale(34));
	}

	public static Font getHeadLine(DisplayScale displayScale) {
		return createFont(ROBOTO_REGULAR_URL, displayScale.scale(24));
	}

	public static Font getTitle(DisplayScale displayScale) {
		return createFont(ROBOTO_MEDIUM_URL, displayScale.scale(24));
	}

	public static Font getSubHeading(DisplayScale displayScale) {
		return createFont(ROBOTO_REGULAR_URL, displayScale.scale(16));
	}

	public static Font getBody2(DisplayScale displayScale) {
		return createFont(ROBOTO_MEDIUM_URL, displayScale.scale(14));
	}

	public static Font getBody1(DisplayScale displayScale) {
		return createFont(ROBOTO_REGULAR_URL, displayScale.scale(14));
	}

	public static Font getCaption(DisplayScale displayScale) {
		return createFont(ROBOTO_REGULAR_URL, displayScale.scale(12));
	}

	public static Font getButton(DisplayScale displayScale) {
		return createFont(ROBOTO_MEDIUM_URL, displayScale.scale(14));
	}

	public static Font getFontAwesome() {
		return createFont(FontAwesomeUrl.FONTAWESOME_NAME, FontAwesomeUrl.FONTAWESOME_URL,32);
	}

}
