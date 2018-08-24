package nth.reflect.fw.layer5provider.url.application;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.url.UrlProvider;

/**
 * A {@link ApplicationUrlProvider} handles a {@link ApplicationUrl}.
 * 
 * {@insert ApplicationUrl}
 * 
 * @author nilsth
 *
 */
public class ApplicationUrlProvider extends UrlProvider {

	private static final String JAR_EXTENTION = ".JAR";
	private static final String EXE_EXTENTION = ".EXE";
	private URL applicationUrl;

	public ApplicationUrlProvider(ReflectApplication application) {
		applicationUrl = application.getClass().getProtectionDomain().getCodeSource().getLocation();
		String fileName = applicationUrl.getFile().toUpperCase();
		if (fileName.endsWith(JAR_EXTENTION) || fileName.endsWith(EXE_EXTENTION)) {
			try {
				applicationUrl = getParentUrl(applicationUrl);
			} catch (MalformedURLException e) {
			}
		}
	}

	private URL getParentUrl(URL url) throws MalformedURLException {
		String parentURL = url.toString();
		if ((parentURL == null) || parentURL.equals("") || parentURL.equals("/")) {
			return new URL("");
		}

		parentURL=parentURL.replace("\\", "/");
		int lastSlashPos = parentURL.lastIndexOf('/')+1;
		if (lastSlashPos >= 0) {
			parentURL = parentURL.substring(0, lastSlashPos); // strip off the slash and everything behind
			return new URL(parentURL);
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
		String file = url.getFile();
		if (relativePath != null && !relativePath.equals(file)) {
			externalForm.append(relativePath);
		}

		//TODO check for double slashes and add slash if needed
		if (file != null) {
			externalForm.append(file);
		}
		return externalForm.toString();
	}

}
