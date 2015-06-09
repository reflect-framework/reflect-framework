package nth.introspect.documentation;

import nth.introspect.controller.userinterface.UserInterfaceController;

/**
 * <p>
 * An action is a method in a {@link DomainObject} or {@link ServiceObject} is
 * displayed by the {@link UserInterfaceController} as a menu item. An Action
 * method is invoked by the {@link UserInterfaceController} when the user clicks
 * on the menu item.
 * </p>
 * <h3>Action Methods for Service Objects</h3>
 * <p>
 * {@link ServiceObject}s always have one ore more {@link ActionMethod}s.
 * </p>
 * <ul>
 * <li>These {@link ActionMethod}s are displayed by the {@link UserInterfaceController} as menu options in the main menu or in a form tab
 * that represents the {@link DomainObject}.</li>
 * <li>Examples: an CustomerService object may have an {@link ActionMethod} such as
 * findCustomer(CustomerSearchArgument searchArgument).</li>
 * </ul>
 * </p>
 * <p>
 * Note that business logic should be placed in {@link DomainObject}s and therefore have no place in {@link ActionMethod}s in service objects! {@link ActionMethod}s in service objects should only give access to  
 * </p>
 * 
 * {@insert ActionMethod}
*/
public interface ServiceObjectActionMethod extends Documentation {

}
