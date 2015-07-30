package nth.introspect.layer5provider.path;

import java.net.URI;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.Provider;

/**
 * <p>
 * The PathProvider provides the locations of files that are needed within an
 * IntrospectApplication.
 * </p>
 * <p>
 * The actual file location depends on the type of introspectApplication used.
 * </p>
 * <ul>
 * <li>TODO explain interface</li>
 * <li>TODO explain the different file locations for swing, android, vaadin</li>
 * </ul>
 * The {@link PathProvider} is used by the user interfaceController and can also
 * be used in other objects: TODO Code example file path to database in
 * {@link InfrastructureObject}
 * 
 * <p>
 * You can provide your own implementation of the {@link PathProvider}. You can register
 * your customizer {@link PathProvider} implementation by overriding the
 * {@link IntrospectApplication#getPathProviderClass()} method.
 * </p>
 * 
 * @author nilsth
 *
 */
public interface PathProvider extends Provider {

	URI getRootPath();

	URI getConfigPath();

	URI getConfigPath(String relativePath);

	URI getDocumenPath();

	URI getDocumentPath(String relativePath);

	URI getImagePath();

	URI getImagePath(CharSequence identifier);

	boolean uriExists(URI uri);

}
