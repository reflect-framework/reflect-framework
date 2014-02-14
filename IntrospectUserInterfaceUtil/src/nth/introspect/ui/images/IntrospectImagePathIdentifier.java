package nth.introspect.ui.images;

import java.net.URI;

import nth.introspect.provider.path.id.PathID;

public class IntrospectImagePathIdentifier extends PathID {

	private URI path;

	public IntrospectImagePathIdentifier (String identfier) {
		super(identfier);
		try {
			path=IntrospectImagePathIdentifier.class.getResource(identfier).toURI();
		} catch (Throwable e) {
			path=null;
		}
	}
	
	@Override
	public URI getPath() {
		return path;
	}

}
