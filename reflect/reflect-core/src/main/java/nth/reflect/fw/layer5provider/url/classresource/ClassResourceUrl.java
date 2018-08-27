package nth.reflect.fw.layer5provider.url.classresource;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import nth.reflect.fw.layer5provider.url.ReflectUrl;

/**
 * <p>
 * A {@link ClassResourceUrl} is a {@link ReflectUrl} that helps to create a
 * reference to a class resource (See {@link Class#getResource(String)})
 * </p>
 * <p>
 * The format of a {@link ClassResourceUrl} is:
 * reflect-class-resource://&lt;class path&gt;/&lt;resource name&gt;
 * </p>
 * <p>
 * E.g.: reflect-class-resource://com.acme.SalesApp/sales.png; (for a sales.png
 * file in the com.acme package, next to the SalesApp class)
 * </p>
 * 
 * @author nilsth
 *
 */

public class ClassResourceUrl implements ReflectUrl {
	public static String PROTOCOL = "reflect-class-resource";
	private final URL classResourceUrl;
	private final String resourceFile;

	public ClassResourceUrl(Class<?> resourceClass, String resourceFile) throws MalformedURLException {
		this(new URL(PROTOCOL, resourceClass.getCanonicalName(), resourceFile));
	}

	public ClassResourceUrl(String classResourceUrl) throws MalformedURLException {
		this(new URL(classResourceUrl));
	}

	public ClassResourceUrl(URL classResourceUrl) throws MalformedURLException {
		this.classResourceUrl = classResourceUrl;
		this.resourceFile = removeFirstSlash(classResourceUrl.getFile());
		verify();
	}

	private void verify() throws MalformedURLException {
		if (!PROTOCOL.equals(classResourceUrl.getProtocol())) {
			throw new MalformedURLException("The protocol must be: " + PROTOCOL);
		}

		Class<?> resourceClass = getResourceClass();

		String resourceFile = getResourceFile();

		URL resource = resourceClass.getResource(resourceFile);
		File file = null;
		try {
			if (resource != null) {
				file = new File(resource.toURI());
			}
			if (resource == null || file == null || !file.exists()) {
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
		return resourceFile;
	}

	private String removeFirstSlash(String resourceFile) {
		if (resourceFile == null) {
			return null;
		} else if (resourceFile.startsWith("/")) {
			return resourceFile.substring(1);
		} else {
			return resourceFile;
		}
	}

	@Override
	public URL toInternalURL() {
		return classResourceUrl;
	}

}