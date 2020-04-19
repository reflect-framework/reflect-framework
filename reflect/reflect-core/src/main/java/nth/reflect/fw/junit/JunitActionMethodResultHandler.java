package nth.reflect.fw.junit;

import java.util.List;

import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class JunitActionMethodResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ActionMethodInfo actionMethodInfo) {
		return true;
	}

	@Override
	public void process(UserInterfaceController userInterfaceController, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter, Object methodResult) {
		UserInterfaceControllerForJUnit userInterfaceControllerForJUnit = (UserInterfaceControllerForJUnit) userInterfaceController;
		List<String> events = userInterfaceControllerForJUnit.getEvents();
		events
				.add(String
						.format("%s.process(%s, %s, %s", JunitActionMethodResultHandler.class.getSimpleName(),
								methodOwner, actionMethodInfo, methodParameter, methodResult));

	}

}
