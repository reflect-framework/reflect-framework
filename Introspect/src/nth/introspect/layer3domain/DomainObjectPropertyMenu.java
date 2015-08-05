package nth.introspect.layer3domain;

import nth.introspect.documentation.Documentation;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
/**
 * TODO PICTURE property context MENU and field table row
 * context menu
 * <p>
 * The {@link DomainObjectPropertyMenu} is displayed as a <a
 * href="https://en.wikipedia.org/wiki/Context_menu">context menu</a> in a
 * formview when the menu button of a referenceProperty or a row of a
 * collectionproperty is clicked. The propertyMenu allows a user to manipulate a
 * {@link DomainObjectProperty}. It contains all
 * {@link DomainObjectPropertyActionMethod}s of and all {@link ActionMethod}s of
 * {@link ServiceObject}s that take the {@link DomainObject} as a parameter.
 * Each {@link ServiceObject} is displayed as a sub menu.
 * </p>

 * @author nilsth
 *
 */
public interface DomainObjectPropertyMenu extends Documentation {

}
