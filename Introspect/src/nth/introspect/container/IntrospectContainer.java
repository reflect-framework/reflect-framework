package nth.introspect.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import nth.introspect.container.exception.ClassAlreadyRegisteredInContainerException;
import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.documentation.ServiceObjectActionMethod;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.UserInterfaceLayer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceContainer;
import nth.introspect.layer2service.ServiceLayer;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainContainer;
import nth.introspect.layer3domain.DomainLayer;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureContainer;
import nth.introspect.layer4infrastructure.InfrastructureLayer;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.ProviderLayer;

/**
 * <p>
 * The {@link IntrospectFramework } is a dependency injection framework and
 * consists of several <a
 * href="http://en.wikipedia.org/wiki/Dependency_injection">dependency
 * injection</a> containers called {@link IntrospectContainer}s.
 * </p>
 * <p>
 * Please read <a href="http://en.wikipedia.org/wiki/Martin_Fowler">Martin
 * Fowler</a>'s easy to read <a
 * href="http://martinfowler.com/articles/injection.html">article</a> in which
 * he explains the basics dependency injection.
 * </p>
 * <p>
 * Each {@link IntrospectContainer} is responsible for:
 * <ul>
 * <li>Creating new instances of types that are registered to a
 * {@link IntrospectContainer}</li>
 * <li>Linking dependencies (references to other objects) to these new instances
 * (using constructor injection)</li>
 * <li>Caching these new instances, if we only need one instance (like a
 * singleton)</li>
 * </ul>
 * </p>
 * <p>
 * The {@link IntrospectArchitecture} consists of several layers. Each layer has
 * its own {@link IntrospectContainer} that is for managing the objects in the
 * same layer:
 * <ul>
 * <li>{@link UserInterfaceLayer}: {@link UserInterfaceController} object is
 * managed by a {@link UserInterfaceContainer}. The
 * {@link UserInterfaceContainer} knows the {@link ServiceContainer}</li>
 * <li>{@link ServiceLayer}: {@link ServiceObject}'s are managed by a
 * {@link ServiceContainer}. The {@link ServiceContainer} knows the
 * {@link DomainContainer}</li>
 * <li>{@link DomainLayer}: {@link DomainObject}'s are managed by a
 * {@link DomainContainer}. The {@link DomainContainer} knows the
 * {@link InfrastructureContainer}</li>
 * <li>{@link InfrastructureLayer}: {@link InfrastructureObject}'s are managed
 * by a {@link InfrastructureContainer}. The {@link DomainContainer} knows the
 * {@link ProviderContainer}</li>
 * <li>{@link ProviderLayer}: {@link Provider} object's are managed by a
 * {@link ProviderContainer}.</li>
 * </ul>
 * </p>
 * 
 * <h3>Constructor Injection</h3>
 * <p>
 * {@insert ConstructionInjection}
 * </p>
 * 
 * 
 * @author nilsth
 * @see IntrospectArchitecture
 */
public abstract class IntrospectContainer {

	private final IntrospectContainer innerContainer;
	private final HashMap<Class<?>, Object> typesAndInstances;

	public IntrospectContainer() {
		this(null);
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
		if (type == null) {
			throw new NullPointerException();
		}
		List<Class<?>> allClasses = getAllClasses();
		Class<?> foundClass = NearestParentFinder.findParent(allClasses, type);
		if (foundClass != null) {
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
		List<Class<?>> classesWaitingToBeInstantiated = new ArrayList<Class<?>>();
		return get(type, classesWaitingToBeInstantiated);
	}

	public Object get(Class<?> type,
			List<Class<?>> classesWaitingToBeInstantiated)
			throws IntrospectContainerException {

		if (IntrospectContainer.class.isAssignableFrom(type)) {
			// Reflect containers can be hierarchical.
			// That is why we here return the outer container, instead of
			// getting the value from innerContainers.
			return this;
		} else if (innerContainer != null) {
			// Try to get the object from one of the inner containers.
			Object object = innerContainer.get(type,
					classesWaitingToBeInstantiated);
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
			if (storedObject == null) {
				classesWaitingToBeInstantiated.add(foundType);
				InstanceFactory instanceFactory = new InstanceFactory(
						foundType, this);
				Object newObject = instanceFactory
						.createInstance(classesWaitingToBeInstantiated);
				typesAndInstances.put(type, newObject);
				classesWaitingToBeInstantiated.remove(foundType);
				// TODO IntrospectLog.debug(name + " created: " +
				// type.getCanonicalName());
				return newObject;
			} else {
				// TODO IntrospectLog.debug(name + " from cache: "+
				// type.getCanonicalName());
				return storedObject;
			}
		}

	}

	/**
	 * 
	 * @return all classes that are supported by this container and all its
	 *         inner containers
	 */
	public List<Class<?>> getAllClasses() {
		List<Class<?>> allClasses = new ArrayList<Class<?>>();
		// add this container
		allClasses.add(this.getClass());
		// add all supported classes known by this container
		allClasses.addAll(typesAndInstances.keySet());
		// add all supported classes of all inner containers
		if (innerContainer != null) {
			allClasses.addAll(innerContainer.getAllClasses());
		}
		return allClasses;
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

}
