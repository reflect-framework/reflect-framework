package nth.introspect.ui.junit;

import java.net.URISyntaxException;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer5provider.path.DefaultPathProvider;

public class PathProviderForJUnit extends DefaultPathProvider {

	public PathProviderForJUnit(IntrospectApplication application)
			throws URISyntaxException {
		super(((IntrospectApplicationForJUnit) application).getIntrospectApplicationClass());
	}


	
}
