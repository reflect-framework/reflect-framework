package nth.introspect.layer3domain;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer4infrastructure.InfrastructureContainer;

/**
 * This {@link DependencyInjectionContainer} represents the {@link DomainLayer}
 * @author nilsth
 *
 */
public class DomainContainer extends DependencyInjectionContainer {
	
	public DomainContainer(IntrospectApplication application) {
		super(new InfrastructureContainer(application));
	}

}
