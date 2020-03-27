package nth.reflect.fw.layer3domain;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * {@link DomainObject}s may have {@link ActionMethod}s that do something with
 * the value of a {@link DomainObjectProperty}, e.g. modify it or display it.
 * These {@link DomainObjectPropertyActionMethod}'s are displayed in a
 * {@link PropertyPanelMenu}
 * </p>
 * <p>
 * The name of the {@link ActionMethod} for a {@link DomainObjectProperty}
 * always begins with the {@link DomainObjectProperty}name, followed by the
 * {@link ActionMethod}name.
 * </p>
 * <p>
 * Example: A WebShop object may have an property products that has a
 * {@link DomainObjectPropertyActionMethod}s such as:
 * <ul>
 * <li>productsAdd(Product product)</li>
 * <li>productsRemove(Product product)</li>
 * <li>productsFind(String query)</li>
 * </ul>
 * </p>
 */
public interface DomainObjectPropertyActionMethod extends ReflectDocumentationInterface {

}
