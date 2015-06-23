package nth.introspect.documentation;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;

/**
 * <p>
 * The {@link ServiceLayer} (sometimes also called <a
 * href="application layer">application layer</a>) defines what the software is
 * supposed to do. It is called upon by the {@link UserInterfaceController} and
 * redirects the {@link DomainObject}s and {@link InfrastructureObject}s to work
 * out the complicated stuff.
 * </p>
 * <p>
 * The {@link ServiceContainer} is an {@link IntrospectContainer} that
 * represents the {@link ServiceLayer} and holds and manages
 * {@link ServiceObject}s.
 * </p>
 * <p>
 * Note that the {@link ServiceLayer} is a middle layer(see
 * {@link IntrospectArchitecture}):
 * <ul>
 * <li>
 * The {@link ServiceObject}s have NO references to objects in the
 * {@link UserInterfaceLayer}</li>
 * <li>The {@link ServiceObject}s may have references to the objects in the
 * lower {@link DomainLayer}, {@link InfrastructureLayer} and {@link ProviderLayer}, but not visa versa!</li>
 * </ul>
 * </p>
 * <h2>Service Objects</h2> {@insert ServiceObject}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface ServiceLayer extends Documentation {

}
