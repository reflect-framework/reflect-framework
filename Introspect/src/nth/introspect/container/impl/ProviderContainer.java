package nth.introspect.container.impl;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.definition.ProviderLayer;

/**
 * See {@link ProviderLayer}
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
		add(application.getDomainInfoProviderClass());
		add(application.getVersionProviderClass());
	}

}
