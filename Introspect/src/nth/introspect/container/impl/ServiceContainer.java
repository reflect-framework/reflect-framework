package nth.introspect.container.impl;

import java.util.List;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.exception.MissingServiceClassException;
import nth.introspect.definition.ServiceLayer;

/**
 * See {@link ServiceLayer}
 * 
 * @author nilsth
 * 
 */
public class ServiceContainer extends IntrospectContainer {

	public ServiceContainer(IntrospectApplication application) {
		super(new DomainContainer(application));

		// add all service classes
		List<Class<?>> serviceClasses = application.getServiceClasses();
		if (serviceClasses.size() == 0) {
			throw new MissingServiceClassException(application);
		}
		add(serviceClasses);
	}

}
