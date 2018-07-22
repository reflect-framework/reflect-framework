package nth.reflect.fw.ui.style.fonticonurl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import nth.reflect.fw.layer5provider.url.UrlProvider;

public class FontIconUrlHandler extends UrlProvider {

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
