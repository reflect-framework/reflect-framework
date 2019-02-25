package nth.reflect.fw.layer3domain;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.PropertyActionMethod;

/**
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s that do something with
 * the value of a {@link DomainObjectProperty}, e.g. modify it or display it or
 * open it in a browser. In this case the we need to annotate it with
 * the @{@link PropertyActionMethod} annotation to indicated that the
 * {@link ActionMethod} is an {@link DomainObjectPropertyActionMethod} and needs
 * to be displayed in the {@link PropertyPanelMenu}. You therefore must specify
 * the {@link DomainObjectProperty} name in the @{@link PropertyActionMethod}
 * annotation.
 * </p>
 * <p>
 * Examples:
 * <ul>
 * <li>A ShoppingCar object may have an property lineItems that has a
 * {@link DomainObjectPropertyActionMethod}s such as addLineItem(LineItem
 * lineItem) or removeLineItem(LineItem lineItem) or removeAllLineItems().</li>
 * <li>A Customer object may have an property address that has a
 * PropertyActionMethod such as moveToNewAddress(Address new Address)</li>
 * </ul>
 * </p>
 */
public interface DomainObjectPropertyActionMethod extends ReflectDocumentationInterface {

}
