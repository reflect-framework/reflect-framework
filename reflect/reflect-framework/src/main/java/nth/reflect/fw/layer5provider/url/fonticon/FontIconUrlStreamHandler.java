package nth.reflect.fw.layer5provider.url.fonticon;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;

/**
 * The {@link FontIconUrlStreamHandler} resolves {@link FontIconUrl}s. {@insert FontIconUrl}
 * @author nilsth
 *
 */
public class FontIconUrlStreamHandler extends ReflectUrlStreamHandler {

	@Override
	public String getProtocol() {
		return FontIconUrl.PROTOCOL;
	}

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		FontIconUrl fontIconUrl = new FontIconUrl(url);
		URL fontUrl = fontIconUrl.getFontUrl();
		InputStream inputStream = fontUrl.openStream();
		return new URLConnection(url) {

			@Override
			public void connect() throws IOException {
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return inputStream;
			}

		};
	}

}
