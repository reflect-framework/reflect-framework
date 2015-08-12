package nth.introspect.layer5provider.reflection.behavior;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer5provider.reflection.behavior.disabled.DisabledMethodModel;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenMethodModel;
import nth.introspect.layer5provider.reflection.behavior.icon.IconMethod;
import nth.introspect.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryMethod;
import nth.introspect.layer5provider.reflection.behavior.validation.ValidationMethod;

public class BehavioralMethods {

	public static List<BehavioralMethod> getAll(){
		List<BehavioralMethod> behavioralMethods=new ArrayList<>();
		behavioralMethods.add(new HiddenMethodModel(null));
		behavioralMethods.add(new DisabledMethodModel(null));
		behavioralMethods.add(new IconMethod());
		behavioralMethods.add(new ParameterFactoryMethod());
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

	
	public static String createMethodName(String prefix, String suffix) {
		StringBuffer methodName = new StringBuffer();
		methodName.append(StringUtil.firstCharToLowerCase(prefix));
		methodName.append(StringUtil.firstCharToUpperCase(suffix));
		return methodName.toString();
	}
}
