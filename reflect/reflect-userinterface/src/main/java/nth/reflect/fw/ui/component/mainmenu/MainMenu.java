package nth.reflect.fw.ui.component.mainmenu;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.FormMenu;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.ui.component.ReflectGuiComponent;
import nth.reflect.fw.ui.component.applicationbar.ApplicationBar;

/**
 * TODO PICTURE MAIN MENU
 * <p>
 * The {@link MainMenu} is displayed on a panel on the left hand side of the
 * user interface. Sometimes this panel is hidden but it can always be made
 * visible again using the menu icon on the {@link ApplicationBar}. The
 * {@link MainMenu} provides the user access to the {@link DomainObject}s so
 * that the user can work on them. The {@link MainMenu} contains all
 * {@link ActionMethod}s of {@link ServiceObject}s that either have no method
 * parameter or have an {@link ParameterFactory}.
 * </p>
 * <p>
 * {@link ActionMethod}s of {@link ServiceObject}s that take a
 * {@link DomainObject} as a parameter and have no {@link ParameterFactory} are
 * displayed in {@link FormMenu}s and propertyMenus.
 * </p>
 * Each {@link ServiceObject} is always displayed as a sub menu
 * 
 * @author nilsth
 *
 */
public interface MainMenu extends ReflectDocumentationInterface, ReflectGuiComponent {

}
