package nth.reflect.fw.layer5provider.url.fonticon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;
import nth.reflect.fw.layer5provider.url.ReflectUrl;

/**
 * <p>
 * A {@link FontIconUrl} is a {@link ReflectUrl} that helps to create a
 * reference to a {@link FontIcon}.
 * </p>
 * <p>
 * The format of a {@link FontIconUrl} is: reflect-font-icon://&lt;font
 * name&gt;/&lt;font fontIconUrl&gt#&lt;uni code&gt;
 * </p>
 * <p>
 * E.g.:
 * reflect-font-icon://FontAwesome/META-INF/resources/webjars/font-awesome/4.5.0/
 * fonts/fontawesome-webfont.ttf#F0C9 for a menu/navigation icon
 * </p>
 * 
 * <p>
 * {@link FontIconUrl}'s can be used in your code with e.g.
 * FontAwesomeUrl.NAVIGATION
 * </p>
 * 
 * @author nilsth
 */

public class FontIconUrl implements ReflectUrl {
	public static final String PROTOCOL_SUFFIX = "://";
	public static final String PROTOCOL = "reflect-font-icon";
	private final URL fontIconUrl;

	public FontIconUrl(String fontName, URL fontUrl, char uniCode) throws MalformedURLException {
		this(new URL(PROTOCOL, fontName, fontUrl.toString()));
	}

	
	public FontIconUrl(String fontIconUrl) throws MalformedURLException {
		this(new URL(fontIconUrl));
	}

	public FontIconUrl(URL fontIconUrl) throws MalformedURLException {
		this.fontIconUrl = fontIconUrl;
		verify();
	}

	private void verify() throws MalformedURLException {
		if (!PROTOCOL.equals(fontIconUrl.getProtocol())) {
			throw new MalformedURLException("The protocol must be: " + PROTOCOL);
		}

		String fontName = getFontName();
		if (fontName==null || fontName.trim().length()==0  ) {
		throw new MalformedURLException(
					"No font name defined in FontIconUrl. URL should end with: "+PROTOCOL+"://<fontName>");
		}
		
//		if (urlExists(getFontUrl())) {
//			throw new MalformedURLException(
//					"Font uri: " + fontIconUrl.getFile() + " does not exist.");
//		}

		String character = getCharacter();
		if (character==null || character.trim().length()==0   ) {
			throw new MalformedURLException(
					"No unicode character defined in FontIconUrl. URL should end with #\\<unicode character in Hex>: ");
		}
	}

	private boolean urlExists(URL url) {
		try {
			url.openStream();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public URL toInternalURL() {
		return fontIconUrl;
	}

	public String getFontName()  {
		String fontName = fontIconUrl.getHost();
		return fontName;
	}
	
	public URL getFontUrl() {
		String fontPath = fontIconUrl.getFile();
		URL url = FontIconUrl.class.getResource(fontPath);
		return url;
	}

	public String getCharacter() {
		String hexString=fontIconUrl.getRef();
		int hexInt=Integer.parseInt(hexString, 16);
		String character = new String(Character.toChars(hexInt));
		return character;
	}
	
//	public Font getFont() throws MalformedURLException {
//		String fontName=getFontName();
//		URL fontUrl=getFontUrl();
//		Font font=new Font(fontUrl, fontName, 32, false);
//		return font;
//	}
}