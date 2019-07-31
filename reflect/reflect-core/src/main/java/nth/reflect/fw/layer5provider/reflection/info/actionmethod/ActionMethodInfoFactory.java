package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;

public class ActionMethodInfoFactory {

	public static List<ActionMethodInfo> createSorted(ProviderContainer providerContainer, Class<?> methodOwner,
			Predicate<Method> methodFilter) {
		ArrayList<ActionMethodInfo> actionMethodInfos = new ArrayList<>();
		Method[] methods = methodOwner.getMethods();
		for (Method method : methods) {
			if (methodFilter.test(method)) {
				try {
					ActionMethodInfo actionMethodInfo = new ActionMethodInfo(providerContainer, method);
					actionMethodInfos.add(actionMethodInfo);
				} catch (InvalidActionMethodException e) {
					// method is not a valid ActionMethod
				}
			}
		}
		sortActionMethodInfos(actionMethodInfos);
		return actionMethodInfos;
	}

	/**
	 * The {@link Order} of {@link ActionMethod}s is not dynamic, so we can already
	 * sort them
	 * 
	 * @param actionMethodInfos
	 */
	private static void sortActionMethodInfos(ArrayList<ActionMethodInfo> actionMethodInfos) {
		Collections.sort(actionMethodInfos, new ActionMethodInfoComparator());
	}

	public static List<ActionMethodInfo> createSorted(ProviderContainer providerContainer, Class<?> domainClass) {
		Predicate<Method> methodFilter = createNoPropertyActionMethodFilter();
		return createSorted(providerContainer, domainClass, methodFilter);
	}

	private static Predicate<Method> createNoPropertyActionMethodFilter() {
		return new Predicate<Method>() {
			@Override
			public boolean test(Method method) {
				Boolean hasNoPropertyActionMethodAnnotation = method.getAnnotation(PropertyActionMethod.class) == null;
				return hasNoPropertyActionMethodAnnotation;
			}
		};
	}

	public static List<ActionMethodInfo> createSorted(ProviderContainer providerContainer, Class<?> domainObjectInfo,
			String propertyName) {
		Predicate<Method> methodFilter = createPropertyActionMethodFilter(propertyName);
		return createSorted(providerContainer, domainObjectInfo, methodFilter);
	}

	private static Predicate<Method> createPropertyActionMethodFilter(String propertyName) {
		return new Predicate<Method>() {
			@Override
			public boolean test(Method method) {
				PropertyActionMethod propertyActionMethodAnnotation = method.getAnnotation(PropertyActionMethod.class);
				return propertyActionMethodAnnotation != null
						&& propertyActionMethodAnnotation.value().equalsIgnoreCase(propertyName);
			}
		};
	}

}
