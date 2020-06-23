package nth.reflect.fw.junit;

import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider;

public class ActionMethodExecutionProviderForJUnit extends ActionMethodExecutionProvider {

	private final LogProvider log;

	public ActionMethodExecutionProviderForJUnit(LogProvider log) {
		this.log = log;
	}

	@Override
	public void executeOnUiThread(Runnable methodExecutionRunnable) {
		log.add(String.format("execute()"));
	}

}
