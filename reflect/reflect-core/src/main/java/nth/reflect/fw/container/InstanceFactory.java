package nth.reflect.fw.container;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.fw.container.exception.ClassHasNoUsableConstructorException;
import nth.reflect.fw.container.exception.DependencyLoopException;
import nth.reflect.fw.container.exception.ReflectContainerException;

public class InstanceFactory {

	private final Class<?> classToInstantiate;
	private final Constructor<?> bestConstructor;
	private List<Class<?>> dependencyClasses;
	private DependencyInjectionContainer container;

	public InstanceFactory(Class<?> classToInstantiate, DependencyInjectionContainer container)
			throws ReflectContainerException {
		this.classToInstantiate = classToInstantiate;
		this.container = container;
		this.bestConstructor = findBestConstructor(classToInstantiate, container);
		this.dependencyClasses = new ArrayList<Class<?>>();
		this.dependencyClasses.addAll(Arrays.asList(bestConstructor.getParameterTypes()));
	}

	private Constructor<?> findBestConstructor(Class<?> classToInstantiate, DependencyInjectionContainer container)
			throws ClassHasNoUsableConstructorException {
		List<Class<?>> allowedDependencyClasses = container.getAllClasses();
		Constructor<?> bestConstructor = null;
		for (Constructor<?> constructor : classToInstantiate.getConstructors()) {
			if (isBestConstructor(bestConstructor, constructor, allowedDependencyClasses)) {
				bestConstructor = constructor;
			}
		}

		if (bestConstructor == null) {
			throw new ClassHasNoUsableConstructorException(classToInstantiate);
		}
		return bestConstructor;
	}

	private boolean isBestConstructor(Constructor<?> bestConstructor, Constructor<?> constructor,
			List<Class<?>> allowedDependecyClasses) {
		return isValidConstructor(constructor, allowedDependecyClasses)
				&& (bestConstructor == null || hasMoreParameters(constructor, bestConstructor));
	}

	private boolean hasMoreParameters(Constructor<?> constructor1, Constructor<?> constructor2) {
		return constructor1.getParameterTypes().length > constructor2.getParameterTypes().length;
	}

	private boolean isValidConstructor(Constructor<?> constructor, List<Class<?>> allowedDependecyClasses) {
		Class<?>[] parameterTypes = constructor.getParameterTypes();

		// check is all parameter types are ok
		for (Class<?> parameterType : parameterTypes) {
			if (parameterType == getClassToInstantiate()// no loops to it self
					|| !NearestParentFinder.containsParent(allowedDependecyClasses, parameterType)) {
				return false;
			}
		}
		return true;
	}

	public List<Class<?>> getDependencyClasses() {
		return dependencyClasses;
	}

	public boolean isCrossDependency(InstanceFactory classInstantiateInfo) {
		Class<?> classToInstantiate = classInstantiateInfo.getClassToInstantiate();
		for (Class<?> dependencyClass : getDependencyClasses()) {
			if (dependencyClass == classToInstantiate) {
				return true;
			}
		}
		return false;
	}

	public Class<?> getClassToInstantiate() {
		return classToInstantiate;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(classToInstantiate.getSimpleName());
		if (dependencyClasses.size() > 0) {
			string.append(" (");
			boolean isFirst = true;
			for (Class<?> dependencyClass : dependencyClasses) {
				if (isFirst) {
					isFirst = false;
				} else {
					string.append(", ");
				}
				string.append(dependencyClass.getSimpleName());
			}
			string.append(")");
		}
		return string.toString();

	}

	public Constructor<?> getBestConstructor() {
		return bestConstructor;
	}

	public boolean needsToGoBefore(InstanceFactory instantiationInfo) {
		List<Class<?>> dependencyClasses = instantiationInfo.getDependencyClasses();
		boolean needsToGoBefore = NearestParentFinder.containsParent(dependencyClasses, classToInstantiate);
		return needsToGoBefore;
	}

	public Object createInstance(List<Class<?>> classesWaitingToBeInstantiated) throws ReflectContainerException {

		Class<?>[] constructorParameterTypes = bestConstructor.getParameterTypes();

		checkForLoopedDependency(constructorParameterTypes, classesWaitingToBeInstantiated);

		Object[] constructorParameterValues = getConstructorParameterValues(container, constructorParameterTypes,
				classesWaitingToBeInstantiated);

		Object object;
		try {
			object = bestConstructor.newInstance(constructorParameterValues);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new nth.reflect.fw.container.exception.InstantiationException(container, bestConstructor, e);
		}
		return object;
	}

	private void checkForLoopedDependency(Class<?>[] constructorParameterTypes,
			List<Class<?>> classesWaitingToBeInstantiated) {
		for (Class<?> classWaitingToBeInstantiated : classesWaitingToBeInstantiated) {
			for (Class<?> constructorParameterType : constructorParameterTypes) {
				if (constructorParameterType.isAssignableFrom(classWaitingToBeInstantiated)) {
					throw new DependencyLoopException(classToInstantiate, constructorParameterType);
				}
			}
		}

	}

	private Object[] getConstructorParameterValues(DependencyInjectionContainer container,
			Class<?>[] constructorParameterTypes, List<Class<?>> classesWaitingToBeInstantiated)
			throws ReflectContainerException {
		Object[] constructorParameterValues = new Object[constructorParameterTypes.length];
		for (int index = 0; index < constructorParameterTypes.length; index++) {
			Class<?> constructorParameterType = constructorParameterTypes[index];
			Object constructorParameterValue = container.get(constructorParameterType, classesWaitingToBeInstantiated);
			constructorParameterValues[index] = constructorParameterValue;
		}
		return constructorParameterValues;
	}
}
