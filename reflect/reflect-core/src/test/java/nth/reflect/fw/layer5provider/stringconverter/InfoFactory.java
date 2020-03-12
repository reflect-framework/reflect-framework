package nth.reflect.fw.layer5provider.stringconverter;

import java.lang.reflect.Method;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

/**
 * Utility class to create a {@link StringConverterFactoryInfo} for Junit
 * testing
 * 
 * @author nilsth
 *
 */
public class InfoFactory {

	public static StringConverterFactoryInfo create(DependencyInjectionContainer container,
			String domainObjectGetterMethod) {
		return create(container, domainObjectGetterMethod, null);
	}

	public static StringConverterFactoryInfo create(DependencyInjectionContainer container,
			String domainObjectGetterMethod, String formatPattern) {
		Method method = findMethod(domainObjectGetterMethod);
		ReflectApplication application = container.get(ReflectApplication.class);
		ReturnTypeInfo typeInfo = new ReturnTypeInfo(application, method);
		StringConverterFactoryInfo stringConverterFactoryInfo = new StringConverterFactoryInfo(typeInfo, container,
				formatPattern);
		return stringConverterFactoryInfo;
	}

	private static Method findMethod(String domainObjectGetterMethod) {
		Method[] allMethods = DomainObject.class.getDeclaredMethods();
		for (Method method : allMethods) {
			if (method.getName().equals(domainObjectGetterMethod)) {
				return method;
			}
		}
		throw new RuntimeException(
				"Could not find method " + domainObjectGetterMethod + " in " + DomainObject.class.getCanonicalName());
	}

}
