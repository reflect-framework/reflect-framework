package nth.reflect.fw.ui.component.tab.table;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.ui.component.ReflectGuiComponent;
import nth.reflect.fw.ui.component.tab.ActionMethodTab;
import nth.reflect.fw.ui.component.tab.form.FormTab;

/**
 * A {@link TableTab} is a {@link ReflectGuiComponent}. It shows the user a
 * table or grid of values which could be {@link DomainObject}. These values (an
 * array, list, set or queue) is the result of a {@link ActionMethod}.
 * {@link TableTab}s are normally used to select e {@link DomainObject}, so that
 * it can be viewed or manipulated with a {@link FormTab}
 * 
 * @author nilsth
 *
 */
public interface TableTab extends ActionMethodTab {

	public ReadOnlyValueModel getSelectedRowModel();

	public ReadOnlyValueModel getAllRowsModel();

	public UserInterfaceContainer getUserInterfaceContainer();

}
