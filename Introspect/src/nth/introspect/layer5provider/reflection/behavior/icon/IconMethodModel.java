package nth.introspect.layer5provider.reflection.behavior.icon;

import java.lang.reflect.Method;
import java.net.URI;

import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.reflection.behavior.BehaviorMethodInvokeException;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;

/**
 * Model that returns the value of the {@link IconMethod}
 * 
 * @author nilsth
 *
 */
public class IconMethodModel  implements IconModel {

	private final Method iconMethod;
	private final URI imageFolderUri;

	public IconMethodModel(Method iconMethod, URI imageFolderUri) {
		this.iconMethod = iconMethod;
		this.imageFolderUri = imageFolderUri;
	}


	@Override
	public URI getURI(Object obj) {
		Object[] arguments = new Object[0];
		try {
			String iconURI = (String) iconMethod.invoke(obj, arguments);
			try {
				URI uri = IconUriFactory.create(iconURI, imageFolderUri);
				return uri;
			} catch (Exception exception) {
				return null;
			}
		} catch (Exception e) {
			throw new BehaviorMethodInvokeException(iconMethod);
		}
	}
}
