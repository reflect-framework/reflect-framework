package nth.introspect.container;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.application.IntrospectApplication;

public class IntrospectOuterContainer extends IntrospectContainer {

	private final IntrospectApplication application;

	public IntrospectOuterContainer(String layerName,
			IntrospectApplication application, IntrospectContainer innerContainer) {
		super(layerName, innerContainer);
		this.application = application;
	}

	public List<Object> getServiceObjects() {
		List<Class<?>> serviceClasses = application.getServiceClasses();
		List<Object> serviceObjects=new ArrayList<Object>();
		for (Class<?> serviceClass : serviceClasses) {
			Object serviceObject = get(serviceClass);
			serviceObjects.add(serviceObject);
		}
		return serviceObjects;
	}
	

	public List<Class<?>> getAllClasses() {
		List<Class<?>> allClasses = super.getAllClasses();
		allClasses.remove(IntrospectContainer.class);
		allClasses.add(IntrospectOuterContainer.class);
		return allClasses;
	}

}
