package nth.reflect.fw.ui.commandline.provider.actionmethodexecution.result;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.commandline.view.FormView;

/**
 * Shows the {@link DomainObject} as a form
 * 
 * @author nilsth
 *
 */
public class DomainObjectResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ActionMethodInfo actionMethodInfo) {
		boolean isDomainClass = actionMethodInfo.getReturnTypeInfo().isDomainClass();
		return isDomainClass;
	}

	@Override
	public void process(UserInterfaceController userInterfaceController, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter, Object methodResult) {
		UserInterfaceContainer container = userInterfaceController.getUserInterfaceContainer();
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		FormView formView = new FormView(reflectionProvider, actionMethodInfo, methodResult);
		System.out.println(formView.toString());
	}
}
