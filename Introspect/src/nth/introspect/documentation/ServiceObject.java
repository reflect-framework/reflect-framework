package nth.introspect.documentation;

import nth.introspect.controller.userinterface.UserInterfaceController;

/**
 * {@link ServiceObject}s do not need to implement any interface or superclass.<br>
 * <br>
 * {@link ServiceObject}s are placed in the {@link ServiceLayer}<br>
 * {@link ServiceObject}s contain methods that basically represent the main menu
 * items in {@link UserInterfaceController} <br>
 * {@link ServiceObject}s are normally named to the
 * {@link DomainObject}s that they service (e.g. CustomerService).
 * 
 * @author Nils ten Hoeve
 * 
 */
public interface ServiceObject extends Documentation {

}
