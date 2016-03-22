package nth.introspect.layer2service;

import java.util.ArrayList;
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

	public ServiceContainer(IntrospectApplication application, boolean mustHaveServiceClasses) {
		super(new DomainContainer(application));

		// add all service classes
		List<Class<?>> serviceClasses = application.getServiceClasses();
		if (mustHaveServiceClasses && (serviceClasses==null || serviceClasses.size() == 0) ) {
			throw new MissingServiceClassException(application);
		}
		add(serviceClasses);
	}
	
	public List<Object> getServiceObjects() {
		IntrospectApplication application = get(IntrospectApplication.class);
		List<Class<?>> serviceClasses = application.getServiceClasses();
		List<Object> serviceObjects = new ArrayList<Object>();
		for (Class<?> serviceClass : serviceClasses) {
			Object serviceObject = get(serviceClass);
			serviceObjects.add(serviceObject);
		}
		return serviceObjects;
	}

}
