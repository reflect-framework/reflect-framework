package nth.introspect.layer5provider.url.application;

import java.net.MalformedURLException;
import java.net.URL;

import nth.introspect.layer5provider.url.ReflectUrl;

/**
 * <p>
 * A {@link ApplicationUrl} is a {@link ReflectUrl} that helps to create a
 * reference to the application folder e.g. to get a resource file
 * </p>
 * <p>
 * The format of a {@link ApplicationUrl} is: reflect-application-url://&lt;relative path to resource&gt;
 * </p>
 * <p>
 * E.g.: reflect-application-url://images/sales.png  (for a reference to the sales.png file in the application sub folder images) 
 * </p>
 * 
 * @author nilsth
 *
 */

public class ApplicationUrl implements ReflectUrl {
	public static String PROTOCOL = "reflect-application-url";
	private final URL applicationUrl;

	/**
	 * Creates a reference to the application folder
	 * @param relativePath If you have no relative path (a file in the application folder) use "/" as relative path.
	 * @param resourceFile
	 * @throws MalformedURLException
	 */
	public ApplicationUrl(String relativePath, String resourceFile)
			throws MalformedURLException {
		this(new URL(PROTOCOL, relativePath, resourceFile));
	}


	public ApplicationUrl() throws MalformedURLException {
		this("/","");
	}
	
	public ApplicationUrl(String applicationUrl) throws MalformedURLException {
		this(new URL(applicationUrl));
	}

	public ApplicationUrl(URL applicationUrl) throws MalformedURLException {
		this.applicationUrl = applicationUrl;
		verify();
	}

	private void verify() throws MalformedURLException {
		if (!PROTOCOL.equals(applicationUrl.getProtocol())) {
			throw new MalformedURLException("The protocol must be: " + PROTOCOL);
		}
	}

	public String getRelativePath() throws MalformedURLException {
		String relativePath = applicationUrl.getHost();
		return relativePath;
	}

	public String getResourceFile() {
		return applicationUrl.getFile();
	}

	@Override
	public URL toInternalURL() {
		return applicationUrl;
	}

}