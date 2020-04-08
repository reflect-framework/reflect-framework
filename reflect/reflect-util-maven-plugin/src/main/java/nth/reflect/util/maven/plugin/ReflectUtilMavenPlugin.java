package nth.reflect.util.maven.plugin;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.documentation.ReflectDocumentation;
import nth.reflect.util.maven.plugin.language.files.UpdateLanguageFiles;

/**
 * {@link ReflectUtilMavenPlugin} can be used to execute goals for a
 * {@link ReflectApplication}.
 * 
 * <h3>Adding a {@link ReflectUtilMavenPlugin} to a
 * {@link ReflectApplication}</h3> Add the following XML tags to the pom.xml
 * file:
 * 
 * <pre>
 * &lt;build&gt;
 * 	&lt;plugins&gt;
 * 		&lt;plugin&gt;
 * 			&lt;groupId&gt;com.github.reflect-framework&lt;/groupId&gt;
 * 			&lt;version&gt;0.0.2-SNAPSHOT&lt;/version&gt;
 * 			&lt;artifactId&gt;reflect-util-maven-plugin&lt;/artifactId&gt;
 *		&lt;/plugin&gt;
 *	&lt;/plugins&gt;
 * &lt;/build&gt;}
 * </pre>
 * 
 * <h3>Executing a {@link ReflectUtilMavenPlugin} goal</h3>
 * <ul>
 * <li>In Eclipse you can execute goals by right clicking the project, selecting
 * Debug as, Maven build... and entering the goal.<br>
 * e.g.:
 * com.github.reflect-framework:reflect-util-maven-plugin:{@link UpdateLanguageFiles}</li>
 * <li>You can also open a command console, go to the project folder and enter
 * mvn followed by the goal.<br>
 * e.g.:mvn
 * com.github.reflect-framework:reflect-util-maven-plugin:{@link UpdateLanguageFiles}
 * </li>
 * </ul>
 * <h3>{@link UpdateLanguageFiles} goal</h3> {@insert UpdateLanguageFiles}
 * 
 * 
 * @author nilsth
 *
 */

public interface ReflectUtilMavenPlugin extends ReflectDocumentation {

}
