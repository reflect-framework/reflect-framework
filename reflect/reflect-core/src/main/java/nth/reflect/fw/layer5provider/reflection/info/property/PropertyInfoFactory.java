package nth.reflect.fw.layer5provider.reflection.info.property;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.generic.util.TypeUtil;
import nth.reflect.fw.layer5provider.ProviderContainer;

public class PropertyInfoFactory {

	public static List<PropertyInfo> createSorted(ProviderContainer providerContainer, Class<?> objectClass) {
		ReflectApplication reflectApplication = providerContainer.get(ReflectApplication.class);
		if (!TypeUtil.isDomainType(objectClass, reflectApplication)) {
			//Only domain objects have property info's
			return new ArrayList<>();
		}
		ArrayList<PropertyInfo> propertyInfos = new ArrayList<PropertyInfo>();
		List<Method> getterMethods = getGetterMethods(objectClass);
		for (Method method : getterMethods) {
			PropertyInfo propertyInfo = new PropertyInfo(providerContainer, method);
			propertyInfos.add(propertyInfo);
		}

		// order form properties (note that the order is not dynamic)
		Collections.sort(propertyInfos, new PropertyInfoComparator());
		return propertyInfos;
	}

	public static List<Method> getGetterMethods(Class<?> objectClass) {
		List<Method> getterMethods = new ArrayList<Method>();
		Method[] methods = objectClass.getMethods();
		for (Method method : methods) {
			if (PropertyInfo.isGetterMethod(method)) {
				getterMethods.add(method);
			}
		}
		return getterMethods;
	}

}
