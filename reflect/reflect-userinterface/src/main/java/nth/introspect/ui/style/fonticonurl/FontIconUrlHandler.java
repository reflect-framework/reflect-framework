package nth.introspect.ui.style.fonticonurl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import nth.introspect.layer5provider.path.url.ReflectUrlConnection;

public class FontIconUrlHandler extends ReflectUrlConnection {

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
