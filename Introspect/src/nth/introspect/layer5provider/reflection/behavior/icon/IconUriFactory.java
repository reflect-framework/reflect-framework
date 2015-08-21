package nth.introspect.layer5provider.reflection.behavior.icon;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Creates an iconUri from a string, using either {@link IconUriClassResource}
 * or {@link IconUriRelativePath}
 * 
 * @author nilsth
 *
 */
public class IconUriFactory {

	public static URI create(String iconURI, URI imageUri)
			throws URISyntaxException {
		URI uri = new URI(iconURI);
		if (IconUriClassResource.SCHEMA.equals(uri.getScheme())) {
			return new IconUriClassResource(iconURI).getAbsoluteURI();
		} else if (uri.isAbsolute()) {
			return checkIfExists(uri);
		} else {
			return createAbsoluteUri(imageUri, uri);
		}
	}

	private static URI createAbsoluteUri(URI imageUri, URI relativeUri) {
		URI uri = imageUri.resolve(relativeUri);
		return checkIfExists(uri);
	}

	private static URI checkIfExists(URI uri) {
		File file = new File(uri);
		if (file.exists()) {
			return uri;
		} else {
			return null;
		}
	}

}
