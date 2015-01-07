package nth.introspect.container.impl;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.definition.InfrastructureLayer;

/**
 * This {@link IntrospectContainer} represents the {@link InfrastructureLayer}
 * 
 * @author nilsth
 * 
 */
public class InfrastructureContainer extends IntrospectContainer {

	public InfrastructureContainer(IntrospectApplication application) {
		super(new ProviderContainer(application));

		add(application.getInfrastructureClasses());
	}

}
