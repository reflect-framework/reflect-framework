package nth.introspect.layer5provider;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;

/**
 * This {@link DependencyInjectionContainer} represents the {@link ProviderLayer}
 * @author nilsth
 *
 */
public class ProviderContainer extends DependencyInjectionContainer {

	public ProviderContainer(IntrospectApplication application) {
		super();
		
		add(application);
		
		// add provider classes
		add(application.getPathProviderClass());
		add(application.getLanguageProviderClass());
		add(application.getValidationProviderClass());
		add(application.getAuthorizationProviderClass());
		add(application.getNotificationProviderClass());
		add(application.getReflectionProviderClass());
		add(application.getVersionProviderClass());
	}

	

}
