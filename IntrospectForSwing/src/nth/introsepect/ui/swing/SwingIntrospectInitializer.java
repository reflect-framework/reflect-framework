package nth.introsepect.ui.swing;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.initializator.IntrospectInitializer;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.provider.domain.DefaultDomainProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.info.DefaultInfoProvider;
import nth.introspect.provider.info.InfoProvider;
import nth.introspect.provider.language.DefaultLanguageProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.DefaultPathProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

public class SwingIntrospectInitializer implements IntrospectInitializer {

	private final Object application;
	private final List<Class<?>> serviceClasses;

	public SwingIntrospectInitializer(Object application) {
		this.application = application;
		this.serviceClasses=new ArrayList<Class<?>>();
	}
	
	@Override
	public DomainProvider createDomainProvider() {
		return new DefaultDomainProvider(serviceClasses);
	}

	@Override
	public AuthorizationProvider createAuthorizationProvider() {
		return new DefaultAuthorizationProvider();
	}

	@Override
	public ValidationProvider createValidationProvider() {
		return null;
	}

	@Override
	public LanguageProvider createLanguageProvider() {
		return new DefaultLanguageProvider();
	}

	@Override
	public InfoProvider createInfoProvider() {
		return new DefaultInfoProvider(application);
	}

	@Override
	public PathProvider createPathProvider() {
		try {
			return new DefaultPathProvider(application);
		} catch (URISyntaxException e) {
			return null;
		}
	}

	@Override
	public UserInterfaceProvider<?> createUserInterfaceProvider() {
		return new SwingUserinterfaceProvider(application);
		

	}

	@Override
	public void addServiceClass(Class<?> serviceObject) {
		serviceClasses.add(serviceObject);
	}

}
