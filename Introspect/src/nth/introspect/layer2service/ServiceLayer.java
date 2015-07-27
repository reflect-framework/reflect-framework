package nth.introspect.layer2service;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.documentation.Documentation;
import nth.introspect.documentation.IntrospectArchitecture;
import nth.introspect.layer1userinterface.UserInterfaceLayer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainLayer;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureLayer;
import nth.introspect.layer5provider.ProviderLayer;

/**
 * <p>
 * The {@link ServiceLayer} (sometimes also called <a
 * href="application layer">application layer</a>) gives the user access to the
 * {@link DomainObject}s so that the user can work on them.
 * </p>
 * <p>The {@link ServiceContainer} is an {@link IntrospectContainer} that
 * represents the {@link ServiceLayer} and holds and manages
 * {@link ServiceObject}s. </p>
 * <p>
 * Note that the {@link ServiceLayer} is a middle layer(see
 * {@link IntrospectArchitecture}):
 * <ul>
 * <li>
 * The {@link ServiceObject}s have NO references to objects in the
 * {@link UserInterfaceLayer}</li>
 * <li>The {@link ServiceObject}s may have references to the objects in the
 * lower {@link DomainLayer}, {@link InfrastructureLayer} and
 * {@link ProviderLayer}, but not visa versa!</li>
 * </ul>
 * </p>
 * <h2>Service Objects</h2> {@insert ServiceObject}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface ServiceLayer extends Documentation {

}
