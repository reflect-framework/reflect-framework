package nth.introspect.layer5provider.path.url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * <p>A {@link FontIconUrl} is a {@link ReflectUrl} that helps to create a reference to a font icon.
 *</p>
 * <p>
 * Font icons are icons included in a font (a unicode character). Fonts have
 * many advantages over bitmap images:
 * <ul> 
 * <li>Browsers are usually faster in rendering
 * fonts than loading image files.</li>
 * <li>Web fonts are vector graphics, so they are
 * scalable.</li> 
 * <li>As font icons are text characters, you can define their color with
 * a style sheet by the regular foreground color property.</li>
 * </ul>
 * </p>
 * You can easily use glyphs in existing fonts as icons, or create your own. You
 * are free to use any of the many ways to create icons and embed them into
 * fonts. Here, we give basic instructions for using the IcoMoon service, where
 * you can pick icons from a large library of well-designed icons.
 * <p>
 * </p>

 * 
 * </p>
 * <p>
 * The format of a {@link FontIconUrl} is: reflectfonticon://&lt;font name&gt;/&lt;font fontIconUrl&gt#&lt;uni code&gt;
 * </p>
 * <p>
 * E.g.: reflectfonticon://FontAwesome/META-INF/resources/webjars/font-awesome/4.5.0/fonts/fontawesome-webfont.ttf#uf0c9 for a menu/noavigation icon
 * </p>
 * 
 * <p>
 * {@link FontIconUrl}'s can be used in your code with e.g. FontAwesomeUrl.NAVIGATION
 * </p>
 * @author nilsth
 */

public class FontIconUrl  implements ReflectUrl {
	public static String PROTOCOL = "reflectfonticon";
	private final URL fontIconUrl;

	public FontIconUrl(String fontName, URL fontUrl, char uniCode) throws MalformedURLException {
		this ( new URL(PROTOCOL, fontName, fontUrl.toString()));
	}

	public FontIconUrl(String fontIconUrl) throws MalformedURLException {
		this( new URL(fontIconUrl));
	}
	
	public FontIconUrl(URL fontIconUrl) throws MalformedURLException {
		this.fontIconUrl=fontIconUrl;
		verify();
	}

	private void verify() throws MalformedURLException {
		if (!PROTOCOL.equals(fontIconUrl.getProtocol())) {
			throw new MalformedURLException("The protocol must be: " + PROTOCOL);
		}

		File file;
		try {
			URI fontUri = getFontUrl().toURI();
			file = new File(fontUri);
			if (!file.exists()) {
				throw new MalformedURLException("Unknown font uri: " + fontIconUrl.getFile());
			}
		} catch (URISyntaxException e) {
			throw new MalformedURLException("Invalid font uri: " + fontIconUrl.getFile());
		}
		
		//TODO test unicode???
	}

	@Override
	public URL toURL() {
		return fontIconUrl;
	}

	public URL getFontUrl() throws MalformedURLException {
		String fontPath = fontIconUrl.getFile();
		URL fontUrl = new URL(fontPath);
		return fontUrl;
	}
}