package nth.reflect.fw.javafx.control.style;

import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.layer5provider.url.ReflectUrl;

/**
 * <p>
 * A {@link RfxStyleSheetUrl} is a {@link ReflectUrl} that helps to create a
 * reference to a Cascading Style Sheet that is generated with code
 * </p>
 * <p>
 * The format of a {@link RfxStyleSheetUrl} is: reflectfxstylesheet:stylesheet.css
 * </p>
 * 
 * @author nilsth
 *
 */

public class RfxStyleSheetUrl implements ReflectUrl {
	public static String PROTOCOL = "reflectfxstylesheet";
	public static String FILE = "stylesheet.css";
	public static String STYLE_SHEET_URL =PROTOCOL+":"+FILE;
	private URL styleSheetUrl;


	public RfxStyleSheetUrl()  {
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