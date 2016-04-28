package nth.introspect.layer5provider.reflection.behavior.icon;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import nth.introspect.layer5provider.path.url.ClassResourceUrl;

/**
 * 
 * <p>
 * This class represents a reference to a Icon, by getting the absolute URI to a
 * class resource.
 * </p>
 * <p>
 * String presentation:
 * <ul>
 * <li>Syntax String presentation:
 * introspectIconClassResource://&lt;canonicalClassName&gt;/&lt;resourceName&gt;
 * </li>
 * <li>Example String presentation:
 * introspectIconClassResource://com.acme.customer.Customer/person.png</li>
 * <li>Example AbsolutePath:
 * file://c:/MyProject/src/com/acme/customer/person.png</li>
 * </ul>
 * 
 * @deprecated use {@link ClassResourceUrl}
 * @author nilsth
 *
 */
public class IconUriClassResource {

	public static final String SCHEMA = "introspectIconClassResource";
	private final Class<?> ownerClass;
	private final String resourceName;
	private final URI absoluteUri;

	public IconUriClassResource(Class<?> ownerClass, String resourceName)
			throws URISyntaxException {
		this.ownerClass = ownerClass;
		this.resourceName = resourceName;
		this.absoluteUri = createAbsoluteUri();
	}

	public IconUriClassResource(String introspectIconURI)
			throws URISyntaxException {
		URI uri = new URI(introspectIconURI);
		if (SCHEMA.equals(uri.getScheme())) {
			this.ownerClass = getOwnerClass(uri);
			this.resourceName = uri.getPath().substring(1);
			this.absoluteUri = createAbsoluteUri();
		} else {
			throw new URISyntaxException(introspectIconURI,
					"URI must begin with " + SCHEMA + ":");
		}
	}

	private Class<?> getOwnerClass(URI uri) throws URISyntaxException {
		String canonicalClassName = uri.getAuthority();
		if (canonicalClassName == null
				|| canonicalClassName.trim().length() == 0) {
			throw new URISyntaxException(
					uri.toString(),
					SCHEMA
							+ ": must be followed by a canonical class name (e.g. com.acme.costomer.Customer)");
		}
		try {
			Class<?> ownerClass = Class.forName(canonicalClassName);
			return ownerClass;
		} catch (ClassNotFoundException e) {
			throw new URISyntaxException(
					uri.toString(),
					SCHEMA
							+ ": must be followed by a existing and accesable class name (e.g. com.acme.costomer.Customer)");
		}
	}

	public URI createAbsoluteUri() throws URISyntaxException {
		URL url = ownerClass.getResource(resourceName);
		if (url == null) {
			return null;
		} else {
			URI uri = url.toURI();
			InputStream is = ownerClass.getResourceAsStream(resourceName);
			try {
				is.available();
				return uri;
			} catch (Exception exception) {
				return null;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("introspectIconClassResource://");
		s.append(ownerClass.getCanonicalName());
		s.append("/");
		s.append(resourceName);
		return s.toString();
	}

	public URI getAbsoluteURI() {
		return absoluteUri;
	}

}
