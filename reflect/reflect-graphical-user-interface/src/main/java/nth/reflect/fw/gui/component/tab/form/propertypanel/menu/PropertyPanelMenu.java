package nth.reflect.fw.gui.component.tab.form.propertypanel.menu;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer3domain.DomainObjectPropertyActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * TODO PICTURE property context MENU and field table row context menu
 * </p>
 * The {@link PropertyPanelMenu} is displayed as a
 * <a href="https://en.wikipedia.org/wiki/Context_menu">context menu</a> when
 * the menu button (an icon button with 3 vertical dots) of a
 * {@link PropertyPanel} is pressed. It contains all
 * {@link DomainObjectPropertyActionMethod}s and all {@link ActionMethod}s of
 * {@link ServiceObject}s that take the {@link DomainObjectProperty} type as a
 * parameter. Each {@link ServiceObject} is displayed as a sub menu.
 * <p>
 * </p>
 * Note that the menu button is not displayed there are no visible
 * {@link DomainObjectPropertyActionMethod}s
 * </p>
 * 
 * @author nilsth
 *
 */
public interface PropertyPanelMenu extends ReflectDocumentationInterface {

}
