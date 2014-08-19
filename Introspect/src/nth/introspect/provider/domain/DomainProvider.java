package nth.introspect.provider.domain;

import java.util.Comparator;
import java.util.List;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.filter.Filter;
import nth.introspect.provider.Provider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.property.PropertyInfo;

public interface DomainProvider extends Provider{

	Object getServiceObject(Class<?> serviceClass);

/**
 * @deprecated use {@link IntrospectContainer#getFrontEndServiceObjects()}
 */
	List<Object> getServiceObjects();

	ClassInfo getClassInfo(Class<?> introspectedClass);

	PropertyInfo getPropertyInfo(Class<?> introspectedClass, String propertyName);
	
	List<PropertyInfo> getPropertyInfos(Class<?> introspectedClass);
	
	List<PropertyInfo> getPropertyInfos(Class<?> introspectedClass, Filter<PropertyInfo> propertyInfoFilter, Comparator<PropertyInfo> propertyInfoComparator);

	List<PropertyInfo> getOrderedPropertyInfos(Class<?> introspectedClass);
	
	List<PropertyInfo> getOrderedAndVisiblePropertyInfos(Class<?> introspectedClass);
	
	List<MethodInfo> getMethodInfos(Class<?> introspectedClass);

	List<MethodInfo> getMethodInfos(Class<?> introspectedClass, Filter<MethodInfo> methodInfoFilter);

	void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);

	void removePropertyChangeListener(PropertyChangeListener propertyChangeListener);

	void invokePropertyChangeListeners(Object introspectedObject, String propertyName, PropertyChangeType propertyChangeType);

	

}
