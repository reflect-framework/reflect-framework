package nth.introspect.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public class ActionMethodInfoFactory {

	public static List<ActionMethodInfo> createSorted(
			ProviderContainer providerContainer, ClassInfo classInfo) {
		ArrayList<ActionMethodInfo> actionMethodInfos = new ArrayList<ActionMethodInfo>();
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		Method[] methods = classInfo.getObjectClass().getMethods();
		for (Method method : methods) {
			String linkedPropertyName = findLinkedPropertyName(method,
					propertyInfos);
			// TODO make actionMethods part of ClassInfo's and PropertyInfo's!!!
			try {
				ActionMethodInfo actionMethodInfo = new ActionMethodInfo(
						providerContainer, method, linkedPropertyName);
				actionMethodInfos.add(actionMethodInfo);
			} catch (InvalidActionMethodException e) {
				// method is not a valid ActionMethod
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
