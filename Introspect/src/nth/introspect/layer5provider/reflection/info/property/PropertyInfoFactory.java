package nth.introspect.layer5provider.reflection.info.property;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;

public class PropertyInfoFactory {

	public static List<PropertyInfo> create(ReflectionProvider reflectionProvider, LanguageProvider languageProvider, Class<?> objectClass) {
		ArrayList<PropertyInfo> propertyInfos = new ArrayList<PropertyInfo>();
		List<Method> getterMethods = getGetterMethods(objectClass);
		for (Method method : getterMethods) {
			PropertyInfo propertyInfo = new PropertyInfo(reflectionProvider, languageProvider, method);
			propertyInfos.add(propertyInfo);
		}

		// order form properties (note that the formOrder value getter is not dynamic)
		Collections.sort(propertyInfos, new FormOrderComparator());
		return propertyInfos;
	}

	public static List<Method> getGetterMethods(Class<?> objectClass) {
		List<Method> getterMethods = new ArrayList<Method>();
		Method[] methods = objectClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			boolean isGetClass = "getClass".equals(methodName);
			boolean hasReturnValue = method.getReturnType()!=Void.class;
			boolean hasNoParameters = method.getParameterTypes().length==0;
			boolean isEnumGetDeclairingClass=objectClass.isEnum() && "getDeclaringClass".equals(methodName);
			boolean startsWithIs = methodName.startsWith(PropertyInfo.IS_PREFIX);
			boolean startsWithGet = methodName.startsWith(PropertyInfo.GET_PREFIX);
			if (!isGetClass && hasReturnValue && hasNoParameters && !isEnumGetDeclairingClass &&( startsWithIs || startsWithGet)) {
				getterMethods.add(method);
			}
		}
		return getterMethods;
	}

}
