package nth.introspect.provider.about;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

public class About extends VersionInfo {


	private IntrospectApplication application;

	public About(IntrospectApplication introspectApplication) {
		super(introspectApplication.getClass());
		this.application = introspectApplication;
	}
	
	@GenericReturnType(VersionInfo.class)
	public List<VersionInfo> getProviders() {
		List<VersionInfo> providerInfos = new ArrayList<VersionInfo>();
		providerInfos.add(getAuthorizationProviderInfo());
		providerInfos.add(getDomainInfoProviderInfo());
		providerInfos.add(getLanguageProviderInfo());
		providerInfos.add(getPathProviderInfo());
		providerInfos.add(getValidationProviderInfo());
		providerInfos.add(getUserInterfaceProviderInfo());
		return providerInfos;
	}


	private VersionInfo getValidationProviderInfo() {
		Class<? extends ValidationProvider> validationProviderClass = application.getValidationProviderClass();
		return new VersionInfo(validationProviderClass);
	}

	private VersionInfo getAuthorizationProviderInfo() {
		Class<? extends AuthorizationProvider> authorizationProviderClass = application.getAuthorizationProviderClass();
		return new VersionInfo(authorizationProviderClass);
	}

	private VersionInfo getDomainInfoProviderInfo() {
		Class<? extends DomainInfoProvider> domainInfoProviderClass = application.getDomainInfoProviderClass();
		return new VersionInfo(domainInfoProviderClass);
	}

	private VersionInfo getLanguageProviderInfo() {
		Class<? extends LanguageProvider> languageProviderClass = application.getLanguageProviderClass();
		return new VersionInfo(languageProviderClass);
	}

	private VersionInfo getPathProviderInfo() {
		Class<? extends PathProvider> pathProviderClass = application.getPathProviderClass();
		return new VersionInfo(pathProviderClass);
	}

	private VersionInfo getUserInterfaceProviderInfo() {
		Class<? extends UserInterfaceProvider<?>> userInterfaceProviderClass = application.getUserInterfaceProviderClass();
		return new VersionInfo(userInterfaceProviderClass);
	}

}
