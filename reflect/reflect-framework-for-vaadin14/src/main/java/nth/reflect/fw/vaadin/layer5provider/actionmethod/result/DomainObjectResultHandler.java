package nth.reflect.fw.vaadin.layer5provider.actionmethod.result;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.vaadin.tab.form.FormTab;

public class DomainObjectResultHandler extends nth.reflect.fw.gui.layer5provider.actionmethod.result.DomainObjectResultHandler {

	@Override
	public Tab createFormTab(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult, FormMode formMode) {
		return new FormTab(container, methodOwner, methodInfo, methodParameter, methodResult, formMode);
	}

}
