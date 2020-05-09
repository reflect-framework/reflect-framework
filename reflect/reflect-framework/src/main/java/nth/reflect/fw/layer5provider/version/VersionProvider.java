package nth.reflect.fw.layer5provider.version;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.Provider;

/**
 * The {@link VersionProvider} provides the following information on all objects
 * used in an {@link ReflectApplication}:
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
public interface VersionProvider extends Provider {

	ApplicationVersionInfo getApplicationVersionInfo();
}