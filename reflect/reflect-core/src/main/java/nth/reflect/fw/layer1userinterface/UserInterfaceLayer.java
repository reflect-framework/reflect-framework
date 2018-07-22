package nth.reflect.fw.layer1userinterface;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.documentation.ReflectArchitecture;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;

/**
 * 
 * The {@link UserInterfaceLayer} is also know as presentation layer (although I
 * think that 'presentation layer' is a poor name, because it is responsible for
 * so much more). The {@link UserInterfaceLayer} contains the
 * {@link UserInterfaceController}, which is responsible for showing information to the user and processing the information from the user using the objects in the lower layers (see {@link ReflectArchitecture}).
 * <p>
 * The {@link UserInterfaceContainer} is an {@link DependencyInjectionContainer} that
 * represents the {@link UserInterfaceLayer} and holds and manages the
 * {@link UserInterfaceController}.
 * </p>
 *
 * Note that this layer is the top layer, which means it may know all the
 * objects in the lower layers but not visa versa! See
 * {@link ReflectArchitecture}
 * 
 * <h2>UserInterfaceController</h2> {@insert UserInterfaceController}
 * 
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface UserInterfaceLayer extends ReflectDocumentationInterface {
}
