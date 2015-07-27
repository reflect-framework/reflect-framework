package nth.introspect.layer3domain;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.layer4infrastructure.InfrastructureContainer;

/**
 * This {@link IntrospectContainer} represents the {@link DomainLayer}
 * @author nilsth
 *
 */
public class DomainContainer extends IntrospectContainer {
	
	public DomainContainer(IntrospectApplication application) {
		super(new InfrastructureContainer(application));
	}

}
