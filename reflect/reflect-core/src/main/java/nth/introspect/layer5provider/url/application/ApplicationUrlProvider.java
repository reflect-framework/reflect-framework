package nth.introspect.layer5provider.url.application;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer5provider.url.UrlProvider;

/**
 * A {@link ApplicationUrlProvider} handles a {@link ApplicationUrl}.
 * 
 * {@include ApplicationUrl}
 * 
 * @author nilsth
 *
 */
public class ApplicationUrlProvider extends UrlProvider {

	private static final String JAR_EXTENTION = ".JAR";
	private static final String EXE_EXTENTION = ".EXE";
	private URL applicationUrl;

	public ApplicationUrlProvider(IntrospectApplication application) {
		URL applicationUrl = application.getClass().getProtectionDomain().getCodeSource().getLocation();
		String fileName = applicationUrl.getFile().toUpperCase();
		if (fileName.endsWith(JAR_EXTENTION) || fileName.endsWith(EXE_EXTENTION)) {
			try {
				applicationUrl = getParentUrl(applicationUrl);
			} catch (MalformedURLException e) {
			}
		}
	}

	private URL getParentUrl(URL url) throws MalformedURLException {
		String path = url.getPath();
		if ((path == null) || path.equals("") || path.equals("/")) {
			return new URL("");
		}

		int lastSlashPos = path.lastIndexOf('/');
		if (lastSlashPos >= 0) {
			path = path.substring(0, lastSlashPos); // strip off the slash
			return new URL(path);
		} else {
			return new URL("");
		}
	}

	@Override
	public String getProtocol() {
		return ApplicationUrl.PROTOCOL;
	}

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		String externalForm = toExternalForm(url);
		URL externalURL = new URL(externalForm);
		return new DataURLConnection(externalURL);
	}

	@Override
	protected String toExternalForm(URL url) {
		StringBuilder externalForm = new StringBuilder(applicationUrl.toString());

		//TODO check for double slashes
		String relativePath = url.getPath();
		if (relativePath != null) {
			externalForm.append(relativePath);
		}

		//TODO check for double slashes and add slash if needed
		String file = url.getFile();
		if (file != null) {
			externalForm.append(file);
		}
		return externalForm.toString();
	}

}
