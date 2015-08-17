package nth.introspect.layer5provider.reflection.behavior;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.reflection.behavior.disabled.DisabledMethodModel;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenCollectionModel;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenMethodModel;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenModel;
import nth.introspect.layer5provider.reflection.behavior.icon.IconMethod;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryMethodModel;
import nth.introspect.layer5provider.reflection.behavior.validation.ValidationMethod;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

public class BehavioralMethodFactory {

	public static List<BehavioralMethod> getAll(){
		List<BehavioralMethod> behavioralMethods=new ArrayList<>();
		behavioralMethods.add(new HiddenMethodModel(null));
		behavioralMethods.add(new DisabledMethodModel(null));
		behavioralMethods.add(new IconMethod());
		behavioralMethods.add(new ParameterFactoryMethodModel(null));
		behavioralMethods.add(new ValidationMethod());
		return behavioralMethods;
	}
			
	
	public static boolean isBehavioralMethod(Method method) {
		List<BehavioralMethod> behavioralMethods = getAll();
		for (BehavioralMethod behavioralMethod: behavioralMethods) {
			if(behavioralMethod.isBehavioralMethod(method)) {
				return true;
			}
		}
		return false;
	}

	
	private static String createMethodName(String owner, String behavioralName) {
		StringBuffer methodName = new StringBuffer();
		methodName.append(StringUtil.firstCharToLowerCase(owner));
		methodName.append(StringUtil.firstCharToUpperCase(behavioralName));
		return methodName.toString();
	}
	
	public static Method create(Method method, String behaviourName) {
		String behavioralMethodName = getBehavioralMethodName(method, behaviourName);
		Class<?> methodOwner = method.getDeclaringClass();
		Class<?>[] parameterTypes = new Class<?>[0];
		try {
			Method behavioralMethod = methodOwner.getMethod(behavioralMethodName,
					parameterTypes);
			return behavioralMethod;
		} catch (Exception e) {
			return null;// method does not exist
		}
	}

	private static String getBehavioralMethodName(Method method, String behaviourName) {
		if (PropertyInfo.isGetterMethod(method)) {
			return getBehavioralMethodNameForProperty(method, behaviourName);
		} else {
			return createMethodName(method.getName(), behaviourName);
		}
	}

	private static String getBehavioralMethodNameForProperty(Method getterMethod, String behaviourName) {
		String propertyName;
		String methodName = getterMethod.getName();
		if (methodName.startsWith(PropertyInfo.IS_PREFIX)) {
			propertyName = methodName
					.substring(PropertyInfo.IS_PREFIX.length());
		} else {
			propertyName = methodName.substring(PropertyInfo.GET_PREFIX
					.length());
		}
		String behavioralMethodName = createMethodName(propertyName, behaviourName);
		return behavioralMethodName;
	}

}
