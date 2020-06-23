package nth.reflect.fw.junit;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class JunitActionMethodPreHandler implements ActionMethodPreHandler {

	@Override
	public boolean canProcess(ActionMethodInfo actionMethodInfo) {
		return true;
	}

	@Override
	public void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) throws Exception {
		LogProvider log = container.get(LogProvider.class);
		log
				.add(String
						.format("%s.preProcess(%s, %s, %s)", JunitActionMethodPreHandler.class.getSimpleName(),
								methodInfo, methodOwner.getClass().getCanonicalName(), methodParameter));

	}

}
