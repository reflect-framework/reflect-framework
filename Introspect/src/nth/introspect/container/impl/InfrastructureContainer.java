package nth.introspect.container.impl;

import java.util.List;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.documentation.InfrastructureLayer;

/**
 * This {@link IntrospectContainer} represents the {@link InfrastructureLayer}
 * 
 * @author nilsth
 * 
 */
public class InfrastructureContainer extends IntrospectContainer {

	public InfrastructureContainer(IntrospectApplication application) {
		super(new ProviderContainer(application));
		List<Class<?>> infrastructureClasses = application
				.getInfrastructureClasses();
		if (infrastructureClasses != null) {
			add(infrastructureClasses);
		}
	}

}
