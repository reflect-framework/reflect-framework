package nth.reflect.fw.gui.component.tab.grid;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.ReflectGuiComponent;
import nth.reflect.fw.gui.component.tab.ActionMethodTab;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.table.info.TableInfo;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * A {@link GridTab} is a {@link ReflectGuiComponent}. It shows the user a table
 * or grid of values which could be {@link DomainObject}. These values (an
 * array, list, set or queue) is the result of a {@link ActionMethod}.
 * {@link GridTab}s are normally used to select e {@link DomainObject}, so that
 * it can be viewed or manipulated with a {@link FormTab}
 * </p>
 * <h3>Grid Tab Menu</h3> {@insert GridTabMenu}
 * </p>
 * 
 * @author nilsth
 *
 */
public interface GridTab extends ActionMethodTab, ReflectGuiComponent {

	public ReadOnlyValueModel getSelectedRowModel();

	public UserInterfaceContainer getUserInterfaceContainer();

}
