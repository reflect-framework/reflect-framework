package nth.introspect.container;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nth.introspect.container.exception.DependencyLoopException;
import nth.introspect.container.exception.InstantiateException;
import nth.introspect.container.exception.IntrospectContainerException;

public class InstantiationStrategy {

	private final List<InstantiationInfo> instantiationInfos;
	private final Map<Class<?>, Object> instances;

	public InstantiationStrategy(List<Class<?>> classesToInstantiate,
			Map<Class<?>, Object> instances)
			throws IntrospectContainerException {

		this.instances = instances;
		List<Class<?>> allowedDependecyClasses = new ArrayList<Class<?>>();
		allowedDependecyClasses.addAll(classesToInstantiate);
		allowedDependecyClasses.addAll(instances.keySet());

		instantiationInfos = new ArrayList<InstantiationInfo>();
		for (Class<?> classToInstantiate : classesToInstantiate) {

			InstantiationInfo instantiationInfo = new InstantiationInfo(
					classToInstantiate, allowedDependecyClasses);

			int position = positionToInsert(instantiationInfos, instantiationInfo);
			instantiationInfos.add(position, instantiationInfo);
		}

		allowedDependecyClasses.clear();
		allowedDependecyClasses.addAll(instances.keySet());
		validate(instantiationInfos, allowedDependecyClasses);

	}

	private int positionToInsert(
			List<InstantiationInfo> instantiationInfos,
			InstantiationInfo instantiationInfoToInsert) {
		List<Class<?>> dependenciesToBeFound = new ArrayList<Class<?>>( instantiationInfoToInsert.getDependencyClasses());
		for (InstantiationInfo instantiationInfo : instantiationInfos) {
			if (dependenciesToBeFound.size()==0) {
				//all dependencies are already created by preceding instantiation info's, so lets insert it at the current position
				return instantiationInfos.indexOf(instantiationInfo);
			}

			Class<?> type = instantiationInfo.getClassToInstantiate();
			if (dependenciesToBeFound.contains(type)) {
				dependenciesToBeFound.remove(type);
			}
		}
		//if not all of the dependencies where created yet, lets put it at as the last instantiation info, hoping all the the remaining dependencies will be inserted later
		return instantiationInfos.size();
	}

	private void validate(List<InstantiationInfo> instantiationInfos,
			Collection<Class<?>> allowedDependecyClasses)
			throws DependencyLoopException {
		for (InstantiationInfo instantiationInfo : instantiationInfos) {
			List<Class<?>> dependencyClasses = instantiationInfo
					.getDependencyClasses();
			for (Class<?> dependencyClass : dependencyClasses) {
				if (!allowedDependecyClasses.contains(dependencyClass)) {
					throw new DependencyLoopException(
							instantiationInfo.getClassToInstantiate(),
							dependencyClass);
				}
			}
			allowedDependecyClasses.add(instantiationInfo
					.getClassToInstantiate());
		}
	}


	public List<Object> createInstances() throws InstantiateException {
		List<Object> createdInstances = new ArrayList<Object>();
		for (InstantiationInfo instantiationInfo : instantiationInfos) {
			List<Class<?>> parameterClasses = instantiationInfo
					.getDependencyClasses();
			Object[] parameterValues = getConstructorParameterValues(parameterClasses);
			Constructor<?> constructor = instantiationInfo.getConstructor();
			try {
				Object createdInstance = constructor
						.newInstance(parameterValues);
				createdInstances.add(createdInstance);
				instances.put(instantiationInfo.getClassToInstantiate(),
						createdInstance);
			} catch (Exception exception) {
				throw new InstantiateException(
						instantiationInfo.getClassToInstantiate(), exception);
			}
		}
		return createdInstances;
	}

	private Object[] getConstructorParameterValues(
			List<Class<?>> parameterClasses) {
		Object[] parameterValues = new Object[parameterClasses.size()];
		int index = 0;
		for (Class<?> parameterClass : parameterClasses) {
			Object parameterValue = instances.get(parameterClass);
			parameterValues[index++] = parameterValue;
		}
		return parameterValues;
	}

	@Override
	public String toString() {
		return instantiationInfos.toString();
	}

}
