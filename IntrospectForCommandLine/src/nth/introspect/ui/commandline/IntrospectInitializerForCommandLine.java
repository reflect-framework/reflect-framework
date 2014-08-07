package nth.introspect.ui.commandline;

import nth.introspect.initializer.IntrospectInitializer;
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

public class IntrospectInitializerForCommandLine extends IntrospectInitializer {

	private final String[] commandLineArguments;

	
	public IntrospectInitializerForCommandLine(Object application, String[] commandLineArguments) {
		super(application);
		this.commandLineArguments = commandLineArguments;
	}
	
	@Override
	public UserInterfaceProvider<?> createUserInterfaceProvider() {
		return new CommandLineUserInterfaceProvider(commandLineArguments);
	}

	@Override
	public DomainProvider createDomainProvider() {
		return new DefaultDomainProvider();
	}

	@Override
	public VersionProvider createVersionProvider() {
		return new DefaultVersionProvider(getApplication());
	}

	@Override
 	public PathProvider createPathProvider() {
		try {
			return new DefaultPathProvider(getApplication());
		} catch( Exception e) {
			return null;
		}
		
	}

	@Override
	public LanguageProvider createLanguageProvider() {
		return new DefaultLanguageProvider();
	}

	@Override
	public AuthorizationProvider createAuthorizationProvider() {
		return new DefaultAuthorizationProvider();
	}

	@Override
	public ValidationProvider createValidationProvider() {
		return new DefaultValidationProvider();
	}


}
