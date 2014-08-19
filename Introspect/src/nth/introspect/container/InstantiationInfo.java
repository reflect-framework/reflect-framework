package nth.introspect.container;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import nth.introspect.container.exception.ClassHasNoUsableConstructorException;

public class InstantiationInfo {

	private final Class<?> classToInstantiate;
	private final Constructor<?> bestConstructor;
	private final List<Class<?>> dependencyClasses;

	public InstantiationInfo(Class<?> classToInstantiate,
			List<Class<?>> allowedDependecyClasses)
			throws ClassHasNoUsableConstructorException {
		this.classToInstantiate = classToInstantiate;
		this.bestConstructor = findBestConstructor(classToInstantiate,
				allowedDependecyClasses);
		this.dependencyClasses = Arrays.asList(bestConstructor
				.getParameterTypes());
	}

	private Constructor<?> findBestConstructor(Class<?> classToInstantiate,
			List<Class<?>> allowedDependecyClasses)
			throws ClassHasNoUsableConstructorException {
		Constructor<?> bestConstructor = null;
		for (Constructor<?> constructor : classToInstantiate.getConstructors()) {
			if (isBestConstructor(bestConstructor, constructor,
					allowedDependecyClasses)) {
				bestConstructor = constructor;
			}
		}

		if (bestConstructor == null) {
			throw new ClassHasNoUsableConstructorException(classToInstantiate);
		}
		return bestConstructor;
	}

	private boolean isBestConstructor(Constructor<?> bestConstructor,
			Constructor<?> constructor, List<Class<?>> allowedDependecyClasses) {
		return isValidConstructor(constructor, allowedDependecyClasses)
				&& (bestConstructor == null || hasMoreParameters(constructor,
						bestConstructor));
	}

	private boolean hasMoreParameters(Constructor<?> constructor1,
			Constructor<?> constructor2) {
		return constructor1.getParameterTypes().length > constructor2
				.getParameterTypes().length;
	}

	private boolean isValidConstructor(Constructor<?> constructor,
			List<Class<?>> allowedDependecyClasses) {
		Class<?>[] parameterTypes = constructor.getParameterTypes();

		// check is all parameter types are ok
		for (Class<?> parameterType : parameterTypes) {
			if (parameterType == getClassToInstantiate()
					|| !allowedDependecyClasses.contains(parameterType)) {
				return false;
			}
		}
		return true;
	}

	public List<Class<?>> getDependencyClasses() {
		return dependencyClasses;
	}

	public boolean isCrossDependency(InstantiationInfo classInstantiateInfo) {
		Class<?> classToInstantiate = classInstantiateInfo
				.getClassToInstantiate();
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
					isFirst=false;
				} else {
					string.append(", ");	
				}
				string.append(dependencyClass.getSimpleName());
			}
			string.append(")");
		}
		return string.toString();
		
	}

	public Constructor<?> getConstructor() {
		return bestConstructor;
	}

}
