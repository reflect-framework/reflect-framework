package nth.reflect.fw.junit;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.result.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class JunitActionMethodResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo actionMethodInfo) {
		return true;
	}

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameter, Object methodResult) {
		LogProvider log = container.get(LogProvider.class);
		log
				.add(String
						.format("%s.process(%s, %s, %s", JunitActionMethodResultHandler.class.getSimpleName(),
								methodOwner, actionMethodInfo, methodParameter, methodResult));

	}

}
