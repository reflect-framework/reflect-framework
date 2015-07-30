package nth.introspect.layer2service;

import nth.introspect.documentation.Documentation;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectMenu;
import nth.introspect.layer3domain.DomainObjectPropertyMenu;
import nth.introspect.layer4infrastructure.InfrastructureObject;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

/**
 * <p>
 * {@link ServiceObject}s exist because they always have one ore more
 * {@link ActionMethod}s. ActionMethods of serviceObjects can appear as menu
 * items in the {@link MainMenu}, {@link DomainObjectMenu}, or
 * {@link DomainObjectPropertyMenu}</li>
 * </p>
 * <p>
 * In Example: a CustomerService object may have an {@link ActionMethod} such as
 * findCustomer(CustomerSearchArgument searchArgument) that is displayed in the
 * {@link MainMenu}.
 * </p>
 * <h3>Main menu</h3> {@insert MainMenu}
 */
public interface ServiceObjectActionMethod extends Documentation {

}
