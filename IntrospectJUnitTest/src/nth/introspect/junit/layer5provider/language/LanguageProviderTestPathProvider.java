package nth.introspect.junit.layer5provider.language;

import java.net.URI;
import java.net.URISyntaxException;

import nth.introspect.layer5provider.path.DefaultPathProvider;

public class LanguageProviderTestPathProvider extends DefaultPathProvider {

	public LanguageProviderTestPathProvider() throws URISyntaxException {
		super(null,createUriForThisPackage(),null,null);
	}

	private static URI createUriForThisPackage()
			throws URISyntaxException {
		return LanguageProviderTestPathProvider.class.getResource("").toURI();
	}
}
