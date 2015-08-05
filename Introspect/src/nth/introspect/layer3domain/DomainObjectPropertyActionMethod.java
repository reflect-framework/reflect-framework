package nth.introspect.layer3domain;

import nth.introspect.documentation.Documentation;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

/**
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s that do something with
 * the value of a {@link DomainObjectProperty} (e.g. manipulate it).
 * </p>
 * <p>
 * The name of the ActionMethod for a {@link DomainObjectProperty} must begin
 * with the action name, followed by its property name
 * </p>
 * Examples:
 * <ul>
 * <li>A ShoppingCar object may have an property lineItems that has a
 * {@link DomainObjectPropertyActionMethod}s such as addLineItems(LineItem
 * lineItem) or removeLineItem(LineItem lineItem) or removeAllLineItems().</li>
 * <li>A Customer object may have an property address that has a
 * PropertyActionMethod such as moveToNewAddress(Address new Address)</li>
 * </ul>
 * {@link ActionMethod}s of {@link DomainObject}s are displayed as menu items in
 * the PropertyMenu.
 * 
 * <h3>Domain Object Property Menu</h3> 
 * <p>
 * {@insert DomainObjectPropertyMenu}
 * </p> */
public interface DomainObjectPropertyActionMethod extends Documentation {

}
