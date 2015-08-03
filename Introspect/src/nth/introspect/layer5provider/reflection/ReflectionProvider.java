package nth.introspect.layer5provider.reflection;

import java.util.Comparator;
import java.util.List;

import nth.introspect.container.ConstructionInjection;
import nth.introspect.generic.filter.Filter;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

/**
 * The {@link ReflectionProvider} provides information on objects (
 * {@link ClassInfo}), properties ({@link PropertyInfo}) of {@link ActionMethod}
 * ({@link MethodInfo}) using <a
 * href="https://en.wikipedia.org/wiki/Reflection_(computer_programming)"
 * >reflection</a>. The {@link UserInterfaceController} uses this information to
 * know how the user interface should look like and behave.
 * 
 * You can use the {@link ReflectionProvider} in your code when you need meta
 * information (See {@link ConstructionInjection})
 * 
 * TODO Code example
 * 
 * @author nilsth
 *
 */
public interface ReflectionProvider extends Provider {

	ClassInfo getClassInfo(Class<?> objectClass);

	PropertyInfo getPropertyInfo(Class<?> objectClass, String propertyName);

	List<PropertyInfo> getPropertyInfos(Class<?> objectClass);

	List<PropertyInfo> getPropertyInfos(Class<?> objectClass,
			Filter<PropertyInfo> propertyInfoFilter,
			Comparator<PropertyInfo> propertyInfoComparator);

	// TODO move to classInfo

	List<PropertyInfo> getOrderedPropertyInfos(Class<?> objectClass);

	// TODO move to classInfo

	List<PropertyInfo> getOrderedAndVisiblePropertyInfos(
			Class<?> objectClass);

	// TODO move to classInfo

	List<MethodInfo> getMethodInfos(Class<?> objectClass);

	// TODO move to classInfo

	List<MethodInfo> getMethodInfos(Class<?> objectClass,
			Filter<MethodInfo> methodInfoFilter);
	// TODO move to classInfo
}
