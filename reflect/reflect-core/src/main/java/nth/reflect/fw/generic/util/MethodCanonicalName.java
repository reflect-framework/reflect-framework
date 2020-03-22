package nth.reflect.fw.generic.util;

import java.lang.reflect.Method;

public class MethodCanonicalName {
	public static String getFor(Method method) {
		return method.getDeclaringClass().getCanonicalName() + "." + method.getName();
	}
}
