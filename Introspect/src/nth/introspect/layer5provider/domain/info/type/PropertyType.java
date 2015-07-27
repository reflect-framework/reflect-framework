package nth.introspect.layer5provider.domain.info.type;

import java.lang.reflect.Method;

public class PropertyType extends ValueType{

	private static TypeCategory[] NONE_SUPPORTED_CATEGORIES= {TypeCategory.NONE};
	
	public PropertyType(Method readMethod) {
		super(readMethod.getReturnType(), readMethod, NONE_SUPPORTED_CATEGORIES);
	}
	
}
