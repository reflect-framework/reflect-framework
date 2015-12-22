package nth.introspect.layer5provider.reflection.behavior.icon;

import java.net.URI;

public class IconUriModel implements IconModel{

	private final URI uri;

	public IconUriModel(URI uri) {
		this.uri = uri;
	}


	@Override
	public URI getURI(Object obj) {
		return uri;
	}

}
