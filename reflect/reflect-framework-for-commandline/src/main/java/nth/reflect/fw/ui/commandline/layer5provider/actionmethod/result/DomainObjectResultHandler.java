package nth.reflect.fw.ui.commandline.layer5provider.actionmethod.result;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.commandline.view.FormView;

/**
 * Shows the {@link DomainObject} as a form
 * 
 * @author nilsth
 *
 */
public class DomainObjectResultHandler
		extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.DomainObjectResultHandler {

	@Override
	public void process(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, Object methodResult) {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		FormView formView = new FormView(reflectionProvider, methodInfo, methodResult);
		System.out.println(formView.toString());
	}
}
