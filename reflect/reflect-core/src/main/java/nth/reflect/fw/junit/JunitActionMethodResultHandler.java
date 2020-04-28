package nth.reflect.fw.junit;

import java.util.List;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class JunitActionMethodResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo actionMethodInfo) {
		return true;
	}

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameter, Object methodResult) {
		UserInterfaceControllerForJUnit userInterface = container.get(UserInterfaceControllerForJUnit.class);
		List<String> events = userInterface.getEvents();
		events
				.add(String
						.format("%s.process(%s, %s, %s", JunitActionMethodResultHandler.class.getSimpleName(),
								methodOwner, actionMethodInfo, methodParameter, methodResult));

	}

}
