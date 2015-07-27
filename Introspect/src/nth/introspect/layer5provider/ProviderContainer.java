package nth.introspect.layer5provider;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;

/**
 * This {@link IntrospectContainer} represents the {@link ProviderLayer}
 * @author nilsth
 *
 */
public class ProviderContainer extends IntrospectContainer {

	public ProviderContainer(IntrospectApplication application) {
		super();
		
		add(application);
		
		// add provider classes
		//TODO throw new ProviderNotDefined(application, providerType); application.get...providerclass returns null
		add(application.getPathProviderClass());
		add(application.getLanguageProviderClass());
		add(application.getValidationProviderClass());
		add(application.getAuthorizationProviderClass());
		add(application.getNotificationProviderClass());
		add(application.getReflectionProviderClass());
		add(application.getVersionProviderClass());
	}

}
