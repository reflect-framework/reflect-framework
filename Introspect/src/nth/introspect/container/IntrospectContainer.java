package nth.introspect.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import nth.introspect.application.IntrospectApplication;
import nth.introspect.container.exception.DoubleServiceClassException;
import nth.introspect.container.exception.IntrospectContainerException;
import nth.introspect.container.exception.IntrospectContainerInitializationException;
import nth.introspect.container.exception.MissingServiceClassException;
import nth.introspect.container.inject.annotation.Inject;
import nth.introspect.provider.userinterface.UserInterfaceProvider;

public final class IntrospectContainer {

	private List<Object> backEndServiceObjects;
	private List<Object> frontEndServiceObjects;
	private List<Object> providerObjects;
	private final Map<Class<?>, Object> allInstances;
	private final IntrospectApplication application;

	/**
	 * This constructor will initialize the {@link IntrospectContainer} by
	 * creating the introspect objects and then inject these objects into each
	 * other where needed
	 * 
	 * @param application
	 *            that provides the information needed to initialize
	 */
	public IntrospectContainer(IntrospectApplication application) {
		this.application = application;
		backEndServiceObjects = new ArrayList<Object>();
		frontEndServiceObjects = new ArrayList<Object>();
		providerObjects = new ArrayList<Object>();
		allInstances = new HashMap<Class<?>, Object>();
		// FIXME call createInstances(application) from constructor and remove
		// "service lookups" of the providers in the Introspect class
	}

	public void createInstances() throws IntrospectContainerException {
		try {
			validateServiceClasses(application);

			// add IntrospectApplication to instances
			allInstances.put(IntrospectApplication.class, application);

			// add IntrospectContainer to instances
			allInstances.put(this.getClass(), this);

			providerObjects = createProviderObjects(application, allInstances);
			
			backEndServiceObjects = createBackEndServiceObjects(application,
					allInstances);

			frontEndServiceObjects = createFrontEndServiceObjects(application,
					allInstances);

		} catch (Exception exception) {
			throw new IntrospectContainerInitializationException(exception);
		}

	}

	private List<Object> createProviderObjects(
			IntrospectApplication application,
			Map<Class<?>, Object> instantiatedObjects)
			throws IntrospectContainerException {
		List<Class<?>> providerClasses = new ArrayList<Class<?>>();

		providerClasses.add(application.getPathProviderClass());
		providerClasses.add(application.getLanguageProviderClass());
		providerClasses.add(application.getValidationProviderClass());
		providerClasses.add(application.getAuthorizationProviderClass());
		providerClasses.add(application.getDomainProviderClass());
		providerClasses.add(application.getUserInterfaceProviderClass());
		providerClasses.add(application.getVersionProviderClass());
		return createInstances(providerClasses, instantiatedObjects);
	}

	private List<Object> createBackEndServiceObjects(
			IntrospectApplication application,
			Map<Class<?>, Object> instantiatedObjects)
			throws IntrospectContainerException {
		List<Class<?>> backEndServiceClasses = application
				.getBackEndServiceClasses();

		return createInstances(backEndServiceClasses, instantiatedObjects);
	}

	private List<Object> createFrontEndServiceObjects(
			IntrospectApplication application,
			Map<Class<?>, Object> instantiatedObjects)
			throws IntrospectContainerException {
		List<Class<?>> frontEndServiceClasses = application
				.getFrontEndServiceClasses();

		return createInstances(frontEndServiceClasses, instantiatedObjects);
	}

	private List<Object> createInstances(List<Class<?>> classesToInstantiate,
			Map<Class<?>, Object> instances)
			throws IntrospectContainerException {

		InstantiationStrategy instantiationStrategy = new InstantiationStrategy(
				classesToInstantiate, instances);
		return instantiationStrategy.createInstances();
	}

	private void validateServiceClasses(IntrospectApplication application)
			throws DoubleServiceClassException, MissingServiceClassException {
		List<Class<?>> frontEndServiceClasses = application
				.getFrontEndServiceClasses();
		if (frontEndServiceClasses == null
				|| frontEndServiceClasses.size() == 0) {
			throw new MissingServiceClassException(application.getClass());
		}

		List<Class<?>> backEndServiceClasses = application
				.getBackEndServiceClasses();

		if (backEndServiceClasses != null) {
			for (Class<?> backEndServiceClass : backEndServiceClasses) {
				if (frontEndServiceClasses.contains(backEndServiceClass)) {
					throw new DoubleServiceClassException(backEndServiceClass);
				}
			}
		}
	}

	public List<Object> getFrontEndServiceObjects() {
		return frontEndServiceObjects;
	}

	// FIXME: get rid of this public method after removing the "service lookup"
	// methods in the Introspect class
	public Object get(Class<?> classToFind) {
		if (allInstances.containsKey(classToFind)) {
			return allInstances.get(classToFind);
		}
		for (Class<?> instanceClass : allInstances.keySet()) {
			if (classToFind.isAssignableFrom(instanceClass)) {
				return allInstances.get(instanceClass);
			}
		}
		throw new RuntimeException("Could not lookup "
				+ classToFind.getCanonicalName());
	}

}
