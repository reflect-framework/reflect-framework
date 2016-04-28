package nth.introspect.layer5provider.path.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ClassResourceUrlHandler extends ReflectUrlConnection {

	@Override
	public String getProtocol() {
		return ClassResourceUrl.PROTOCOL;
	}

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		ClassResourceUrl classResourceUrl = new ClassResourceUrl(url);
		Class<?> resourceClass = classResourceUrl.getResourceClass();
		String resourecFile = classResourceUrl.getResourceFile();
		InputStream resourceStream = resourceClass.getResourceAsStream(resourecFile);
		return new URLConnection(url) {

			@Override
			public void connect() throws IOException {
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return resourceStream;
			}

		};
	}

}
