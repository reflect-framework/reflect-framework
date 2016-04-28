package nth.introspect.layer5provider.path.url;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

/**
 * <p>
 * A {@link ClassResourceUrl} is a {@link ReflectUrl} that helps to create a
 * reference to a class resource (See {@link Class#getResource(String)})
 * </p>
 * <p>
 * The format of a {@link ClassResourceUrl} is: reflectclassresource://&lt;class
 * path&gt;/&lt;resource name&gt;
 * </p>
 * <p>
 * E.g.: reflectclassresource://com.acme.SalesApp/sales.png;
 * </p>
 * 
 * @author nilsth
 *
 */

public class ClassResourceUrl implements ReflectUrl {
	public static String PROTOCOL = "reflectclassresource";
	private final URL classResourceUrl;

	public ClassResourceUrl(Class<?> resourceClass, String resourceFile)
			throws MalformedURLException {
		this(new URL(PROTOCOL, resourceClass.getCanonicalName(), resourceFile));
	}

	public ClassResourceUrl(String classResourceUrl) throws MalformedURLException {
		this(new URL(classResourceUrl));
	}

	public ClassResourceUrl(URL classResourceUrl) throws MalformedURLException {
		this.classResourceUrl = classResourceUrl;
		verify();
	}

	private void verify() throws MalformedURLException {
		if (!PROTOCOL.equals(classResourceUrl.getProtocol())) {
			throw new MalformedURLException("The protocol must be: " + PROTOCOL);
		}

		Class<?> resourceClass = getResourceClass();

		String resourceFile = getResourceFile();

		URL resource = resourceClass.getResource(resourceFile);
		File file;
		try {
			file = new File(resource.toURI());
			if (!file.exists()) {
				throw new MalformedURLException("Unknown resource file: " + resourceFile);
			}
		} catch (URISyntaxException e) {
			throw new MalformedURLException("Invalid file name: " + resourceFile);
		}
	}

	public Class<?> getResourceClass() throws MalformedURLException {
		String className = classResourceUrl.getHost();
		Class<?> resourceClass = null;
		try {
			resourceClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new MalformedURLException("Unknown class: " + className);
		}
		return resourceClass;
	}

	public String getResourceFile() {
		return classResourceUrl.getFile();
	}

	@Override
	public URL toURL() {
		return classResourceUrl;
	}
}