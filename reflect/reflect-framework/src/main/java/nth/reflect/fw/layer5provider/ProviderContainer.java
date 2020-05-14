package nth.reflect.fw.layer5provider;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.container.exception.ClassAlreadyRegisteredInContainerException;
import nth.reflect.fw.layer5provider.url.UrlProvider;

/**
 * This {@link DependencyInjectionContainer} represents the
 * {@link ProviderLayer}
 * 
 * @author nilsth
 *
 */
public class ProviderContainer extends DependencyInjectionContainer {

	public ProviderContainer(ReflectApplication application) {
		super();

		add(application);

		addProvidersFromApplication(application);

		initializeUrlProvider(application);
	}

	private void initializeUrlProvider(ReflectApplication application) {
		Class<? extends UrlProvider> urlProviderClass = application.getUrlProviderClass();
		get(urlProviderClass);
	}

	private void addProvidersFromApplication(ReflectApplication application) {
		List<Method> methods = findApplicationMethodsThatReturnProvider(application);
		for (Method method : methods) {
			method.setAccessible(true);
			try {
				Object provider = method.invoke(application);
				if (provider == null) {
					throw new ProviderNotDeclaredException(method);
				}
				if (provider instanceof Class) {
					Class<?> providerClass = (Class<?>) provider;
					add(providerClass);
				} else {
					add(provider);
				}
			} catch (ClassAlreadyRegisteredInContainerException e) {
				// do nothing
			} catch (Exception e) {
				throw new ProviderNotDeclaredException(method, e);
			}
		}
	}

	private List<Method> findApplicationMethodsThatReturnProvider(ReflectApplication application) {
		Set<Method> allMethods = findAllMethods(application.getClass());
		ProviderMethodFilter providerMethodFilter = new ProviderMethodFilter();
		List<Method> methodsThatReturnProvider = allMethods
				.stream()
				.filter(providerMethodFilter)
				.collect(Collectors.toList());
		return methodsThatReturnProvider;
	}

	private Set<Method> findAllMethods(Class<?> objectClass) {
		Set<Method> allMethods = new HashSet<Method>();
		Method[] declaredMethods = objectClass.getDeclaredMethods();
		allMethods.addAll(Arrays.asList(declaredMethods));
		Method[] methods = objectClass.getMethods();
		allMethods.addAll(Arrays.asList(methods));
		Class<?> superclass = objectClass.getSuperclass();
		if (superclass != null && superclass != Object.class) {
			Class<?> superClass = objectClass.getSuperclass();
			Set<Method> superClassMethods = findAllMethods(superClass);
			allMethods.addAll(superClassMethods);
		}
		return allMethods;
	}

}
