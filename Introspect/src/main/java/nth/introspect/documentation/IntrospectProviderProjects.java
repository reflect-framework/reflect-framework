package nth.introspect.documentation;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;

/**
 * <p>
 * {@link Provider} objects can have different implementations. Depending on the
 * type of application you can write your own implementation of a provider or
 * use or extend one of the {@link IntrospectProviderProjects} that come with
 * the framework. In example, depending on your application you might need a
 * different {@link AuthorizationProvider} such as:
 * <ul>
 * <li>hard coded authorization</li>
 * <li>file based authorization</li>
 * <li>database authorization</li>
 * <li>web container authorization (e.g. <a
 * href="https://tomcat.apache.org/tomcat-7.0-doc/realm-howto.html">Apache
 * Tomcat Realm</a>)</li>
 * <li><a
 * href="https://nl.wikipedia.org/wiki/Lightweight_Directory_Access_Protocol"
 * >LDAP</a></li>
 * <li><a href="https://nl.wikipedia.org/wiki/Active_Directory">Active
 * directory</a></li>
 * <li>or other</li>
 * </ul>
 * <p>
 * If you wish to use a non default {@link Provider}, you need to register a
 * {@link Provider} by overwriting the matching {@link IntrospectApplication#get
 * ...ProviderClass()} method.
 * </p>
 * <p>
 * The following sections will describe the optional
 * {@link IntrospectProviderProjects} that come with the
 * {@link IntrospectFramework}.
 * <h2>IntrospectProviderTomcatAuthorization</h2>
 * <p>
 * {@include ...}
 * </p>
 * 
 * @author nilsth
 *
 */
public interface IntrospectProviderProjects extends Documentation {

}
