package nth.reflect.fw.layer5provider.reflection.behavior.applicationicon;

import java.lang.reflect.Method;
import java.net.URL;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.reflection.behavior.BehaviorMethodInvokeException;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconMethod;

/**
 * Model that returns the value of the {@link FontIconMethod}
 * 
 * @author nilsth
 *
 */
public class ApplicationIconMethodModel  implements ApplicationIconModel {

	private final Method iconMethod;
	private final ReflectApplication reflectApplication;

	public ApplicationIconMethodModel(ReflectApplication reflectApplication, Method iconMethod) {
		this.reflectApplication = reflectApplication;
		this.iconMethod = iconMethod;
	}


	@Override
	public URL getUrl() {
		Object[] arguments = new Object[0];
		try {
			String iconURL = (String) iconMethod.invoke(reflectApplication, arguments);
			try {
				return new URL(iconURL);
			} catch (Exception exception) {
				return null;
			}
		} catch (Exception exception) {
			throw new BehaviorMethodInvokeException(iconMethod, exception);
		}
	}
}
