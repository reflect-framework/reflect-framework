package nth.reflect.fw.layer5provider.stringconverter;

import java.lang.reflect.Method;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class InfoFactory {

	private static final String NO_FORMAT = null;

	public static TypeInfo create(ReflectApplication application, String domainObjectGetterMethod) {
		return create(application, domainObjectGetterMethod, NO_FORMAT);
	}

	public static TypeInfo create(ReflectApplication application, String domainObjectGetterMethod,
			String formatPattern) {
		Method method = findMethod(domainObjectGetterMethod);
		ReturnTypeInfo typeInfo = new ReturnTypeInfo(application, method);
		return typeInfo;
	}

	private static Method findMethod(String domainObjectGetterMethod) {
		Method[] allMethods = AllFeatureDomainObject.class.getDeclaredMethods();
		for (Method method : allMethods) {
			if (method.getName().equals(domainObjectGetterMethod)) {
				return method;
			}
		}
		throw new MethodNotFoundException(domainObjectGetterMethod);
	}

}
