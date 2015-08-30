package nth.introspect.layer5provider.reflection;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.filter.FilterUtil;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfoFactory;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfoFactory;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfoComparator;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfoFactory;
import nth.introspect.layer5provider.reflection.info.property.TableVisibleFilter;

public class DefaultReflectionProvider implements ReflectionProvider {
	private final HashMap<Class<?>, ClassInfo> classInfos;
	private final HashMap<Class<?>, List<PropertyInfo>> propertyInfosPerClass;
	private final HashMap<Class<?>, List<ActionMethodInfo>> methodInfosPerClass;
	private final ProviderContainer providerContainer;

	public DefaultReflectionProvider(ProviderContainer providerContainer) {
		this.providerContainer = providerContainer;
		
		classInfos = new HashMap<Class<?>, ClassInfo>();
		propertyInfosPerClass = new HashMap<Class<?>, List<PropertyInfo>>();
		methodInfosPerClass = new HashMap<Class<?>, List<ActionMethodInfo>>();
	}


	public ClassInfo getClassInfo(Class<?> objectClass) {
		if (!classInfos.containsKey(objectClass)) {
			classInfos.put(objectClass,
					ClassInfoFactory.create(providerContainer, objectClass));
		}
		return classInfos.get(objectClass);
	}

	/**
	 * @return A collection of ActionInfos that hold information on what actions
	 *         menus in table view and form vieuws should be generated for a
	 *         given service or domain class.
	 */
	public List<ActionMethodInfo> getMethodInfos(Class<?> objectClass) {
		if (!methodInfosPerClass.containsKey(objectClass)) {
			methodInfosPerClass.put(objectClass,
					ActionMethodInfoFactory.create(providerContainer, objectClass));
		}
		return methodInfosPerClass.get(objectClass);
	}

	@Override
	public List<ActionMethodInfo> getMethodInfos(Class<?> objectClass,
			Filter<ActionMethodInfo> methodInfoFilter) {
		List<ActionMethodInfo> actionMethodInfos = getMethodInfos(objectClass);
		List<ActionMethodInfo> foundMethodInfos = FilterUtil.filter(actionMethodInfos,
				methodInfoFilter);
		return foundMethodInfos;
	}

	@Override
	public List<PropertyInfo> getOrderedAndVisiblePropertyInfos(
			Class<?> objectClass) {
		List<PropertyInfo> propertyInfos = getPropertyInfos(objectClass);
		// only return visible properties
		propertyInfos=FilterUtil.filter(propertyInfos, new TableVisibleFilter());
		// order properties
		Collections.sort(propertyInfos, new PropertyInfoComparator());
		return propertyInfos;
	}

	@Override
	public List<PropertyInfo> getOrderedPropertyInfos(Class<?> objectClass) {
		List<PropertyInfo> propertyInfos = getPropertyInfos(objectClass);
		// order properties
		Collections.sort(propertyInfos, new PropertyInfoComparator());
		return propertyInfos;
	}

	public PropertyInfo getPropertyInfo(Class<?> objectClass,
			String propertyName) {
		List<PropertyInfo> propertyInfos = getPropertyInfos(objectClass);
		for (PropertyInfo propertyInfo : propertyInfos) {
			if (propertyInfo.getSimpleName().equals(propertyName)) {
				return propertyInfo;
			}
		}
		return null;
	}

	/**
	 * @return A collection of PropertyInfos that hold information on how a form
	 *         view should be generated for a given domain class.
	 */
	public List<PropertyInfo> getPropertyInfos(Class<?> objectClass) {
		if (!propertyInfosPerClass.containsKey(objectClass)) {
			propertyInfosPerClass.put(objectClass,
					PropertyInfoFactory.create(providerContainer, objectClass));
		}
		return propertyInfosPerClass.get(objectClass);
	}

	@Override
	public List<PropertyInfo> getPropertyInfos(Class<?> objectClass,
			Filter<PropertyInfo> propertyInfoFilter,
			Comparator<PropertyInfo> propertyInfoComparator) {

		List<PropertyInfo> propertyInfos = getPropertyInfos(objectClass);

		propertyInfos = FilterUtil.filter(propertyInfos, propertyInfoFilter);

		Collections.sort(propertyInfos, propertyInfoComparator);

		return propertyInfos;
	}



}
