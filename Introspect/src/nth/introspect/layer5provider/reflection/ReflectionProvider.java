package nth.introspect.layer5provider.reflection;

import java.util.Comparator;
import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public interface ReflectionProvider extends Provider {

	ClassInfo getClassInfo(Class<?> introspectedClass);

	PropertyInfo getPropertyInfo(Class<?> introspectedClass, String propertyName);

	List<PropertyInfo> getPropertyInfos(Class<?> introspectedClass);

	List<PropertyInfo> getPropertyInfos(Class<?> introspectedClass,
			Filter<PropertyInfo> propertyInfoFilter,
			Comparator<PropertyInfo> propertyInfoComparator);

	List<PropertyInfo> getOrderedPropertyInfos(Class<?> introspectedClass);

	List<PropertyInfo> getOrderedAndVisiblePropertyInfos(
			Class<?> introspectedClass);

	List<MethodInfo> getMethodInfos(Class<?> introspectedClass);

	List<MethodInfo> getMethodInfos(Class<?> introspectedClass,
			Filter<MethodInfo> methodInfoFilter);


}
