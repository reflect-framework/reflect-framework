package nth.introspect.documentation;

import nth.introspect.layer4infrastructure.InfrastructureObject;

/**
 * <p>
 * {@link InfrastructureObject}s often need information on how they need to do
 * their work (i.e. how to connect to a database). The
 * {@link IntrospectFramework} prefers to configure programmatically (Read the
 * <a href=
 * "http://www.martinfowler.com/articles/injection.html#CodeOrConfigurationFiles"
 * >“Code or configuration files”</a> section in Martin Fowlers article for the
 * arguments why), by means of a {@link ConfigurationObject}.
 * </p>
 * <p>
 * {@link ConfigurationObject}s:
 * <ul>
 * <li>Objects that need a {@link ConfigurationObject} have a constructor that
 * takes the {@link ConfigurationObject} as a constructor parameter. The idea
 * behind this is to create valid objects at construction time where possible.
 * Constructors with parameters give you a clear statement of what it means to
 * create a valid object in an obvious place.</li>
 * <li>ConfigurationObjects often have a <a
 * href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>.</li>
 * </ul>
 * </p>
 * 
 * @author nilsth
 *
 */
public interface ConfigurationObject extends Documentation {

}
