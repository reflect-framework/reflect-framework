package nth.introspect.container.impl;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.definition.InfrastructureLayer;

/**
 * See {@link InfrastructureLayer}
 * @author nilsth
 *
 */
public class InfrastructureContainer extends IntrospectContainer {

	public InfrastructureContainer(IntrospectApplication application) {
		super(InfrastructureContainer.class.getSimpleName(),
				new ProviderContainer(application));

		add(application.getInfrastructureClasses());
	}

}
