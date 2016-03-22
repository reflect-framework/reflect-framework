package nth.introspect.junit.layer5provider.reflection.behavior.icon.foractionmethod;

import java.net.URI;
import java.net.URISyntaxException;

import nth.introspect.layer5provider.path.DefaultPathProvider;

public class IconModelForActionMethodPathProvider extends DefaultPathProvider {

	public IconModelForActionMethodPathProvider() throws URISyntaxException {
		super(createUriForThisPackageAsRootUri());
	}

	private static URI createUriForThisPackageAsRootUri()
			throws URISyntaxException {
		return IconModelForActionMethodPathProvider.class.getResource("").toURI();
	}
}
