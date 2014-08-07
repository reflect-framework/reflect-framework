package nth.introspect.provider.version;

import java.net.URI;
import java.util.List;

import nth.introspect.provider.Provider;

public interface VersionProvider extends Provider{

	Object getApplication();
	String getApplicationTitle();
	URI getApplicationIconURI();

	List<ProviderInfo> getProviderInfos();
	
	ProviderInfo getUserInterfaceProviderInfo();

	ProviderInfo getAuthorizationProviderInfo();

	ProviderInfo getDomainProviderInfo();
	
	ProviderInfo getValidationProviderInfo();

	ProviderInfo getLanguageProviderInfo();

	ProviderInfo getPathProviderInfo();
	
	//List<ProviderInfo> getDataAccessProviders() did not include this because its being populated during run time

}