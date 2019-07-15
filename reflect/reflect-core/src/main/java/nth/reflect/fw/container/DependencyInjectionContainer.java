package nth.reflect.fw.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.exception.ClassAlreadyRegisteredInContainerException;
import nth.reflect.fw.container.exception.ReflectContainerException;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.UserInterfaceLayer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.fw.layer2service.ServiceLayer;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainContainer;
import nth.reflect.fw.layer3domain.DomainLayer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer4infrastructure.InfrastructureContainer;
import nth.reflect.fw.layer4infrastructure.InfrastructureLayer;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.ProviderLayer;

/**
 * <p>
 * The {@link ReflectFramework} is a dependency injection framework and consists
 * of several
 * <a href="http://en.wikipedia.org/wiki/Dependency_injection">dependency
 * injection containers</a>.
 * </p>
 * <p>
 * If you are new to Dependency Injection I recommend reading
 * <a href="http://en.wikipedia.org/wiki/Martin_Fowler">Martin Fowler</a>'s
 * <a href="http://martinfowler.com/articles/injection.html">article</a> in
 * which he explains the basics dependency injection.
 * </p>
 * <p>
 * Each {@link DependencyInjectionContainer} is responsible for:
 * <ul>
 * <li>Creating new instances of types that are registered to a
 * {@link DependencyInjectionContainer} with the {@link ReflectApplication}
 * .get...Class() or .get...Classes() methods</li>
 * <li>Linking dependencies (references to other objects) to these new instances
 * (using constructor injection)</li>
 * <li>Caching these new instances, if we only need one instance (like a
 * singleton)</li>
 * </ul>
 * </p>
 * <p>
 * The {@link ReflectArchitecture} consists of several layers. Each layer has
 * its own {@link DependencyInjectionContainer} that manages the objects in that
 * layer:
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
 * <h3>Constructor Injection</h3> {@insert ConstructionInjection}
 * 
 * @author nilsth
 */
public abstract class DependencyInjectionContainer {

	private final DependencyInjectionContainer innerContainer;
	private final HashMap<Class<?>, Object> typesAndInstances;

	public DependencyInjectionContainer() {
		this(null);
	}

	public DependencyInjectionContainer(DependencyInjectionContainer innerContainer) {
		this.innerContainer = innerContainer;
		this.typesAndInstances = new HashMap<Class<?>, Object>();
	}

	public void add(Object object) {
		Class<?> type = object.getClass();
		typesAndInstances.put(type, object);
	}

	public void add(Class<?> type) throws ReflectContainerException {
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

	public void add(Collection<Class<?>> types) throws ReflectContainerException {
		for (Class<?> type : types) {
			add(type);
		}
	}

	public <T extends Object> T get(Class<T> type) throws ReflectContainerException {
		List<Class<?>> classesWaitingToBeInstantiated = new ArrayList<Class<?>>();
		return get(type, classesWaitingToBeInstantiated);
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T get(Class<T> type, List<Class<?>> classesWaitingToBeInstantiated)
			throws ReflectContainerException {

		if (DependencyInjectionContainer.class.isAssignableFrom(type)) {
			Class<? extends DependencyInjectionContainer> dependencyInjectionType = (Class<? extends DependencyInjectionContainer>) type;
			return (T) getDependencyContainer(dependencyInjectionType);
		} else if (innerContainer != null) {
			// Try to get the object from one of the inner containers.
			Object object = innerContainer.get(type, classesWaitingToBeInstantiated);
			if (object != null) {
				return (T) object;
			}
		}

		return getObjectFromThisContainer(type, classesWaitingToBeInstantiated);
	}

	@SuppressWarnings("unchecked")
	private <T> T getObjectFromThisContainer(Class<T> typeToFind, List<Class<?>> classesWaitingToBeInstantiated) {

		Class<?> typeToGet = NearestParentFinder.findParent(typesAndInstances.keySet(), typeToFind);

		if (typeToGet == null) {
			return null; // TODO throw exception?
		} else {
			Object storedObject = typesAndInstances.get(typeToGet);
			if (storedObject == null) {
				classesWaitingToBeInstantiated.add(typeToGet);
				InstanceFactory instanceFactory = new InstanceFactory(typeToGet, this);
				Object newObject = instanceFactory.createInstance(classesWaitingToBeInstantiated);
				typesAndInstances.put(typeToGet, newObject);
				classesWaitingToBeInstantiated.remove(typeToGet);
				return (T) newObject;
			} else {
				return (T) storedObject;
			}
		}
	}

	private DependencyInjectionContainer getDependencyContainer(Class<? extends DependencyInjectionContainer> type) {
		if (this.getClass() == type) {
			return this;
		} else if (innerContainer == null) {
			return null;// TODO throw exception???
		} else if (innerContainer.getClass() == type) {
			return innerContainer;
		} else {
			// dig deeper
			return innerContainer.getDependencyContainer(type);
		}

	}

	/**
	 * 
	 * @return all classes that are supported by this container and all its inner
	 *         containers
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
