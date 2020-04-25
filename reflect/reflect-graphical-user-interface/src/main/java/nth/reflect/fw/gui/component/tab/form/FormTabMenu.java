package nth.reflect.fw.gui.component.tab.form;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.gui.component.tab.table.TableTab;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * TODO PICTURE Domain object MENU
 * <p>
 * The {@link FormTabMenu} is displayed on the
 * <a href="https://en.wikipedia.org/wiki/Toolbar">toolbar</a> of a
 * {@link FormTab} (that displays a {@link DomainObject}) or as a
 * <a href="">context menu</a> when the user clicks on a row of a
 * {@link TableTab}. The {@link FormTabMenu} allows a user to perform an action
 * on or with a {@link DomainObject}. An {@link FormTabMenu} contains all the
 * {@link ActionMethod}s of the {@link DomainObject} and all
 * {@link ActionMethod} s of {@link ServiceObject}s that take the
 * {@link DomainObject} as a parameter. Each {@link ServiceObject} is displayed
 * as a sub menu.
 * </p>
 * 
 * @author nilsth
 *
 */
public interface FormTabMenu extends ReflectDocumentationInterface {

}
