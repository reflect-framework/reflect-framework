package nth.introspect.provider.about;

import nth.introspect.application.IntrospectApplication;

public class DefaultAboutProvider implements AboutProvider {

	private final IntrospectApplication application;

	public DefaultAboutProvider (IntrospectApplication application) {
		this.application = application;
	}

	@Override
	public About about() {
		return new About(application);
	}

}