package nth.reflect.fw.javafx.control.style;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.layer5provider.url.ReflectUrl;

/**
 * <p>
 * A {@link StyleSheetUrl} is a {@link ReflectUrl} that helps to create a
 * reference to a Cascading Style Sheet that is generated with code
 * </p>
 * <p>
 * The format of a {@link StyleSheetUrl} is: reflectfxstylesheet:stylesheet.css
 * </p>
 * 
 * @author nilsth
 *
 */

public class StyleSheetUrl implements ReflectUrl {
	public static String PROTOCOL = "reflectfxstylesheet";
	public static String FILE = "stylesheet.css";
	public static String STYLE_SHEET_URL =PROTOCOL+":"+FILE;
	private URL styleSheetUrl;


	public StyleSheetUrl()  {
		try {
			styleSheetUrl=new URL(STYLE_SHEET_URL);
		} catch (MalformedURLException e) {
			//can not go wrong
		}
		
	}


	@Override
	public URL toInternalURL() {
		return styleSheetUrl;
	}
	
	@Override
	public String toString() {
		return STYLE_SHEET_URL;
	}
}