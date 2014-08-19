package nth.introspect.container;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.introspect.container.exception.ClassHasNoUsableConstructorException;
import nth.introspect.container.exception.DependencyLoopException;
import nth.introspect.container.exception.InstantiateException;
import nth.introspect.container.exception.IntrospectContainerException;

public class InstantiationStrategy {

	protected static final int BEFORE = -1;
	protected static final int SAME = 0;
	protected static final int AFTER = 1;
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

			instantiationInfos.add(instantiationInfo);
		}

		Comparator<InstantiationInfo> instantiationComparator = createInstantiationComperator();
		Collections.sort(instantiationInfos, instantiationComparator);

		allowedDependecyClasses.clear();
		allowedDependecyClasses.addAll(instances.keySet());
		validate(instantiationInfos, allowedDependecyClasses);
		
	}

	private void validate(List<InstantiationInfo> instantiationInfos, Collection<Class<?>> allowedDependecyClasses) throws DependencyLoopException {
		for (InstantiationInfo instantiationInfo : instantiationInfos) {
			List<Class<?>> dependencyClasses = instantiationInfo.getDependencyClasses();
			for (Class<?> dependencyClass : dependencyClasses) {
				if (!allowedDependecyClasses.contains(dependencyClass)) {
					throw new DependencyLoopException(instantiationInfo.getClassToInstantiate(), dependencyClass);
				}
			}
			allowedDependecyClasses.add(instantiationInfo.getClassToInstantiate());
		}
	}

	private Comparator<InstantiationInfo> createInstantiationComperator() {
		return new Comparator<InstantiationInfo>() {

			@Override
			public int compare(InstantiationInfo i1, InstantiationInfo i2) {

				List<Class<?>> dependencyClasses = i2.getDependencyClasses();
				if (dependencyClasses.contains(i1.getClassToInstantiate())) {
					return BEFORE;
				}
				return AFTER;

				// TODO return SAME;
			}
		};
	}

	public List<Object> createInstances() throws InstantiateException {
		List<Object>  createdInstances=new ArrayList<Object>();  
		for (InstantiationInfo instantiationInfo : instantiationInfos) {
			List<Class<?>> parameterClasses = instantiationInfo.getDependencyClasses();
			Object[] parameterValues = getConstructorParameterValues(parameterClasses);
			Constructor<?> constructor = instantiationInfo.getConstructor();
			try {
				Object createdInstance=constructor.newInstance(parameterValues);
				createdInstances.add(createdInstance);
				instances.put(instantiationInfo.getClassToInstantiate(), createdInstance);
			} catch (Exception exception) {
				throw new InstantiateException(instantiationInfo.getClassToInstantiate(),exception);
			}
		}
		return createdInstances;
	}

	private Object[] getConstructorParameterValues(
			List<Class<?>> parameterClasses) {
		Object[] parameterValues = new Object[ parameterClasses.size()];
		int index=0;
		for (Class<?> parameterClass : parameterClasses) {
			Object parameterValue = instances.get(parameterClass);
			parameterValues[index++]=parameterValue;
		}
		return parameterValues;
	}

	@Override
	public String toString() {
		return instantiationInfos.toString();
	}

	
	
}
