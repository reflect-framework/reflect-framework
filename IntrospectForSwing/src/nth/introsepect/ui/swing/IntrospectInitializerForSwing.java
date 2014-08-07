package nth.introsepect.ui.swing;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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

public class IntrospectInitializerForSwing extends IntrospectInitializer {


	public IntrospectInitializerForSwing(Object application) {
		super(application);
	}

	@Override
	public DomainProvider createDomainProvider() {
		return new DefaultDomainProvider(getFrontEndServiceClasses());
	}

	@Override
	public AuthorizationProvider createAuthorizationProvider() {
		return new DefaultAuthorizationProvider();
	}

	@Override
	public ValidationProvider createValidationProvider() {
		return new DefaultValidationProvider();
	}

	@Override
	public LanguageProvider createLanguageProvider() {
		return new DefaultLanguageProvider();
	}

	@Override
	public VersionProvider createVersionProvider() {
		return new DefaultVersionProvider(getApplication());
	}

	@Override
	public PathProvider createPathProvider() {
		try {
			return new DefaultPathProvider(getApplication());
		} catch (URISyntaxException e) {
			return null;
		}
	}

	@Override
	public UserInterfaceProvider<?> createUserInterfaceProvider() {
		return new SwingUserinterfaceProvider(getApplication());
	}


}
