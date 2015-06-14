package nth.introspect.documentation;

import nth.introspect.container.IntrospectContainer;
import nth.introspect.container.impl.ServiceContainer;
import nth.introspect.controller.userinterface.UserInterfaceController;

/**
 * <p>
 * The {@link ServiceLayer} (sometimes also called application layer) defines
 * what the software is supposed to do. It is called upon by the
 * {@link UserInterfaceController} and redirects the {@link DomainObject}s and
 * {@link InfrastructureObject}s to work out the complicated stuff.
 * </p>
 * {@link ServiceContainer} is an {@link IntrospectContainer} that represents
 * the {@link ServiceLayer} and holds and manages {@link ServiceObject}s.<br>
 * </p>
 * <p>
 * Note that this layer is a middle layer: the {@link ServiceObject}s have no
 * knowledge of the {@link UserInterfaceLayer}, the {@link ServiceObject}s know
 * the objects in the lower layers but not visa versa!
 * </p>
 * <h2>Service Objects</h2> {@insert ServiceObject}
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface ServiceLayer extends Documentation {

}
