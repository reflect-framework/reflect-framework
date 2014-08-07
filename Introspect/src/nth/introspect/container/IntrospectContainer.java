package nth.introspect.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.introspect.container.exception.DoubleServiceClassException;
import nth.introspect.container.exception.InjectionException;
import nth.introspect.container.exception.InstantiateException;
import nth.introspect.container.exception.MissingServiceClassException;
import nth.introspect.container.exception.ProviderNotDefined;
import nth.introspect.container.exception.IntrospectContainerInitializationException;
import nth.introspect.container.exception.UndeclaredInjectionTypeException;
import nth.introspect.container.inject.annotation.Inject;
import nth.introspect.container.lifecycle.listeners.IntrospectOnClosedListener;
import nth.introspect.container.lifecycle.listeners.IntrospectOnInitializedListener;
import nth.introspect.container.lifecycle.listeners.IntrospectOnInjectedListener;
import nth.introspect.initializer.IntrospectInitializer;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;
import nth.introspect.provider.version.VersionProvider;

public final class IntrospectContainer {

	private static final String INJECT = Inject.class.getSimpleName();
	private final Map<Class<?>, Object> objects;
	private final List<Object> frontEndServiceObjects;

	/**
	 * This constructor will initialize the {@link IntrospectContainer} by creating
	 * the introspect objects and then inject these objects into each other where
	 * needed
	 * 
	 * @param introspectInitializer
	 *            that provides the information needed to initialize
	 */
	public IntrospectContainer(IntrospectInitializer introspectInitializer) {
		try {
			validateServiceClasses(introspectInitializer);

			objects = new HashMap<Class<?>, Object>();

			// add IntrospectApplication to objects
			objects.put(introspectInitializer.getClass(), introspectInitializer);

			// add IntrospectContainer to objects
			objects.put(this.getClass(), this);

			frontEndServiceObjects = createFrontEndServiceObjects(introspectInitializer);
			for (Object frontEndServiceObject : frontEndServiceObjects) {
				objects.put(frontEndServiceObject.getClass(),
						frontEndServiceObject);
			}

			Map<Class<?>, Object> backEndServiceObjects = createBackEndServiceObjects(introspectInitializer);
			objects.putAll(backEndServiceObjects);

			inject(introspectInitializer, objects);

			invokeOnIntrospectContainerInitialized(objects);
		} catch (Exception exception) {
			throw new IntrospectContainerInitializationException(exception);
		}
	}

	private Map<Class<?>, Object> createBackEndServiceObjects(
			IntrospectInitializer introspectInitializer) {
		// TODO
		return new HashMap<Class<?>, Object>();
	}

	private List<Object> createFrontEndServiceObjects(
			IntrospectInitializer application) throws InstantiateException {
		List<Class<?>> frontEndServiceClasses = application
				.getFrontEndServiceClasses();
		List<Object> frontEndServiceObjects = new ArrayList<Object>();
		for (Class<?> frontEndServiceClass : frontEndServiceClasses) {
			try {
				Object frontEndServiceObject = frontEndServiceClass
						.newInstance();
				frontEndServiceObjects.add(frontEndServiceObject);
			} catch (Exception exception) {
				throw new InstantiateException(frontEndServiceClass, exception);
			}
		}
		return frontEndServiceObjects;
	}

	private void validateServiceClasses(IntrospectInitializer application)
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

	private void invokeOnIntrospectContainerInitialized(Map<Class<?>, Object> objects) {
		for (Object object : objects.values()) {
			if (object instanceof IntrospectOnInitializedListener) {
				IntrospectOnInitializedListener listener = (IntrospectOnInitializedListener) object;
				listener.onIntrospectInitialized();
			}
		}
	}

	private void invokeOnIntrospectContainerClose(Map<Class<?>, Object> objects) {
		for (Object object : objects.values()) {
			if (object instanceof IntrospectOnClosedListener) {
				IntrospectOnClosedListener listener = (IntrospectOnClosedListener) object;
				listener.onIntrospectClosed();
			}
		}
	}

	private void invokeOnIntrospectContainerMethods(Map<Class<?>, Object> objects) {
		for (Object object : objects.values()) {
			if (object instanceof IntrospectOnInjectedListener) {
				IntrospectOnInjectedListener listener = (IntrospectOnInjectedListener) object;
				listener.onIntrospectInjected();
			}
		}
	}

	public List<Object> getFrontEndServiceObjects() {
		return frontEndServiceObjects;
	}

	/**
	 * closes the {@link IntrospectContainer}
	 */
	public void close() {
		invokeOnIntrospectContainerClose(objects);
		objects.clear();
		System.exit(0);
	}

	private void inject(IntrospectInitializer application,
			Map<Class<?>, Object> introspectObjects)
			throws UndeclaredInjectionTypeException, InjectionException {
		for (Object introspectObject : introspectObjects.values()) {
			List<Field> injectionFields = findInjectionFields(introspectObject);
			for (Field injectionField : injectionFields) {
				Class<?> injectionType = injectionField.getType();
				Object objectToBeInjected = introspectObjects.get(injectionType);
				if (objectToBeInjected == null) {
					throw new UndeclaredInjectionTypeException(
							application.getClass(), introspectObject.getClass(),
							injectionType);
				}
				try {
					injectionField.setAccessible(true);
					injectionField.set(introspectObject, objectToBeInjected);
				} catch (Exception exception) {
					throw new InjectionException(injectionField, exception);
				}

			}
		}

		invokeOnIntrospectContainerMethods(introspectObjects);
	}

	private List<Field> findInjectionFields(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		List<Field> injectionFields = new ArrayList<Field>();
		for (Field field : fields) {
			if (isInjectionField(field)) {
				injectionFields.add(field);
			}
		}
		return injectionFields;

	}

	private boolean isInjectionField(Field field) {
		Annotation[] annotations = field.getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			String annotationName = annotation.annotationType().getSimpleName();
			if (annotationName.equals(INJECT)) {
				return true;
			}
		}
		return false;
	}

}
