package nth.introspect.definition;

import nth.introspect.provider.userinterface.UserInterfaceProvider;
//TODO synchronize with manual
/**
 * This interface serves for documentation purposes only. <br><br>
 * The {@link UserInterfaceLayer} is part if the {@link IntrospectArchitecture}. <br>
 * The  {@link UserInterfaceLayer} is also know as presentation layer (but that wouldn't really do it justice):
 * The {@link UserInterfaceLayer} contains the {@link UserInterfaceProvider}, which is responsible for displaying and controlling the user interface<br>
 *
 * Note that this layer is the top layer, which means it may know all the objects in the lower layers but not visa versa! See {@link IntrospectArchitecture
 * @author Nils ten Hoeve
 * 
 */
public interface UserInterfaceLayer {

}
