package nth.introspect.container.impl;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.definition.DomainLayer;

/**
 * See {@link DomainLayer}
 * @author nilsth
 *
 */
public class DomainContainer extends IntrospectContainer {
	
	public DomainContainer(IntrospectApplication application) {
		super(DomainContainer.class.getSimpleName(), new InfrastructureContainer(application));
	}

}
