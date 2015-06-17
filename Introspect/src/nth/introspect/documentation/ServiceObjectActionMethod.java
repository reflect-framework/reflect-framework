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
 * <li>These {@link ActionMethod}s are displayed by the
 * {@link UserInterfaceController} as menu options in the main menu or in a form
 * tab that represents the {@link DomainObject}.</li>
 * <li>Examples: an CustomerService object may have an {@link ActionMethod} such
 * as findCustomer(CustomerSearchArgument searchArgument).</li>
 * </ul>
 * </p>
 * <p>
 * <a
 * href="https://en.wikipedia.org/?title=Object-oriented_programming"> Object
 * Orientated Programming</a> favors to put business logic and the validation
 * logic into the {@link DomainObject}s (and sometimes
 * {@link InfrastructureObject}s) as much as possible.
 * {@link ServiceObjectActionMethod}s should therefore not contain business
 * logic or validation logic, but delegate the work to collaborations of
 * {@link DomainObject}s and {@link InfrastructureObject}, in order to prevent
 * the <a href="http://martinfowler.com/bliki/AnemicDomainModel.html">Anemic
 * Domain Model</a> - <a
 * href="http://en.wikipedia.org/wiki/Anti-pattern">anti-pattern</a>.
 * </p>
 * 
 * 
 * 
 * {@insert ActionMethod}
 */
public interface ServiceObjectActionMethod extends Documentation {

}
