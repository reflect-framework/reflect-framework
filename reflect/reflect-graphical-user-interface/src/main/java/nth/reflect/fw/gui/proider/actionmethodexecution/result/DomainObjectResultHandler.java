package nth.reflect.fw.gui.proider.actionmethodexecution.result;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class DomainObjectResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ActionMethodInfo actionMethodInfo) {
		boolean returntTypeIsDomainClass = actionMethodInfo.getReturnTypeInfo().isDomainClass();
		return returntTypeIsDomainClass;
	}

	@Override
	public void process(UserInterfaceController userInterfaceController, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter, Object methodResult) {
		GraphicalUserinterfaceController userinterface = (GraphicalUserinterfaceController) userInterfaceController;
		userinterface.showFormTab(methodOwner, actionMethodInfo, methodParameter, methodResult, FormMode.READ_ONLY);
	}

}
