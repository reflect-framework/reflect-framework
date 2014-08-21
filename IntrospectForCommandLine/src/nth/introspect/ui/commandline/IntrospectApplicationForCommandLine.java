package nth.introspect.ui.commandline;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.about.DefaultAboutProvider;
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

public abstract class IntrospectApplicationForCommandLine implements IntrospectApplication {

	private final String[] commandLineArguments;

	
	public IntrospectApplicationForCommandLine(String[] commandLineArguments) {
		this.commandLineArguments = commandLineArguments;
	}


	@Override
	public Class<? extends UserInterfaceProvider<?>> getUserInterfaceProviderClass() {
		return CommandLineUserInterfaceProvider.class;
	}


	@Override
	public Class<? extends DomainProvider> getDomainProviderClass() {
		return DefaultDomainProvider.class;
	}


	@Override
	public Class<? extends AboutProvider> getVersionProviderClass() {
		return DefaultAboutProvider.class;
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


	public String[] getCommandLineArguments() {
		return commandLineArguments;
	}


}
