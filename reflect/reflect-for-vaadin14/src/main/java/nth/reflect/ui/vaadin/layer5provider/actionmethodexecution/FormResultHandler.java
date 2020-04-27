package nth.reflect.ui.vaadin.layer5provider.actionmethodexecution;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.ui.vaadin.tab.form.FormTab;

public class FormResultHandler extends nth.reflect.fw.gui.provider.actionmethodexecution.result.FormResultHandler {

	@Override
	public Tab createFormTab(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult, FormMode formMode) {
		return new FormTab(container, methodOwner, methodInfo, methodParameter, methodResult, formMode);
	}

}
