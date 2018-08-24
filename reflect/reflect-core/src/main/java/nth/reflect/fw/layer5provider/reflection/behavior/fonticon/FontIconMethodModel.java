package nth.reflect.fw.layer5provider.reflection.behavior.fonticon;

import java.lang.reflect.Method;
import java.net.URL;

import nth.reflect.fw.layer5provider.reflection.behavior.BehaviorMethodInvokeException;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrl;

/**
 * Model that returns the value of the {@link FontIconMethod}
 * 
 * @author nilsth
 *
 */
public class FontIconMethodModel  implements FontIconModel {

	private final Method iconMethod;

	public FontIconMethodModel(Method iconMethod) {
		this.iconMethod = iconMethod;
	}


	@Override
	public URL getFontIconUrl(Object obj) {
		Object[] arguments = new Object[0];
		try {
			String iconURL = (String) iconMethod.invoke(obj, arguments);
			try {
				return new FontIconUrl(iconURL).toInternalURL();
			} catch (Exception exception) {
				return null;
			}
		} catch (Exception exception) {
			throw new BehaviorMethodInvokeException(iconMethod, exception);
		}
	}
}
