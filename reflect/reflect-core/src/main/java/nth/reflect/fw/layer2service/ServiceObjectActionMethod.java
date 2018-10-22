package nth.reflect.fw.layer2service;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer3domain.FormMenu;
import nth.reflect.fw.layer3domain.DomainObjectPropertyMenu;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * {@link ServiceObject}s exist because they always have one ore more
 * {@link ActionMethod}s. ActionMethods of serviceObjects can appear as menu
 * items in the {@link MainMenu}, {@link FormMenu}, or
 * {@link DomainObjectPropertyMenu}</li>
 * </p>
 * <p>
 * In Example: a CustomerService object may have an {@link ActionMethod} such as
 * findCustomer(CustomerSearchArgument searchArgument) that is displayed in the
 * {@link MainMenu}.
 * </p>
 * <h3>Main menu</h3> {@insert MainMenu}
 */
public interface ServiceObjectActionMethod extends ReflectDocumentationInterface {

}
