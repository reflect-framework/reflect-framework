package nth.introspect.layer5provider.reflection;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.filter.FilterUtil;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfoFactory;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.method.MethodInfoFactory;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfoFactory;
import nth.introspect.layer5provider.reflection.info.property.TableOrderComparator;
import nth.introspect.layer5provider.reflection.info.property.TableVisibleFilter;

public class DefaultReflectionProvider implements ReflectionProvider {
	private final HashMap<Class<?>, ClassInfo> classInfos;
	private final HashMap<Class<?>, List<PropertyInfo>> propertyInfosPerClass;
	private final HashMap<Class<?>, List<MethodInfo>> methodInfosPerClass;
	private final LanguageProvider languageProvider;
	private final PathProvider pathProvider;

	public DefaultReflectionProvider(PathProvider pathProvider, LanguageProvider languageProvider) {
		this.pathProvider = pathProvider;
		this.languageProvider = languageProvider;
		classInfos = new HashMap<Class<?>, ClassInfo>();
		propertyInfosPerClass = new HashMap<Class<?>, List<PropertyInfo>>();
		methodInfosPerClass = new HashMap<Class<?>, List<MethodInfo>>();
	}


	public ClassInfo getClassInfo(Class<?> introspectedClass) {
		if (!classInfos.containsKey(introspectedClass)) {
			classInfos.put(introspectedClass,
					ClassInfoFactory.create(this, pathProvider, languageProvider, introspectedClass));
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
					MethodInfoFactory.create(this, pathProvider, languageProvider, introspectedClass));
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
					PropertyInfoFactory.create(this, languageProvider, introspectedClass));
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



}
