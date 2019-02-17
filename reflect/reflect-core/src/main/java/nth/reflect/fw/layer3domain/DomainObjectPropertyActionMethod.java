package nth.reflect.fw.layer3domain;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;

/**
 * <h3>PropertyActionMethod annotation</h3> {@insert PropertyActionMethod}
 * 
 * 
 * <h3>ReadOnlyActionMethod annotation</h3> {@insert ReadOnlyActionMethod}
 * 
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
 * 
 * <h3>Domain Object Property Menu</h3> {@insert DomainObjectPropertyMenu}
 */
public interface DomainObjectPropertyActionMethod extends ReflectDocumentationInterface {

}
