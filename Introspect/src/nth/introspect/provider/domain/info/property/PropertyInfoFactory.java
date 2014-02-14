package nth.introspect.provider.domain.info.property;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PropertyInfoFactory {

	public static List<PropertyInfo> create(Class<?> introspectedClass) {
		ArrayList<PropertyInfo> propertyInfos = new ArrayList<PropertyInfo>();
		List<Method> getterMethods = getGetterMethods(introspectedClass);
		for (Method method : getterMethods) {
			PropertyInfo propertyInfo = new PropertyInfo(method);
			propertyInfos.add(propertyInfo);
		}

		// order form properties (note that the formOrder value getter is not dynamic)
		Collections.sort(propertyInfos, new FormOrderComparator());
		return propertyInfos;
	}

	public static List<Method> getGetterMethods(Class<?> introspectedClass) {
		List<Method> getterMethods = new ArrayList<Method>();
		Method[] methods = introspectedClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (!"getClass".equals(methodName) && method.getReturnType()!=Void.class && method.getParameterTypes().length==0 &&( methodName.startsWith(PropertyInfo.IS_PREFIX) || methodName.startsWith(PropertyInfo.GET_PREFIX))) {
				getterMethods.add(method);
			}
		}
		return getterMethods;
	}

}
