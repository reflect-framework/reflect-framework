package nth.reflect.javafx.control.style;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import nth.introspect.layer5provider.path.url.ReflectUrl;

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
	public URL toURL() {
		return styleSheetUrl;
	}
	
	@Override
	public String toString() {
		return STYLE_SHEET_URL;
	}
}