package nth.introspect.ui.swing;

import nth.introspect.Introspect;
import nth.introspect.application.IntrospectApplication;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.provider.domain.DefaultDomainProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.language.DefaultLanguageProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.DefaultPathProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.DefaultValidationProvider;
import nth.introspect.provider.validation.ValidationProvider;
import nth.introspect.provider.version.DefaultVersionProvider;
import nth.introspect.provider.version.VersionProvider;

public abstract class IntrospectApplicationForSwing implements
		IntrospectApplication {

	private final String[] commandLineArguments;

	public IntrospectApplicationForSwing(String[] commandLineArguments) {
		this.commandLineArguments = commandLineArguments;
		Introspect.init(this);
	}
	
	@Override
	public Class<? extends UserInterfaceProvider<?>> getUserInterfaceProviderClass() {
		return SwingUserinterfaceProvider.class;
	}

	@Override
	public Class<? extends DomainProvider> getDomainProviderClass() {
		return DefaultDomainProvider.class;
	}

	@Override
	public Class<? extends VersionProvider> getVersionProviderClass() {
		return DefaultVersionProvider.class;
	}

	@Override
	public Class<? extends PathProvider> getPathProviderClass() {
		return DefaultPathProvider.class;
	}

	@Override
	public Class<? extends LanguageProvider> getLanguageProviderClass() {
		return DefaultLanguageProvider.class;
	}

	@Override
	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
		return DefaultAuthorizationProvider.class;
	}

	@Override
	public Class<? extends ValidationProvider> getValidationProviderClass() {
		return DefaultValidationProvider.class;
	}

}
