package nth.reflect.fw.layer5provider.reflection.info.type;

import java.lang.reflect.Method;

/**
 * FIXME: remove this class (see how we did the userinterface editActionMethodParameter confirmActionMethod and showActionMethodResult methods and do the same for field creation) 
 */
public class PropertyType extends ValueType{

	private static TypeCategory[] NONE_SUPPORTED_CATEGORIES= {TypeCategory.NONE};
	
	public PropertyType(Method readMethod) {
		super(readMethod.getReturnType(), readMethod, NONE_SUPPORTED_CATEGORIES);
	}
	
}
