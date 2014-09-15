package nth.introspect.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.exception.ClassAlreadyRegisteredInContainerException;
import nth.introspect.container.exception.IntrospectContainerException;

public class IntrospectContainer {

	private final String name;
	private IntrospectContainer innerContainer;
	private HashMap<Class<?>, Object> typesAndInstances;

	public IntrospectContainer(String layerName,
			IntrospectApplication application) {
		this(layerName, application, null);
	}

	public IntrospectContainer(String name, IntrospectApplication application,
			IntrospectContainer lowerLayer) {
		this.name = name;
		this.innerContainer = lowerLayer;
		this.typesAndInstances = new HashMap<Class<?>, Object>();
	}

	public void add(Object object) {
		Class<?> type = object.getClass();
		typesAndInstances.put(type, object);
	}

	public void add(Class<?> type) throws IntrospectContainerException {
		// TODO verify if type isn't already defined in lower layer
		List<Class<?>> allClasses = getAllClasses();
		Class<?> foundClass = NearestParentFinder.findParent(allClasses, type);
		if (foundClass!=null) {
			throw new ClassAlreadyRegisteredInContainerException(this, type);
		}
		typesAndInstances.put(type, null);
	}

	public void add(Collection<Class<?>> types)
			throws IntrospectContainerException {
		for (Class<?> type : types) {
			add(type);
		}
	}

	

	public Object get(Class<?> type) throws IntrospectContainerException {
		List<Class<?>> classesWaitingToBeInstantiated=new ArrayList<Class<?>>(); 
		return get(type, classesWaitingToBeInstantiated);
	}	
	
	public Object get(Class<?> type,List<Class<?>> classesWaitingToBeInstantiated) throws IntrospectContainerException {

		if (type.isAssignableFrom(IntrospectContainer.class)) {
			// Reflect containers can be hierarchical.
			// That is why we here return the outer container, instead of
			// getting the value from innerContainers.
			return this;
		} else if (innerContainer != null) {
			// Try to get the object from one of the inner containers.
			Object object = innerContainer.get(type, classesWaitingToBeInstantiated);
			if (object != null) {
				return object;
			}
		}

		// is the requested type supported by this container?
		Class<?> foundType = NearestParentFinder.findParent(
				typesAndInstances.keySet(), type);
		if (foundType == null) {
			return null;
		} else {
			Object storedObject = typesAndInstances.get(foundType);
			if (storedObject ==null) {
				classesWaitingToBeInstantiated.add(foundType);
				InstanceFactory instanceFactory = new InstanceFactory(foundType, this);
				Object newObject = instanceFactory.createInstance(classesWaitingToBeInstantiated);
				typesAndInstances.put(type, newObject);
				classesWaitingToBeInstantiated.remove(foundType);
				System.out.println(name + " created: " + type.getCanonicalName());
				return newObject;
			} else {
				System.out.println(name + " from cache: "
						+ type.getCanonicalName());
				return storedObject;
			}
		}

	}

	public List<Class<?>> getAllClasses() {
		List<Class<?>> allClasses = new ArrayList<Class<?>>();
		if (innerContainer != null) {
			allClasses.addAll(innerContainer.getAllClasses());
		}
		allClasses.addAll(typesAndInstances.keySet());
		return allClasses;
	}

	public String getName() {
		return name;
	}

}
