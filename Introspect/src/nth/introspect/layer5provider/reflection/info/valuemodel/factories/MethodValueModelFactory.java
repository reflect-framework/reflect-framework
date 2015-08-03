package nth.introspect.layer5provider.reflection.info.valuemodel.factories;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.generic.valuemodel.ValueModels;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.valuemodel.impl.MethodValue;

public class MethodValueModelFactory {

	public static HashMap<String, ReadOnlyValueModel> create(ClassInfo classInfo, String[] names) {
		return create(classInfo.getObjectClass(), classInfo.getSimpleName(), names);
	}

	public static Map<String, ReadOnlyValueModel> create(MethodInfo methodinfo, String[] names) {
		return create(methodinfo.getMethod().getDeclaringClass(), methodinfo.getSimpleName(), names);
	}
	
	public static HashMap<String, ReadOnlyValueModel> create(PropertyInfo propertyInfo, String[] names) {
		return create(propertyInfo.getGetterMethod().getDeclaringClass(), propertyInfo.getSimpleName(), names);
	}

	public static HashMap<String, ReadOnlyValueModel> create(Class<?> serviceOrDomainClass, String methodOrPropertyOrClassName, String[] names) {
		ValueModels valueModels = new ValueModels();
		for (String name : names) {
			try {
				// method
				Class<?>[] signature = new Class[0];
				String methodName=createMethodName(methodOrPropertyOrClassName, name);
				Method method = serviceOrDomainClass.getMethod(methodName, signature);
				ReadOnlyValueModel methodValue = new MethodValue(method);
				valueModels.put(name, methodValue);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return valueModels;
	}

	public static String createMethodName(String prefix, String suffix) {
		StringBuffer methodName = new StringBuffer();
		methodName.append(StringUtil.firstCharToLowerCase(prefix));
		methodName.append(StringUtil.firstCharToUpperCase(suffix));
		return methodName.toString();
	}
	

	

}
