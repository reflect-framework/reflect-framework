package nth.introspect.provider.info;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInForm;
import nth.introspect.provider.domain.info.valuemodel.annotations.VisibleInTable;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

public class DefaultInfoProvider implements InfoProvider {

	private final Object application;
	private ClassInfo applicationClassInfo;

	public DefaultInfoProvider (Object application) {
		this.application = application;
		applicationClassInfo=Introspect.getDomainProvider().getClassInfo(application.getClass());
	}

	@GenericReturnType(ProviderInfo.class)
	@Override
	public List<ProviderInfo> getProviderInfos() {
		List<ProviderInfo> providerInfos = new ArrayList<ProviderInfo>();
		providerInfos.add(getAuthorizationProviderInfo());
		providerInfos.add(getDomainProviderInfo());
		providerInfos.add(getLanguageProviderInfo());
		providerInfos.add(getPathProviderInfo());
		providerInfos.add(getValidationProviderInfo());
		providerInfos.add(getUserInterfaceProviderInfo());
		return providerInfos;
	}

	@VisibleInForm(false)
	@Override
	public ProviderInfo getValidationProviderInfo() {
		ValidationProvider validationProvider = Introspect.getValidationProvider();
		return new ProviderInfo(validationProvider, ValidationProvider.class);
	}

	@VisibleInForm(false)
	@Override
	public ProviderInfo getAuthorizationProviderInfo() {
		AuthorizationProvider authorizationProvider = Introspect.getAuthorizationProvider();
		return new ProviderInfo(authorizationProvider, AuthorizationProvider.class);
	}

	@VisibleInForm(false)
	@Override
	public ProviderInfo getDomainProviderInfo() {
		DomainProvider domainProvider = Introspect.getDomainProvider();
		return new ProviderInfo(domainProvider, DomainProvider.class);
	}

	@VisibleInForm(false)
	@Override
	public ProviderInfo getLanguageProviderInfo() {
		LanguageProvider languageProvider = Introspect.getLanguageProvider();
		return new ProviderInfo(languageProvider, LanguageProvider.class);
	}

	@VisibleInForm(false)
	@Override
	public ProviderInfo getPathProviderInfo() {
		PathProvider pathProvider = Introspect.getPathProvider();
		return new ProviderInfo(pathProvider, PathProvider.class);
	}

	
	@VisibleInForm(false)
	@Override
	public ProviderInfo getUserInterfaceProviderInfo() {
		UserInterfaceProvider<?> userInterfaceProvider = Introspect.getUserInterfaceProvider();
		return new ProviderInfo(userInterfaceProvider, UserInterfaceProvider.class);
	}

	@VisibleInForm(false)
	@VisibleInTable(false)
	@Override
	public Object getApplication() {
		return application;
	}

	@Override
	public String getApplicationTitle() {
		return applicationClassInfo.getText();
	}
	
	@VisibleInForm(false)
	@Override
	public URI getApplicationIconURI() {
		return applicationClassInfo.getIconURI(application);
	}

}