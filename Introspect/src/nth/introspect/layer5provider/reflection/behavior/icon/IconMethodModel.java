package nth.introspect.layer5provider.reflection.behavior.icon;

import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer3domain.DomainObject;

/**
 * <p>Instead of the {@link Icon} annotation you can also define the icon with a
 * method recognized by the {@link IntrospectFramework}. This allows you to
 * change the icon dynamically during runtime, based on state (e.g. when the
 * {@link DomainObject} Person is a male or female).</p>
 * <p>
 * Syntax: public string icon&lt;className or actionMethodName&gt;()</p>
 * 
 * The return value must be an iconURI, which can be a:
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
 * TODO EXAMPLE customer male or female icon
 * 
 * @author nilsth
 *
 */
public class IconMethodModel {

}
