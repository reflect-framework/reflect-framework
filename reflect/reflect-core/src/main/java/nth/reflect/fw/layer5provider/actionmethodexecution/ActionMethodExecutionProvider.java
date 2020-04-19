package nth.reflect.fw.layer5provider.actionmethodexecution;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ActionMethodExecutionProvider implements Provider {

	private List<ActionMethodResultHandler> actionMethodResultHandlers;

	public ActionMethodExecutionProvider(ActionMethodResultHandler... actionMethodResultHandlers) {
		this.actionMethodResultHandlers = Arrays.asList(actionMethodResultHandlers);
	}

	public ActionMethodResultHandler getActionMethodResultHandler(ActionMethodInfo actionMethodInfo) {
		Optional<ActionMethodResultHandler> result = actionMethodResultHandlers
				.stream()
				.filter(a -> a.canProcess(actionMethodInfo))
				.findFirst();
		return result.orElseThrow(() -> new NoActionMethodResultHandlerFoundException(actionMethodInfo));
	}
}
