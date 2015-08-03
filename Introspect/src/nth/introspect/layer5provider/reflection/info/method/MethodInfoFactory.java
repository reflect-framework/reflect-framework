package nth.introspect.layer5provider.reflection.info.method;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.valuemodel.factories.MethodValueModelFactory;

public class MethodInfoFactory {
	
	public static List<MethodInfo> create(ReflectionProvider reflectionProvider, PathProvider pathProvider, LanguageProvider languageProvider, Class<?> objectClass) {
		ArrayList<MethodInfo> methodInfos = new ArrayList<MethodInfo>();
		try {
			List<String> propertyNames = getPropertyNames(reflectionProvider, objectClass);

			Method[] methods = objectClass.getMethods();
			List<String> unwantedMethodNames = getUnwantedMethodNames(reflectionProvider, objectClass);
			for (Method method : methods) {
				if (!unwantedMethodNames.contains(method.getName())) {
					String linkedPropertyName = findLinkedPropertyName(method, propertyNames);
					MethodInfo methodInfo = new MethodInfo(pathProvider, languageProvider, method, linkedPropertyName);
					methodInfos.add(methodInfo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// order form properties (note that the formOrder value getter is not dynamic)
		Collections.sort(methodInfos, new MethodInfosOrderComparator());
		return methodInfos;
	}

	private static String findLinkedPropertyName(Method method, List<String> propertyNames) {
		String methodName = method.getName();
		String linkedPropertyName = null;
		for (String propertyName : propertyNames) {
			if (methodName.startsWith(propertyName) && (linkedPropertyName == null || propertyName.length() > linkedPropertyName.length())) {
				linkedPropertyName = propertyName;
			}
		}
		return linkedPropertyName;
	}

	public static List<String> getPropertyNames(ReflectionProvider reflectionProvider, Class<?> objectClass ) {
		List<PropertyInfo> propertyInfos = reflectionProvider.getPropertyInfos(objectClass);
		List<String> propertyNames = new ArrayList<String>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			propertyNames.add(propertyInfo.getSimpleName());
		}
		return propertyNames;
	}

	private static List<String> getUnwantedMethodNames(ReflectionProvider reflectionProvider, Class<?> objectClass) {
		ArrayList<String> unwantedMethodNames = new ArrayList<String>();
		// add property read method names
		try {
			unwantedMethodNames.add("getChildren");// children of service object that represent a HtmlNakedTreeNode need to be ignored as action method
			for (String suffix : ClassInfo.METHOD_NAMES) {
				String unwantedMethodName = MethodValueModelFactory.createMethodName(objectClass.getSimpleName(), suffix);
				unwantedMethodNames.add(unwantedMethodName.toString());
			}
			List<PropertyInfo> propertyInfos = reflectionProvider.getPropertyInfos(objectClass);
			for (PropertyInfo propertyInfo : propertyInfos) {
				try {
					String propertyName = propertyInfo.getSimpleName();
					unwantedMethodNames.add(propertyInfo.getReadMethod().getName());
					unwantedMethodNames.add(propertyInfo.getWriteMethod().getName());
					for (String suffix : PropertyInfo.METHOD_NAMES) {
						String unwantedMethodName = MethodValueModelFactory.createMethodName(propertyName, suffix);
						unwantedMethodNames.add(unwantedMethodName.toString());
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// add method names
		for (Method method : objectClass.getMethods()) {
			if (method.getDeclaringClass() != Object.class) {
				for (String suffix : MethodInfo.METHOD_NAMES) {
					StringBuffer unwantedMethodName = new StringBuffer(method.getName());
					unwantedMethodName.append(StringUtil.firstCharToUpperCase(suffix));
					unwantedMethodNames.add(unwantedMethodName.toString());
				}
			}
		}
		// add object method names
		for (Method method : Object.class.getMethods()) {
			unwantedMethodNames.add(method.getName());
		}
		return unwantedMethodNames;
	}
}
