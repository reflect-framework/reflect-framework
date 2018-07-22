package nth.reflect.fw.layer5provider.about;

import nth.reflect.fw.ReflectApplication;

public class DefaultAboutProvider implements AboutProvider {

	private final ReflectApplication application;

	public DefaultAboutProvider (ReflectApplication application) {
		this.application = application;
	}

	@Override
	public About about() {
		return new About(application);
	}

}