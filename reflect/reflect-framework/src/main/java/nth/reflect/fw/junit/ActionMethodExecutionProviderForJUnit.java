package nth.reflect.fw.junit;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ActionMethodExecutionProviderForJUnit implements ActionMethodExecutionProvider {

	private final LogProvider log;

	public ActionMethodExecutionProviderForJUnit(LogProvider log) {
		this.log = log;
	}

	@Override
	public void execute(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		log.add(String.format("execute(%s, %s, %s)", methodOwner, methodInfo, methodParameter));
	}

}
