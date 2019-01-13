package nth.reflect.fw.layer5provider.reflection.info.type;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import nth.reflect.fw.ReflectApplication;

public class FirstParameterTypeInfo extends TypeInfo {

	public FirstParameterTypeInfo(ReflectApplication reflectApplication, Method method) {
		super(reflectApplication, getFirstParameterType(method), getFirstGenericParameterType(method));
	}

	private static Class<?> getFirstParameterType(Method method) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes.length != 1) {
			throw new RuntimeException("Method: " + method.getClass().getCanonicalName()
					+ " may have only one parameter if you want to create a: "
					+ FirstParameterTypeInfo.class.getCanonicalName());
		}
		return parameterTypes[0];
	}

	private static Type getFirstGenericParameterType(Method method) {
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if (genericParameterTypes.length != 1) {
			throw new RuntimeException("Method: " + method.getClass().getCanonicalName()
					+ " may have only one generic parameter if you want to create a: "
					+ FirstParameterTypeInfo.class.getCanonicalName());
		}
		return genericParameterTypes[0];
	}

}
