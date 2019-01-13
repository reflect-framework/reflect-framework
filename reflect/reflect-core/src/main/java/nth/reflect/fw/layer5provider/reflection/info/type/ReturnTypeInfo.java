package nth.reflect.fw.layer5provider.reflection.info.type;

import java.lang.reflect.Method;

import nth.reflect.fw.ReflectApplication;

public class ReturnTypeInfo extends TypeInfo {

	public ReturnTypeInfo(ReflectApplication reflectApplication, Method method) {
		super(reflectApplication, method.getReturnType(), method.getGenericReturnType());
	}

}
