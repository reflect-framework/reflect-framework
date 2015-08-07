package nth.introspect.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public class ActionMethodInfoFactory {

	public static List<ActionMethodInfo> create(
			ProviderContainer providerContainer, Class<?> objectType) {
		ArrayList<ActionMethodInfo> actionMethodInfos = new ArrayList<ActionMethodInfo>();
		ReflectionProvider reflectionProvider = providerContainer
				.get(ReflectionProvider.class);
		PathProvider pathProvider = providerContainer.get(PathProvider.class);
		LanguageProvider languageProvider = providerContainer
				.get(LanguageProvider.class);
		List<PropertyInfo> propertyInfos = reflectionProvider
				.getPropertyInfos(objectType);
		Method[] methods = objectType.getMethods();
		for (Method method : methods) {
			if (ActionMethodInfo.isActionMethod(method, propertyInfos)) {
				String linkedPropertyName = findLinkedPropertyName(method,
						propertyInfos);// TODO make actionMethods part of
										// ClassInfo's and PropertyInfo's!!!

				ActionMethodInfo actionMethodInfo = new ActionMethodInfo(
						pathProvider, languageProvider, method,
						linkedPropertyName);
				actionMethodInfos.add(actionMethodInfo);
			}
		}
		// order form properties (note that the formOrder value getter is not
		// dynamic)
		Collections.sort(actionMethodInfos, new ActionMethodInfoComparator());
		return actionMethodInfos;
	}

	private static String findLinkedPropertyName(Method method,
			List<PropertyInfo> propertyInfos) {
		String methodName = method.getName();
		for (PropertyInfo propertyInfo : propertyInfos) {
			String propertyName = StringUtil.firstCharToUpperCase(propertyInfo
					.getSimpleName());
			if (methodName.endsWith(propertyName)
					&& methodName.length() > propertyName.length()) {
				return propertyName;
			}
		}
		return null;
	}

}
