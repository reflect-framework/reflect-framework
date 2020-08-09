package nth.reflect.fw.vaadin.layer5provider.actionmethod.prehandler.impl;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.vaadin.tab.form.FormTab;

public class EditDomainObject extends nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl.EditDomainObject {

	@Override
	public Tab createEditableFormTab(UserInterfaceContainer container, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter, Object methodResult) {
		return new FormTab(container, methodOwner, actionMethodInfo, methodParameter, methodResult, FormMode.EDIT);
	}

}
