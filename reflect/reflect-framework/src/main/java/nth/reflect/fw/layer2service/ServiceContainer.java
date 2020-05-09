package nth.reflect.fw.layer2service;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.container.exception.MissingServiceClassException;
import nth.reflect.fw.layer3domain.DomainContainer;

/**
 * This {@link DependencyInjectionContainer} represents the {@link ServiceLayer}
 * 
 * @author nilsth
 * 
 */
public class ServiceContainer extends DependencyInjectionContainer {

	public ServiceContainer(ReflectApplication application, boolean mustHaveServiceClasses) {
		super(new DomainContainer(application));

		// add all service classes
		List<Class<?>> serviceClasses = application.getServiceClasses();
		if (mustHaveServiceClasses && (serviceClasses==null || serviceClasses.size() == 0) ) {
			throw new MissingServiceClassException(application);
		}
		add(serviceClasses);
	}
	
	public List<Object> getServiceObjects() {
		ReflectApplication application = get(ReflectApplication.class);
		List<Class<?>> serviceClasses = application.getServiceClasses();
		List<Object> serviceObjects = new ArrayList<Object>();
		for (Class<?> serviceClass : serviceClasses) {
			Object serviceObject = get(serviceClass);
			serviceObjects.add(serviceObject);
		}
		return serviceObjects;
	}

}
