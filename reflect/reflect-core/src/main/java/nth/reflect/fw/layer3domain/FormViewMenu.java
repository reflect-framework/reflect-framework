package nth.reflect.fw.layer3domain;

import javax.swing.text.html.FormView;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * TODO PICTURE Domain object MENU and table row context menu
 * <p>
 * {@link ActionMethod}s of {@link DomainObject}s are displayed as menu items in
 * the {@link FormViewMenu}.
 * </p>
 * <p>
 * The {@link FormViewMenu} is displayed on the <a
 * href="https://en.wikipedia.org/wiki/Toolbar">toolbar</a> of a
 * {@link FormView} (that displays a {@link DomainObject}) or as a <a
 * href="">context menu</a> when the user clicks on a row of a table view. The
 * {@link FormViewMenu} allows a user to perform an action on or with a
 * {@link DomainObject}. An {@link FormViewMenu} contains all the
 * {@link ActionMethod}s of the {@link DomainObject} and all
 * {@link ActionMethod} s of {@link ServiceObject}s that take the
 * {@link DomainObject} as a parameter. Each {@link ServiceObject} is displayed
 * as a sub menu.
 * </p>
 * 
 * @author nilsth
 *
 */
public interface FormViewMenu extends ReflectDocumentationInterface {

}
