package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.behavior.order.Order;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.exception.ActionMethodException;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class ActionMethodInfoFactory {

	public static List<ActionMethodInfo> createSorted(ProviderContainer providerContainer,
			ServiceClassInfo serviceClassInfo) {
		Predicate<Method> methodFilter = createAllActionMethodFilter(serviceClassInfo);
		Class<?> serviceClass = serviceClassInfo.getType();
		return createSorted(providerContainer, serviceClass, methodFilter);
	}

	private static Predicate<Method> createAllActionMethodFilter(ServiceClassInfo serviceClassInfo) {
		return new Predicate<Method>() {

			@Override
			public boolean test(Method t) {
				return true;
			}
		};
	}

	public static List<ActionMethodInfo> createSorted(ProviderContainer providerContainer,
			DomainClassInfo domainClassInfo) {
		Predicate<Method> methodFilter = createNoPropertyActionMethodFilter(domainClassInfo);
		Class<?> domainClass = domainClassInfo.getType();
		return createSorted(providerContainer, domainClass, methodFilter);
	}

	private static Predicate<Method> createNoPropertyActionMethodFilter(DomainClassInfo domainClassInfo) {
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
		List<String> propertySimpleNames = propertyInfos.stream().map(p -> p.getSimpleName())
				.collect(Collectors.toList());
		return new Predicate<Method>() {

			@Override
			public boolean test(Method method) {
				boolean isPropertyActionMethod = propertySimpleNames.stream()
						.anyMatch(p -> method.getName().startsWith(p));
				return !isPropertyActionMethod;
			}
		};
	}

	public static List<ActionMethodInfo> createSorted(ProviderContainer providerContainer, PropertyInfo propertyInfo) {
		String simpleName = propertyInfo.getSimpleName();
		Predicate<Method> methodFilter = new PropertyActionMethodFilter(simpleName);
		Class<?> domainClass = propertyInfo.getDomainClass();
		return createSorted(providerContainer, domainClass, methodFilter);
	}

	public static List<ActionMethodInfo> createSorted(ProviderContainer providerContainer, Class<?> methodOwner,
			Predicate<Method> methodFilter) {
		ArrayList<ActionMethodInfo> actionMethodInfos = new ArrayList<>();
		Method[] methods = methodOwner.getMethods();
		for (Method method : methods) {
			if (methodFilter.test(method)) {
				try {
					ActionMethodInfo actionMethodInfo;
					if (methodFilter instanceof PropertyActionMethodFilter) {
						PropertyActionMethodFilter filter = (PropertyActionMethodFilter) methodFilter;
						String propertyName = filter.getPropertyName();
						actionMethodInfo = new ActionMethodInfo(providerContainer, method, propertyName);
					} else {
						actionMethodInfo = new ActionMethodInfo(providerContainer, method);
					}
					actionMethodInfos.add(actionMethodInfo);
				} catch (ActionMethodException e) {
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

}
