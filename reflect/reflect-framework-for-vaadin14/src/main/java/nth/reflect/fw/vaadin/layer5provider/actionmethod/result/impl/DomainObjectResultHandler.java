package nth.reflect.fw.vaadin.layer5provider.actionmethod.result.impl;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.vaadin.tab.form.FormTab;

public class DomainObjectResultHandler
		extends nth.reflect.fw.gui.layer5provider.actionmethod.result.impl.DomainObjectResultHandler {

	@Override
	public Tab createReadOnlyFormTab(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		return new FormTab(container, methodOwner, methodInfo, methodParameter, methodResult, FormMode.READ_ONLY);
	}

}
