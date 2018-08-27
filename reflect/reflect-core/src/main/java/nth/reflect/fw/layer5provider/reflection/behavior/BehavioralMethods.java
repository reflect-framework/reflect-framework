package nth.reflect.fw.layer5provider.reflection.behavior;

import java.lang.reflect.Method;

import nth.reflect.fw.layer5provider.reflection.behavior.applicationicon.ApplicationIconMethod;
import nth.reflect.fw.layer5provider.reflection.behavior.disabled.DisabledMethod;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconMethod;
import nth.reflect.fw.layer5provider.reflection.behavior.hidden.HiddenMethod;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactoryMethod;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationMethod;

public class BehavioralMethods {
	public static HiddenMethod HIDDEN=new HiddenMethod(); 
	public static DisabledMethod DISABLED=new DisabledMethod();
	public static FontIconMethod FONT_ICON=new FontIconMethod();
	public static ApplicationIconMethod APPLICATION_ICON=new ApplicationIconMethod();
	public static ParameterFactoryMethod PARAMETER_FACTORY=new ParameterFactoryMethod();
	public static ValidationMethod VALIDATION=new ValidationMethod();
	public static BehavioralMethod[] ALL=new BehavioralMethod[] {HIDDEN, DISABLED, FONT_ICON, APPLICATION_ICON, PARAMETER_FACTORY, VALIDATION};
	
	public static boolean isBehavioralMethod(Method method) {
		for (BehavioralMethod behavioralMethod: ALL) {
			if(behavioralMethod.isValid(method)) {
				return true;
			}
		}
		return false;
	}

}
