package nth.reflect.fw.swing.layer5provider.actionmethod.execution;

public class ActionMethodExecutionProvider
		implements nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	@Override
	public void executeOnUiThread(Runnable methodExecutionRunnable) {
		Thread methodExecutionThread = new Thread(methodExecutionRunnable);
		methodExecutionThread.start();
	}

}
