package nth.introspect.documentation;

import nth.introspect.controller.userinterface.UserInterfaceController;

/**
 * <p>
 * An action is a method in a {@link DomainObject} or {@link ServiceObject} is
 * displayed by the {@link UserInterfaceController} as a menu item. An Action
 * method is invoked by the {@link UserInterfaceController} when the user clicks
 * on the menu item.
 * </p>
 * <h3>Action Methods for Domain Objects</h3>
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s that to do something with
 * or for the given {@link DomainObject}.
 * </p>
 * <ul>
 * <li>These {@link ActionMethod}s are displayed as menu options in a form tab
 * that represents the {@link DomainObject}.</li>
 * <li>Example: an ShoppingCar object may have an {@link ActionMethod} such as
 * checkout().</li>
 * </ul>
 * <h3>Action Methods for Domain Object Properties</h3>
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s to do something with the
 * value of a property (e.g. manipulate it).
 * <ul>
 * <li>These {@link ActionMethod}s are displayed as menu options of a
 * {@link DomainObjectProperty} in a form tab that represents the
 * {@link DomainObject}.</li>
 * <li>The name of the {@link ActionMethod} for a {@link DomainObjectProperty}
 * must begin with the name of the property, followed with a description of what
 * is does.</li>
 * </ul>
 * Examples:
 * <ul>
 * <li>A ShoppingCar object may have {@link ActionMethod} such as
 * lineItemsAdd(LineItem lineItem) or lineItemsRemove(LineItem lineItem) or
 * lineItemsRemoveAll().</li>
 * <li>A Customer object may have {@link ActionMethod} such as
 * adressMoveToNewAddress(Address new Address).</li>
 * </ul>
 * </p>
 * {@insert ActionMethod}
 */
public interface DomainObjectActionMethod extends Documentation {

}
