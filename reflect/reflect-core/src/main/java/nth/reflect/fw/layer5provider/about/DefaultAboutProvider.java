package nth.reflect.fw.layer5provider.about;

import nth.reflect.fw.layer5provider.ProviderContainer;

public class DefaultAboutProvider implements AboutProvider {

	private ApplicationVersionInfo applicationVersionInfo;

	public DefaultAboutProvider (ProviderContainer providerContainer) {
		applicationVersionInfo = new ApplicationVersionInfo(providerContainer);
	}

	@Override
	public ApplicationVersionInfo getApplicationVersionInfo() {
		return applicationVersionInfo;
	}

}