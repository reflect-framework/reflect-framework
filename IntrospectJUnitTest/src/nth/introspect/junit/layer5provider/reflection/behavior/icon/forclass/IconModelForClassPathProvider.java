package nth.introspect.junit.layer5provider.reflection.behavior.icon.forclass;

import java.net.URI;
import java.net.URISyntaxException;

import nth.introspect.layer5provider.path.DefaultPathProvider;

public class IconModelForClassPathProvider extends DefaultPathProvider {

	public IconModelForClassPathProvider() throws URISyntaxException {
		super(createUriForThisPackageAsRootUri());
	}

	private static URI createUriForThisPackageAsRootUri()
			throws URISyntaxException {
		return IconModelForClassPathProvider.class.getResource("").toURI();
	}
}
