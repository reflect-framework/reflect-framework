package nth.reflect.fw.layer5provider.url.classresource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import nth.reflect.fw.layer5provider.url.UrlProvider;
/**
 * A {@link ClassResourceUrlProvider} handles a {@link ClassResourceUrl}.
 * 
 * {@insert ClassResourceUrl}
 * @author nilsth
 *
 */
public class ClassResourceUrlProvider extends UrlProvider {

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
	
	@Override
	protected String toExternalForm(URL url) {
		try{
			ClassResourceUrl classResourceUrl = new ClassResourceUrl(url);
			Class<?> resourceClass = classResourceUrl.getResourceClass();
			String resourecFile = classResourceUrl.getResourceFile();
			URL resourceUrl = resourceClass.getResource(resourecFile);
			return resourceUrl.toExternalForm();
		} catch (Exception exception) {
			return null;
		}
		
	}

}
