package nth.introspect.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import nth.introspect.container.exception.ClassAlreadyRegisteredInContainerException;
import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.container.impl.DomainContainer;
import nth.introspect.container.impl.InfrastructureContainer;
import nth.introspect.container.impl.ProviderContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.definition.DomainLayer;
import nth.introspect.definition.DomainObject;
import nth.introspect.definition.InfrastructureLayer;
import nth.introspect.definition.InfrastructureObject;
import nth.introspect.definition.IntrospectArchitecture;
import nth.introspect.definition.ProviderLayer;
import nth.introspect.definition.ServiceLayer;
import nth.introspect.definition.ServiceObject;
import nth.introspect.definition.UserInterfaceLayer;
import nth.introspect.provider.Provider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;

/**
 * The {@link IntrospectContainer} is a dependency injection container. It is responsible for:
 * <ul>
 * <li>Creating new instances of types that are registered to a {@link IntrospectContainer}</li>
 * <li>Linking dependencies (references to other objects) to these new instances (using constructor injection) </li>
 * <li>Caching these new instances, if we only need one instance  (like a singleton)</li>
 * </ul>
 * 
 * It is possible to create nested {@link IntrospectContainer}'s. Each container will manage a different group of objects (a container for each layer). This is used to implement the {@link IntrospectArchitecture}: 
 * <ul>
 * <li>{@link ProviderLayer}: {@link Provider} object's are managed by a {@link ProviderContainer}.</li>
 * <li>{@link InfrastructureLayer}: {@link InfrastructureObject}'s are managed by a {@link InfrastructureContainer}. The {@link DomainContainer} knows the {@link ProviderContainer}</li>
 * <li>{@link DomainLayer}: {@link DomainObject}'s are managed by a {@link DomainContainer}. The {@link DomainContainer} knows the {@link InfrastructureContainer}</li>  
 * <li>{@link ServiceLayer}: {@link ServiceObject}'s are managed by a {@link ServiceContainer}. The {@link ServiceContainer} knows the {@link DomainContainer}</li>  
 * <li>{@link UserInterfaceLayer}: {@link UserInterfaceProvider} object is managed by a {@link UserInterfaceContainer}. The {@link UserInterfaceContainer} knows the {@link ServiceContainer}</li>  
 * </ul>
 * 
 * @author nilsth
 *
 */
public abstract class IntrospectContainer {

	private final IntrospectContainer innerContainer;
	private final HashMap<Class<?>, Object> typesAndInstances;

	public IntrospectContainer() {
		this( null);
	}

	public IntrospectContainer(IntrospectContainer innerContainer) {
		this.innerContainer = innerContainer;
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

		if (IntrospectContainer.class.isAssignableFrom(type)) {
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
				//TODO IntrospectLog.debug(name + " created: " + type.getCanonicalName());
				return newObject;
			} else {
				//TODO IntrospectLog.debug(name + " from cache: "+ type.getCanonicalName());
				return storedObject;
			}
		}

	}

	/**
	 * 
	 * @return all classes that are supported by this container and all its inner containers
	 */
	public List<Class<?>> getAllClasses() {
		List<Class<?>> allClasses = new ArrayList<Class<?>>();
		//add this container
		allClasses.add(this.getClass());
		//add all supported classes known by this container
		allClasses.addAll(typesAndInstances.keySet());
		//add all supported classes of all inner containers
		if (innerContainer != null) {
			allClasses.addAll(innerContainer.getAllClasses());
		}
		return allClasses;
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

}
