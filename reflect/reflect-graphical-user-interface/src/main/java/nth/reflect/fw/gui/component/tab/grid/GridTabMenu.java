package nth.reflect.fw.gui.component.tab.grid;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * TODO PICTURE {@link GridTabMenu}
 * <p>
 * A {@link GridTabMenu} is displayed as a
 * <a href="https://en.wikipedia.org/wiki/Context_menu">context menu</a> when a
 * user clicks on one of the rows of the grid or table. It contains all the
 * {@link ActionMethod}s of the {@link DomainObject} and all
 * {@link ActionMethod} s of {@link ServiceObject}s that take the
 * {@link DomainObject} as a parameter. Each {@link ServiceObject} is displayed
 * as a sub menu.
 * </p>
 * 
 * @author nilsth
 *
 */

public interface GridTabMenu extends ReflectDocumentationInterface {
}
