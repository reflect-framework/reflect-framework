package nth.introspect.layer4infrastructure;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.documentation.Documentation;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.layer1userinterface.UserInterfaceLayer;
import nth.introspect.layer2service.ServiceContainer;
import nth.introspect.layer2service.ServiceLayer;
import nth.introspect.layer3domain.DomainLayer;
import nth.introspect.layer5provider.ProviderLayer;

/**
 * <p>
 * The {@link InfrastructureLayer} contains {@link InfrastructureObject}'s that
 * provide generic technical capabilities to support the higher layers.
 * </p>
 * <p> The {@link InfrastructureLayer} is also know as:
 * <ul>
 * <li><a href="http://en.wikipedia.org/wiki/Data_access_layer">Data access
 * layer</a></li>
 * <li><a href="http://en.wikipedia.org/wiki/Persistence_layer">Persistence
 * layer</a></li>
 * <li>Logging Layer</a></li>
 * <li>Networking Layer</li>
 * <li>And other services which are required to support the {@link ServiceLayer}
 * or {@link DomainLayer}</li>
 * </ul>
 * </p>
 * <p>
 * The {@link InfrastructureContainer} is an {@link IntrospectContainer} that
 * represents the {@link InfrastructureLayer} and holds and manages
 * {@link InfrastructureObject}s.<br>
 * </p>

 * Note that the {@link InfrastructureLayer} is a middle layer (see
 * {@link IntrospectArchitecture}):
 * <ul>
 * <li>
 * The {@link InfrastructureObject}s have NO references to objects in the upper
 * {@link UserInterfaceLayer}, {@link ServiceLayer} nor {@link DomainLayer}</li>
 * <li>The {@link InfrastructureObject}s may have references to the objects in
 * the lower {@link ProviderLayer}, but not visa versa!</li>
 * </ul>
 * <h2>Infrastructure Objects</h2> {@insert InfrastructureObject}
 *
 */
public interface InfrastructureLayer extends Documentation {

}
