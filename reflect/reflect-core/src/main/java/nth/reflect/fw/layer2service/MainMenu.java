package nth.reflect.fw.layer2service;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.FormViewMenu;
import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * TODO PICTURE MAIN MENU
 * <p>
 * The {@link MainMenu} is displayed on a panel on the left hand side of the
 * user interface. Sometimes this panel is hidden but it can always be made
 * visible again using the main tool bar. The {@link MainMenu} provides the user access
 * to the {@link DomainObject}s so that the user can work on them. The
 * {@link MainMenu} contains all {@link ActionMethod}s of {@link ServiceObject}s
 * that either have no method parameter or have an {@link ParameterFactory}.
 * </p>
 * <p>
 * {@link ActionMethod}s of {@link ServiceObject}s that take a
 * {@link DomainObject} as a parameter and have no {@link ParameterFactory} are
 * displayed in {@link FormViewMenu}s and propertyMenus.
 * </p>
 * Each {@link ServiceObject} is always displayed as a sub menu
 * 
 * @author nilsth
 *
 */
public interface MainMenu extends ReflectDocumentationInterface {

}
