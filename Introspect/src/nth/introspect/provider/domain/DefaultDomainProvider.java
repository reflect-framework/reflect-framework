package nth.introspect.provider.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import nth.introspect.filter.Filter;
import nth.introspect.filter.FilterUtil;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.domain.info.classinfo.ClassInfoFactory;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfoFactory;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.property.PropertyInfoFactory;
import nth.introspect.provider.domain.info.property.TableOrderComparator;
import nth.introspect.provider.domain.info.property.TableVisibleFilter;

public class DefaultDomainProvider implements DomainProvider {
	private List<Object> serviceObjects;
	private HashMap<Class<?>, ClassInfo> classInfos;
	private HashMap<Class<?>, List<PropertyInfo>> propertyInfosPerClass;
	private HashMap<Class<?>, List<MethodInfo>> methodInfosPerClass;
	private List<PropertyChangeListener> propertyChangeListeners;

	public DefaultDomainProvider() {
		serviceObjects = new ArrayList<Object>();
		classInfos = new HashMap<Class<?>, ClassInfo>();
		propertyInfosPerClass = new HashMap<Class<?>, List<PropertyInfo>>();
		methodInfosPerClass = new HashMap<Class<?>, List<MethodInfo>>();
		propertyChangeListeners = new ArrayList<PropertyChangeListener>();
	}

	public DefaultDomainProvider(List<Class<?>> serviceClasses) {
		this();
		for (Class<?> serviceClass : serviceClasses) {
			getServiceObject(serviceClass);
		}
	}

	@Override
	public void addPropertyChangeListener(
			PropertyChangeListener propertyChangeListener) {
		propertyChangeListeners.add(propertyChangeListener);
	}

	public Object findServiceObject(Class<?> serviceClass) {
		for (Object serviceObject : serviceObjects) {
			if (serviceObject.getClass() == serviceClass) {
				return serviceObject;
			}
		}
		return null;
	}

	public ClassInfo getClassInfo(Class<?> introspectedClass) {
		if (!classInfos.containsKey(introspectedClass)) {
			classInfos.put(introspectedClass,
					ClassInfoFactory.create(introspectedClass));
		}
		return classInfos.get(introspectedClass);
	}

	/**
	 * @return A collection of ActionInfos that hold information on what actions
	 *         menus in table view and form vieuws should be generated for a
	 *         given service or domain class.
	 */
	public List<MethodInfo> getMethodInfos(Class<?> introspectedClass) {
		if (!methodInfosPerClass.containsKey(introspectedClass)) {
			methodInfosPerClass.put(introspectedClass,
					MethodInfoFactory.create(introspectedClass));
		}
		return methodInfosPerClass.get(introspectedClass);
	}

	@Override
	public List<MethodInfo> getMethodInfos(Class<?> introspectedClass,
			Filter<MethodInfo> methodInfoFilter) {
		List<MethodInfo> methodInfos = getMethodInfos(introspectedClass);
		List<MethodInfo> foundMethodInfos = FilterUtil.filter(methodInfos,
				methodInfoFilter);
		return foundMethodInfos;
	}

	@Override
	public List<PropertyInfo> getOrderedAndVisiblePropertyInfos(
			Class<?> introspectedClass) {
		List<PropertyInfo> propertyInfos = getPropertyInfos(introspectedClass);
		// only return visible properties
		FilterUtil.filter(propertyInfos, new TableVisibleFilter());
		// order properties
		Collections.sort(propertyInfos, new TableOrderComparator());
		return propertyInfos;
	}

	@Override
	public List<PropertyInfo> getOrderedPropertyInfos(Class<?> introspectedClass) {
		List<PropertyInfo> propertyInfos = getPropertyInfos(introspectedClass);
		// order properties
		Collections.sort(propertyInfos, new TableOrderComparator());
		return propertyInfos;
	}

	public PropertyInfo getPropertyInfo(Class<?> introspectedClass,
			String propertyName) {
		List<PropertyInfo> propertyInfos = getPropertyInfos(introspectedClass);
		for (PropertyInfo propertyInfo : propertyInfos) {
			if (propertyInfo.getName().equals(propertyName)) {
				return propertyInfo;
			}
		}
		return null;
	}

	/**
	 * @return A collection of PropertyInfos that hold information on how a form
	 *         view should be generated for a given domain class.
	 */
	public List<PropertyInfo> getPropertyInfos(Class<?> introspectedClass) {
		if (!propertyInfosPerClass.containsKey(introspectedClass)) {
			propertyInfosPerClass.put(introspectedClass,
					PropertyInfoFactory.create(introspectedClass));
		}
		return propertyInfosPerClass.get(introspectedClass);
	}

	@Override
	public List<PropertyInfo> getPropertyInfos(Class<?> introspectedClass,
			Filter<PropertyInfo> propertyInfoFilter,
			Comparator<PropertyInfo> propertyInfoComparator) {

		List<PropertyInfo> propertyInfos = getPropertyInfos(introspectedClass);

		propertyInfos = FilterUtil.filter(propertyInfos, propertyInfoFilter);

		Collections.sort(propertyInfos, propertyInfoComparator);

		return propertyInfos;
	}

	public Object getServiceObject(Class<?> serviceClass) {
		Object serviceObject = findServiceObject(serviceClass);
		if (serviceObject == null) {
			try {
				serviceObject = serviceClass.newInstance();
				serviceObjects.add(serviceObject);
			} catch (Exception e) {
				// failed. The constructor of the service class probably takes
				// parameters. Use addServiceObject instead
				throw new RuntimeException(e);
			}
		}
		return serviceObject;
	}

	public List<Object> getServiceObjects() {
		return serviceObjects;
	}

	@Override
	public void invokePropertyChangeListeners(Object introspectedObject,
			String propertyName, PropertyChangeType propertyChangeType) {
		if (introspectedObject == null) {
			throw new NullPointerException();
		}
		PropertyInfo propertyInfo = getPropertyInfo(
				introspectedObject.getClass(), propertyName);
		for (PropertyChangeListener propertyChangeListener : propertyChangeListeners) {
			if (propertyChangeListener.getIntrospectedObject() == introspectedObject
					&& propertyChangeListener.getPropertyInfo() == propertyInfo) {
				propertyChangeListener.propertyChanged(propertyChangeType);
			}
		}
	}

	@Override
	public void removePropertyChangeListener(
			PropertyChangeListener propertyChangeListener) {
		propertyChangeListeners.remove(propertyChangeListener);
	}

}
