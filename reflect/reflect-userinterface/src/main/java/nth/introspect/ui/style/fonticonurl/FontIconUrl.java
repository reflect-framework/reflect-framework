package nth.introspect.ui.style.fonticonurl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import nth.introspect.layer5provider.url.ReflectUrl;
import nth.introspect.ui.style.basic.Font;

/**
 * <p>
 * A {@link FontIconUrl} is a {@link ReflectUrl} that helps to create a
 * reference to a font icon.
 * </p>
 * <p>
 * Font icons are icons included in a font (a unicode character). Fonts have
 * many advantages over bitmap images:
 * <ul>
 * <li>Browsers are usually faster in rendering fonts than loading image files.
 * </li>
 * <li>Web fonts are vector graphics, so they are scalable.</li>
 * <li>As font icons are text characters, you can define their color with a
 * style sheet by the regular foreground color property.</li>
 * </ul>
 * </p>
 * You can easily use glyphs in existing fonts as icons, or create your own. You
 * are free to use any of the many ways to create icons and embed them into
 * fonts. Here, we give basic instructions for using the IcoMoon service, where
 * you can pick icons from a large library of well-designed icons.
 * <p>
 * </p>
 * 
 * 
 * </p>
 * <p>
 * The format of a {@link FontIconUrl} is: reflectfonticon://&lt;font
 * name&gt;/&lt;font fontIconUrl&gt#&lt;uni code&gt;
 * </p>
 * <p>
 * E.g.:
 * reflectfonticon://FontAwesomeUrl/META-INF/resources/webjars/font-awesome/4.5.0/
 * fonts/fontawesome-webfont.ttf#\uf0c9 for a menu/navigation icon
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
	public static final String PROTOCOL = "reflectfonticon";
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
	
	public URL getFontUrl() throws MalformedURLException {
		String fontPath = fontIconUrl.getFile();
		URL url = FontIconUrl.class.getResource(fontPath);
		return url;
		
		
//		String fontPath = fontIconUrl.getFile();
//		URL fontUrl = new URL( fontPath);
//		return fontUrl;
	}

	public String getCharacter() {
		String hexString=fontIconUrl.getRef();
		int hexInt=Integer.parseInt(hexString, 16);
		String character = new String(Character.toChars(hexInt));
		return character;
	}
	
	public Font getFont() throws MalformedURLException {
		String fontName=getFontName();
		URL fontUrl=getFontUrl();
		Font font=new Font(fontUrl, fontName, 32, false);
		return font;
	}
}