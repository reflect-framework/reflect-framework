package nth.introspect.layer2service;

import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.container.exception.MissingServiceClassException;
import nth.introspect.layer3domain.DomainContainer;

/**
 * This {@link DependencyInjectionContainer} represents the {@link ServiceLayer}
 * 
 * @author nilsth
 * 
 */
public class ServiceContainer extends DependencyInjectionContainer {

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
