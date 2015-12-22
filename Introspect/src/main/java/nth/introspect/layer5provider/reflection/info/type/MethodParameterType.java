package nth.introspect.layer5provider.reflection.info.type;

import java.lang.reflect.Method;

public class MethodParameterType extends ValueType{

	private static TypeCategory[] NONE_SUPPORTED_CATEGORIES= {TypeCategory.JAVA_TYPE, TypeCategory.DOWNLOAD_STREAM_TYPE, TypeCategory.URI_TYPE, TypeCategory.COLLECTION_TYPE};
	
	public MethodParameterType(Method method) {
		super(getParameterType(method), method, NONE_SUPPORTED_CATEGORIES);
	}

	private static Class<?> getParameterType(Method method) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		switch (parameterTypes.length) {
		case 0:
			return null;
		case 1:
			return parameterTypes[0];
		default:
			throw new RuntimeException("Method "+method.getDeclaringClass().getCanonicalName()+"."+method.getName()+" may not contain multiple parameters!");
		}
	}
	

}
