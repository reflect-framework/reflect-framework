package nth.reflect.fw.gui.component.tab.form;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.ReflectGuiComponent;
import nth.reflect.fw.gui.component.tab.ActionMethodTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * A {@link FormTab} is a {@link ReflectGuiComponent}. It shows the user a
 * {@link DomainObject} with {@link PropertyPanel}s. This {@link DomainObject}
 * is either a {@link ActionMethod} result (read-only) or a {@link ActionMethod}
 * parameter (can be edited by a user depending on the {@link ExecutionMode}
 * annotation of the {@link ActionMethod}.
 * 
 * <h3>Form Tab Menu</h3> {@insert FormTabmenu}
 * 
 * <h3>Property Panels</h3>
 * {@insert nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyPanel}
 * 
 * 
 * 
 * @author nilsth
 *
 */
public interface FormTab extends ActionMethodTab {

	public ReadOnlyValueModel getDomainValueModel();

	public FormMode getFormMode();

	public Object getDomainObject();

	public UserInterfaceContainer getUserInterfaceContainer();

}
