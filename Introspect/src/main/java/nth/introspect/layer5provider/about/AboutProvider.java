package nth.introspect.layer5provider.about;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer5provider.Provider;

/**
 * The {@link AboutProvider} provides the following information on all objects
 * used in an {@link IntrospectApplication}:
 * <ul>
 * <li>The class name</li>
 * <li>The package name (jar,war,etc...)</li>
 * <li>The version of the jar file (from the meta info file)</li>
 * <li>The authors of the jar file (from the meta info file)</li>
 * </ul>
 * This about information can be displayed on a graphical user interface.
 * 
 * @author nilsth
 *
 */
public interface AboutProvider extends Provider {

	About about();
}