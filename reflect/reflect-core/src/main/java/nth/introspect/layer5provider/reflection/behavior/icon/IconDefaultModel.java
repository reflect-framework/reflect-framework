package nth.introspect.layer5provider.reflection.behavior.icon;

import java.lang.reflect.Method;
import java.net.URL;

import nth.introspect.IntrospectFramework;
import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * The {@link IntrospectFramework} will try to find an image with the same
 * {@link Class}Name or {@link ActionMethod}Name but with the “.png” extension
 * in the same package.
 * <ul>
 * <li>Filename signature for object icon: &lt;package&gt;/&lt;className&gt;.png
 * (e.g. c:/MyProject/bin/com/acme/web/shop/customer/CustomerService.png)</li>
 * <li>Filename signature for ActionMethod icon:
 * &lt;package&gt;/&lt;className&gt;_&lt;actionMethodName&gt;.png (e.g.
 * c:/MyProject/bin/com/acme/web/shop/customer/CustomerService_findCustomer.png)
 * </li>
 * </ul>
 * <p>
 * No icon will be displayed when the icon file can not be found.
 * </p>
 * <p>
 * Currently the PNG image format is the only format that is supported. Adding
 * more image types will slow down lookup performance.
 * </p>
 * 
 * @author nilsth
 *
 */
public class IconDefaultModel implements IconModel {

	private URL url;

	public IconDefaultModel(Method actionMethod) {
		url = createURL(actionMethod);
	}

	public IconDefaultModel(Class<?> objectClass) {
		url = createURL(objectClass);
	}

	private URL createURL(Class<?> objectClass) {
		String resourceName = createResourceName(objectClass);
		URL url = objectClass.getResource(resourceName);
		return url;
	}

	private String createResourceName(Class<?> objectClass) {
		StringBuilder resourceName = new StringBuilder();
		resourceName.append(StringUtil.firstCharToLowerCase(objectClass.getSimpleName()));
		resourceName.append(".png");
		return resourceName.toString();

	}

	private URL createURL(Method actionMethod) {
		Class<?> ownerClass = actionMethod.getDeclaringClass();
		String resourceName = createResourceName(actionMethod);
		URL url = ownerClass.getResource(resourceName);
		return url;
	}

	private String createResourceName(Method actionMethod) {
		StringBuilder resourceName = new StringBuilder();
		String className = actionMethod.getDeclaringClass().getSimpleName();
		resourceName.append(StringUtil.firstCharToLowerCase(className));
		resourceName.append("_");
		resourceName.append(actionMethod.getName());
		resourceName.append(".png");
		return resourceName.toString();
	}

	@Override
	public URL getURL(Object obj) {
		return url;
	}

}