package nth.introspect.documentation;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer4infrastructure.InfrastructureObject;

/**
 * <p>
 * An action is a method in a {@link DomainObject} or {@link ServiceObject} that
 * is displayed by the {@link UserInterfaceController} as a menu item. An
 * {@link ActionMethod} is invoked by the {@link UserInterfaceController} when
 * the user clicks on the menu item.
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
 * <a href="https://en.wikipedia.org/?title=Object-oriented_programming"> Object
 * Orientated Programming</a> favors to put business logic and the validation
 * logic into the {@link DomainObject}s (and sometimes
 * {@link InfrastructureObject}s) as much as possible.
 * {@link ServiceObjectActionMethod}s should therefore not contain business
 * logic or validation logic, but delegate the work to collaborations of
 * {@link DomainObject}s and {@link InfrastructureObject}s, in order to prevent
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
