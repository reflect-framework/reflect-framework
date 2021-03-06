package nth.reflect.fw.gui.component.tab.table;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.ReflectGuiComponent;
import nth.reflect.fw.gui.component.tab.ActionMethodTab;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * A {@link TableTab} is a {@link ReflectGuiComponent}. It shows the user a
 * table or grid of values which could be {@link DomainObject}. These values (an
 * array, list, set or queue) is the result of a {@link ActionMethod}.
 * {@link TableTab}s are normally used to select e {@link DomainObject}, so that
 * it can be viewed or manipulated with a {@link FormTab}
 * <p>
 * <h3>Table Tab Menu</h3>
 * <p>
 * {@insert TableTabMenu}
 * 
 * @author nilsth
 *
 */
public interface TableTab extends ActionMethodTab, ReflectGuiComponent {

	public ReadOnlyValueModel getSelectedRowModel();

	@Override
	public UserInterfaceContainer getUserInterfaceContainer();

}
