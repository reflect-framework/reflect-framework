package nth.introspect.provider.domain.info.type;

import java.lang.reflect.Method;

public class MethodReturnType extends ValueType{

	private static TypeCategory[] NONE_SUPPORTED_CATEGORIES= {};
	
	public MethodReturnType(Method method) {
		super(method.getReturnType(),method, NONE_SUPPORTED_CATEGORIES);
	}

}
