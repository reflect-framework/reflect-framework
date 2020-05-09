package nth.reflect.fw.layer5provider.version;

import nth.reflect.fw.layer5provider.ProviderContainer;

public class DefaultVersionProvider implements VersionProvider {

	private ApplicationVersionInfo applicationVersionInfo;

	public DefaultVersionProvider (ProviderContainer providerContainer) {
		applicationVersionInfo = new ApplicationVersionInfo(providerContainer);
	}

	@Override
	public ApplicationVersionInfo getApplicationVersionInfo() {
		return applicationVersionInfo;
	}

}