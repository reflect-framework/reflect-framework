package nth.reflect.fw.layer5provider.reflection.behavior.applicationicon;

import java.lang.reflect.Method;
import java.net.URL;

import nth.reflect.fw.layer5provider.reflection.behavior.BehaviorMethodInvokeException;

/**
 * Model that returns the value of the {@link FontIconMethod}
 * 
 * @author nilsth
 *
 */
public class IconMethodModel  implements IconModel {

	private final Method iconMethod;

	public IconMethodModel(Method iconMethod) {
		this.iconMethod = iconMethod;
	}


	@Override
	public URL getURL(Object obj) {
		Object[] arguments = new Object[0];
		try {
			String iconURL = (String) iconMethod.invoke(obj, arguments);
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
