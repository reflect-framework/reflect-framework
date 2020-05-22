package nth.reflect.fw.gui.layer5provider.actionmethod.result;

import java.util.Optional;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.FormTabFilter;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * Shows the return value from an {@link ActionMethod} in a read only
 * {@link FormTab}, if the return value type is a {@link DomainObject}
 * 
 * @author nilsth
 *
 */
public abstract class DomainObjectResultHandler
		extends nth.reflect.fw.layer5provider.actionmethod.result.handler.DomainObjectResultHandler {

	private static final FormMode READ_ONLY = FormMode.READ_ONLY;

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameter, Object methodResult) {
		GraphicalUserinterfaceController userinterface = container.get(GraphicalUserinterfaceController.class);

		Tabs tabs = userinterface.getTabs();

		FormTabFilter formTabFilter = new FormTabFilter(methodOwner, actionMethodInfo, methodParameter, methodResult,
				READ_ONLY);
		Optional<FormTab> result = tabs.stream().filter(formTabFilter).findFirst();

		if (result.isPresent()) {
			tabs.setSelected(result.get());
		} else {
			Tab formTab = createFormTab(container, methodOwner, actionMethodInfo, methodParameter, methodResult,
					READ_ONLY);
			tabs.setSelected(formTab);
		}
	}

	/**
	 * NOTE that the FormOkItem linked to the OK button of the FormTab will need to
	 * call {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)};
	 * 
	 * @param methodOwner
	 */
	public abstract Tab createFormTab(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult, FormMode formMode);
}
