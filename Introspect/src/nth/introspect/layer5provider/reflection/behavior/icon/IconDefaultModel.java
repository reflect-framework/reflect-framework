package nth.introspect.layer5provider.reflection.behavior.icon;

import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

/**
 * The {@link IntrospectFramework} will try to find an image with the same
 * {@link Class}Name or {@link ActionMethod}Name but with the “.png” extension
 * in the same package.
 * <ul>
 * <li>Filename signature for object icon: &lt;package&gt;/&lt;className&gt;.png (e.g.
 * c:/MyProject/bin/com/acme/web/shop/customer/CustomerService.png)</li>
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
public class IconDefaultModel {

}
