package nth.introspect.layer5provider.reflection.behavior.icon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
/**
 * <p>
 * If you do not want to use the default icon, you can define an alternative
 * icon by placing an Icon annotation before the “class” key word or before the
 * {@link ActionMethod}.
 * </p>
 * 
 * <p>
 * Syntax: {@link Icon}(String iconURI)
 * </p>
 * <p>
 * Parameter iconURI: image file to be used as an icon
 * </p>
 * The iconURI can be a:
 * <ul>
 * <li>
 * &lt;fileName&gt; e.g. Cut.jpg that will be located in the
 * &lt;imageFolder&gt;/Cut.jpg</li>
 * <li>&lt;relativePath&gt;/&lt;fileName&gt; e.g. /edit/Cut.jpg that will be
 * located in the &lt;imageFolder&gt;/edit/Cut.jpg</li>
 * <li>&lt;absolutePath&gt;/&lt;fileName&gt; e.g.
 * file://c:/MyProject/images/Cut.jpg, or you can use the IntrospectIconUri
 * class to give you an absolute URI to one of the standard icons.</li>
 * </ul>
 * 
 * 
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Icon {
	public String iconURI();
}
